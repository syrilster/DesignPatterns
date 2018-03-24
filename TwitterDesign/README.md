## Requirements and Goals of the System

**Functional Requirements**

* Users should be able to post new tweets.
* A user should be able to follow other users.
* Users should be able to mark tweets favorite.
* The service should be able to create and display user’s timeline consisting of top tweets from all the people the user follows.
* Tweets can contain photos and videos.

**Non-functional Requirements**

* Our service needs to be highly available.
* Acceptable latency of the system is 200ms for timeline generation.
* Consistency can take a hit (in the interest of availability), if a user doesn’t see a tweet for a while, it should be fine.

**Extended Requirements**

* Searching tweets.
* Reply to a tweet.
* Trending topics – current hot topics/searches.
* Tagging other users.
* Tweet Notification.
* Who to follow? Suggestions?
* Moments.

##  System APIs
We can have SOAP or REST APIs to expose the functionality of our service. The definition of the API for posting a new tweet:

```
tweet(api_dev_key, tweet_data, tweet_location, user_location, media_ids, maximum_results_to_return)
```
**Parameters:**
* api_dev_key (string): The API developer key of a registered account. This will be used to, among other things, throttle users based on their allocated quota.
* tweet_data (string): The text of the tweet, typically up to 140 characters.
* tweet_location (string): Optional location (longitude, latitude) this Tweet refers to. 
* user_location (string): Optional location (longitude, latitude) of the user adding the tweet.
* media_ids (number[]): Optional list of media_ids to be associated with the Tweet. (All the media photo, video, etc.) need to be uploaded separately.

Returns: (string)
A successful post will return the URL to access that tweet. Otherwise, an appropriate HTTP error is returned.

## High Level System Design
It is clear from the requirements that this will be a read-heavy system.

At a high level, we need multiple application servers to serve all these requests with load balancers in front of them for traffic distributions. On the backend, we need an efficient database that can store all the new tweets and can support a huge number of reads. We would also need some file storage to store photos and videos.

![twitterhdd](https://user-images.githubusercontent.com/6800366/37862869-4349fa58-2f7a-11e8-864b-05dfbac40448.png)

Although our expected daily write load is 100 million and read load is 28 billion tweets. This means, on an average our system will receive around 1160 new tweets and 325K read requests per second. This traffic will be distributed unevenly throughout the day, though, at peak time we should expect at least a few thousand write requests and around 1M read requests per second.

## Database Schema
We need to store data about users, their tweets, their favorite tweets, and people they follow.

![twitter db design](https://user-images.githubusercontent.com/6800366/37862883-7a094b66-2f7a-11e8-868c-1f151fc89dda.png)

## Data Sharding
Since we have a huge number of new tweets every day and our read load is extremely high too, we need to distribute our data onto multiple machines such that we can read/write it efficiently. We have many options to shard our data.

**Sharding based on UserID:** We can try storing all the data of a user on one server. While storing, we can pass the UserID to our hash function that will map the user to a database server where we will store all of the user’s tweets, favorites, follows, etc. While querying for tweets/follows/favorites of a user, we can ask our hash function where can we find the data of a user and then read it from there. This approach has a couple of issues:

* What if a user becomes hot? There could be a lot of queries on the server holding the user. This high load will affect the performance of our service.
* Over time some users can end up storing a lot of tweets or have a lot of follows compared to others. Maintaining a uniform distribution of growing user’s data is quite difficult.
To recover from these situations either we have to repartition/redistribute our data **or use consistent hashing.**

**Sharding based on TweetID:** Our hash function will map each TweetID to a random server where we will store that Tweet. To search tweets, we have to query all servers, and each server will return a set of tweets. A centralized server will aggregate these results to return them to the user. Let’s look into timeline generation example, here are the number of steps our system has to perform to generate a user’s timeline:

* Our application (app) server will find all the people the user follows.
* App server will send the query to all database servers to find tweets from these people.
* Each database server will find the tweets for each user, sort them by recency and return the top tweets.
* App server will merge all the results and sort them again to return the top results to the user.

This approach solves the problem of hot users, but in contrast to sharding by UserID, we have to query all database partitions to find tweets of a user, which can result in **higher latencies.**

We can further improve our performance by introducing cache to store hot tweets in front of the database servers.

**Sharding based on Tweet creation time:** Storing tweets based on recency will give us the advantage of fetching all the top tweets quickly, and we only have to query a very small set of servers. But the problem here is that the traffic load will not be distributed, e.g: while writing, all new tweets will be going to one server, and the remaining servers will be sitting idle. Similarly while reading, the server holding latest data will have a very high load as compared to servers holding old data.

**What if we can combine sharding by TweedID and Tweet creation time?** If we don’t store tweet creation time separately and use TweetID to reflect that, we can get benefits of both the approaches. This way it will be quite quick to find latest Tweets. For this, we must make each TweetID universally unique in our system, and each TweetID should contain timestamp too.

**We can use epoch time for this.** Let’s say our TweetID will have two parts; the first part will be representing epoch seconds and the second part will be an auto-incrementing sequence. So, to make a new TweetID, we can take the current epoch time and append an auto-incrementing number to it. We can figure out shard number from this TweetID and store it there.

**What could be the size of our TweetID?** Let’s say our epoch time starts today, how many bits we would need to store the number of seconds for next 50 years?

86400 sec/day * 365 (days a year) * 50 (years) => 1.6B
We would need 31 bits to store this number. Since on average we are expecting 1150 new tweets per second, we can allocate 17 bits to store auto incremented sequence; this will make our TweetID 48 bits long. So, every second we can store (2^17 => 130K) new tweets. We can reset our auto incrementing sequence every second. For fault tolerance and better performance, we can have two database servers to generate auto-incrementing keys for us, one generating even numbered keys and the other generating odd numbered keys.

![twitter shard strategy](https://user-images.githubusercontent.com/6800366/37862956-d8727866-2f7b-11e8-9217-07b616e0540e.png)


If we assume our current epoch seconds are “1483228800”, our TweetID will look like this:

1483228800 000001
1483228800 000002
1483228800 000003
1483228800 000004
…

If we make our TweetID 64bits (8 bytes) long, we can easily store tweets for next 100 years and also store them for mili-seconds granularity.
