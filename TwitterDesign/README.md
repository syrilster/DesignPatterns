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
