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



