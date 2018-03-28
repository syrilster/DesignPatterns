Load balancing helps you scale horizontally across an ever-increasing number of servers, but caching will enable you to make vastly better use of the resources you already have, as well as making otherwise unattainable product requirements feasible. Caches take advantage of the locality of reference principle: recently requested data is likely to be requested again. They are used in almost every layer of computing: hardware, operating systems, web browsers, web applications and more. A cache is like short-term memory: it has a limited amount of space, but is typically faster than the original data source and contains the most recently accessed items. Caches can exist at all levels in architecture but are often found at the level nearest to the front end, where they are implemented to return data quickly without taxing downstream levels.
* **Application Server Cache**
Placing a cache directly on a request layer node enables the local storage of response data. Each time a request is made to the service, the node will quickly return local, cached data if it exists. If it is not in the cache, the requesting node will query the data from disk.

  **Cons:** What happens when you expand this to many nodes? If the request layer is expanded to multiple nodes, it’s still quite possible to have each node host its own cache. However, if your load balancer randomly distributes requests across the nodes, the same request will go to different nodes, thus increasing cache misses. Two choices for overcoming this hurdle are global caches and distributed caches.
* **Distributed Cache**
In a distributed cache, each of its nodes own part of the cached data. Typically, the cache is divided up using a consistent hashing function, such that if a request node is looking for a certain piece of data, it can quickly know where to look within the distributed cache to determine if that data is available.

  **Cons:** How to resolve a missing node. Some distributed caches get around this by using redundant nodes; however, you can imagine how this logic can get complicated quickly, especially when you add or remove nodes from the request layer.
* **Global Cache**
A global cache is just as it sounds: all the nodes use the same single cache space. This involves adding a server, or file store of some sort, faster than your original store and accessible by all the request layer nodes. Each of the request nodes queries the cache in the same way it would a local one.
* **Content Distribution Network (CDN)**
CDNs are a kind of cache that comes into play for sites serving large amounts of static media. In a typical CDN setup, a request will first ask the CDN for a piece of static media; the CDN will serve that content if it has it locally available. If it isn’t available, the CDN will query the back-end servers for the file and then cache it locally and serve it to the requesting user. Ex: Amazon CloudFront

## Cache Invalidation
While caching is fantastic, it does require some maintenance for keeping cache coherent with the source of truth (e.g., database). If the data is modified in the database, it should be invalidated in the cache, if not, this can cause inconsistent application behavior.

Solving this problem is known as **cache invalidation**, there are three main schemes that are used:

* Write-through cache: Under this scheme data is written into the cache and the corresponding database at the same time. The cached data allows for fast retrieval, and since the same data gets written in the permanent storage, we will have complete data consistency between cache and storage. Also, this scheme ensures that nothing will get lost in case of a crash, power failure, or other system disruptions.

 Although write through minimizes the risk of data loss, since every write operation must be done twice before returning success to the client, this scheme has the disadvantage of higher latency for write operations.

* Write-around cache: This technique is similar to write through cache, but data is written directly to permanent storage, bypassing the cache. This can reduce the cache being flooded with write operations that will not subsequently be re-read, but has the disadvantage that a read request for recently written data will create a “cache miss” and must be read from slower back-end storage and experience higher latency.

* Write-back cache: Under this scheme, data is written to cache alone, and completion is immediately confirmed to the client. The write to the permanent storage is done after specified intervals or under certain conditions. This results in low latency and high throughput for write-intensive applications, however, this speed comes with the risk of data loss in case of a crash or other adverse event because the only copy of the written data is in the cache.

## Cache eviction policies
Following are some of the most common cache eviction policies:

* First In First Out (FIFO): The cache evicts the first block accessed first without any regard to how often or how many times it was accessed before.
* Last In First Out (LIFO): The cache evicts the block accessed most recently first without any regard to how often or how many times it was accessed before.
* Least Recently Used (LRU): Discards the least recently used items first.
* Most Recently Used (MRU): Discards, in contrast to LRU, the most recently used items first.
* Least Frequently Used (LFU): Counts how often an item is needed. Those that are used least often are discarded first.
* Random Replacement (RR): Randomly selects a candidate item and discards it to make space when necessary.

##  Common Problems of Sharding
On a sharded database, there are certain extra constraints on the different operations that can be performed. Most of these constraints are due to the fact that, operations across multiple tables or multiple rows in the same table, will no longer run on the same server.

*  **Joins and Denormalization:** Performing joins on a database which is running on one server is straightforward, but once a database is partitioned and spread across multiple machines it is often not feasible to perform joins that span database shards. Such joins will not be performance efficient since data has to be compiled from multiple servers. A common workaround for this problem is to denormalize the database so that queries that previously required joins can be performed from a single table. Of course, the service now has to deal with all the perils of denormalization such as data inconsistency.
* **Referential integrity:** As we saw that performing a cross-shard query on a partitioned database is not feasible, similarly trying to enforce data integrity constraints such as foreign keys in a sharded database can be extremely difficult.
* **Rebalancing:** There could be many reasons we have to change our sharding scheme:

  * The data distribution is not uniform, e.g there are a lot of places for a particular ZIP code, that cannot fit into one database partition.
  * There are a lot of load on a shard, e.g there are too many requests being handled by the DB shard dedicated to user photos.
  
## Additional Read - REDIS

**Redis (REmote DIctionary Server)** is an in-memory, key-value database, commonly referred to as a data structure server. One of the key differences between Redis and other key-value databases is Redis’s ability to store and manipulate high-level data types. These data types are fundamental data structures (lists, maps, sets, and sorted sets) that most developers are familiar with. Redis’s exceptional performance, simplicity, and atomic manipulation of data structures lends itself to solving problems that are difficult or perform poorly when implemented with traditional relational databases.
To implement dictionaries (used for the main dictionary, but also for hash and set objects, and in conjunction with a skip list for zset objects), Redis use separate chaining hash tables, whose access complexity is O(1+n/k) where n is the number of items and k the number of buckets.

## Common Use Cases

* Caching – Due to its high performance, developers have turned to Redis when the volume of read and write operations exceed the capabilities of traditional databases. With Redis’s capability to easily persist the data to disk, it is a superior alternative to the traditional memcached solution for caching.
* Publish and Subscribe – Since version 2.0, Redis provides the capability to distribute data utilizing the Publish/Subscribe messaging paradigm. Some organizations have moved to Redis and away from other message queuing systems (i.e., RabbitMQ, zeromq) due to Redis’s simplicity and performance.
* Queues – Projects such as Resque use Redis as the backend for queueing background jobs.
* Counters – Atomic commands such as HINCRBY(HINCRBY key field increment), allow for a simple and thread-save implementation of counters. Creating a counter is as simple as determining a name for a key and issuing the HINCRBY command. There is no need to read the data before incrementing, and there are no database schemas to update. Since these are atomic operations, the counters will maintain consistency when accessed from multiple application servers.

## Companies Using Redis

* Twitter – Deployed a massive Redis cluster to store the timelines for all users. Utilizing the list data structure, Twitter stores the 800 most recent incoming tweets for a given user. View the presentation given by Twitter on how they distribute timelines at scale.
* Pinterest – Stores the user follower graphs in a Redis cluster where data is sharded across hundreds of instances. Pinterest turned to Redis after finding that their original solution of MySQL and memcached was reaching its limits. More on how Pinterest is using Redis.
* Github – An early adopter of the Redis project, Github has developed and open-sourced the library, Resque, to facilitate the execution of background jobs that have been placed on a queue. Github developers took advantage of the fact that Redis had solved many of the difficult queueing issues, so the developers could stay focused on the difficult worker scheduling issues. More on how Github uses Redis for their job queueing needs.

## What makes Redis so special?

* The combination of high-level data structures, high performance, and overall intuitiveness allow Redis to fill the role as the Swiss Army knife of data stores for a developer.
* Redis is well suited to solving challenges encountered when developing real-time systems, thanks to the predictability of operations applied to the data in the database.
* Redis attributes much of its performance to that fact that the data always resides in-memory. Data can be persisted to disk, but incorrect configuration could lead to data loss when Redis is shutdown improperly.

## How does Redis achieve O(1) complexity for most of their operations?

Redis makes sure the number of buckets grows with the number of items so that in practice n/k is kept low. This rehashing activity is done incrementally in background. When the number of items is significant, the complexity is close to O(1) (amortized).

Other stores (Cassandra for instance) are designed to store data on disk while minimizing the number of random I/Os for performance reasons. A hash table is not a good data structure for this, because it does not enforce the locality of the data (it does not benefit from buffer caching very well). So disk based stores usually use B-tree variants (most RDBMS) or log-structured merge (LSM) trees variants (Cassandra), which have O(log n) complexity.

So yes, Redis offers O(1) for many operations, but there is a constraint: all data should fit in memory. There is no magic here.
