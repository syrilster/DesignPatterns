Cache system is a widely adopted technique in almost every applications today. In addition, it applies to every layer of the technology stack. For instance, at network area cache is used in DNS lookup and a web server cache is used for frequent requests.

In short, a cache system stores commonly used resources (maybe in memory) and when the next time someone requests the same resource, the system can return immediately. It increases the system's efficiency by consuming more storage space.

## LRU
The way LRU cache works is quite simple. When the client requests resource A, the  following happens:

* If A exists in the cache, we just return immediately.
* If not and the cache has extra storage slots, we fetch resource A and return to the client. In addition, insert A into the cache.
* If the cache is full, we kick out the resource that is least recently used and replace it with resource A.

## LRU design
An LRU cache should support the operations: lookup, insert and delete. Apparently, in order to achieve fast lookup, we need to use a hash. By the same token, if we want to make insert/delete fast, something like linked list is preffered. Since we need to locate the least recently used item efficiently, we need something in order like queue, stack or sorted array.

* A Queue which is implemented using a doubly linked list. The maximum size of the queue will be equal to the total number of frames available (cache size). The most recently used pages will be near front end and least recently pages will be near rear end i.e. head and tail of queue.

* A Hash with page number as key and address of the corresponding queue node as value. 

When a page is referenced, the required page may be in the memory. If it is in the memory, we need to detach the node of the list and bring it to the front of the queue. **If the required page is not in the memory**, we bring that in memory. In simple words, we add a new node to the front of the queue and update the corresponding node address in the hash. If the queue is full, i.e. all the frames are full, we remove a node from the rear of queue, and add the new node to the front of queue. 

Here’s how it works. when resource A is requested, we check the hash table to see if A exists in the cache. If exists, we can immediately locate the corresponding queue node and return the resource. If not, we’ll add A into the cache. If there are enough space, we just add a to the end of the queue and update the hash table. Otherwise, we need to delete the least recently used entry. To do that, we can easily remove the head of the queue and the corresponding entry from the hash table.

## Eviction policy
When the cache is full, we need to remove existing items for new resources. In fact, deleting the least recently used item is just one of the most common approaches. So are there other ways to do that?

As mentioned above, The strategy is to maximum the chance that the requesting resource exists in the cache. 

* Random Replacement (RR) – As the term suggests, we can just randomly delete an entry.
* Least frequently used (LFU) – We keep the count of how frequent each item is requested and delete the one least frequently used.
* W-TinyLFU – This is a modern eviction policy. In a nutshell, the problem of LFU is that sometimes an item is only used frequently in the past, but LFU will still keep this item for a long while. W-TinyLFU solves this problem by calculating frequency within a time window. It also has various optimizations of storage. 

   W-TinyLFU uses the Segmented LRU (SLRU) policy for long term retention. An entry starts in the probationary segment and on a subsequent access it is promoted to the protected segment (capped at 80% capacity). When the protected segment is full it evicts into the probationary segment, which may trigger a probationary entry to be discarded. This ensures that entries with a small reuse interval (the hottest) are retained and those that are less often reused (the coldest) become eligible for eviction.

   ![image](https://user-images.githubusercontent.com/6800366/38196857-e80f9082-36a2-11e8-9b7a-30cb0fdd6661.png)

* ARC (Adaptive Replacement Cache): This is a balance between LRU and LFU, to give improved result. It dynamically updates the size of protected segment and the probationary segment so to make optimum use of available cache space.


## Concurrency

When multiple clients are trying to update the cache at the same time, there can be conflicts. For instance, two clients may compete for the same cache slot and the one who updates the cache last wins. This is the classic reader-writer problem.

The common solution of course is using a lock. The downside is obvious – it affects the performance a lot. How can we optimize this?

**Lock Striping to the rescue**
In case you have only one lock for the whole data structure like Array or Map and you are synchronizing it and using it in a multi-threaded environment, then effectively at any given point in time, only one thread can manipulate the map as there is only a single lock. All the other threads would be waiting to get the monitor.

If there can be separate locks for separate buckets, thread contention won't be at the whole data structure level but at the bucket level. That's the concept of lock striping. Having separate locks for a portion of a data structure where each lock is locking on a variable sized set of independent objects.

Lock striping works by splitting the cache into many smaller independent regions i.e. to split the cache into multiple shards and have a lock for each of them so that clients won’t wait for each other if they are updating cache from different shards. However, given that hot entries are more likely to be visited, certain shards will be more often locked than others.

An alternative is to use commit logs. To update the cache, we can store all the mutations into logs rather than update immediately. And then some background processes will execute all the logs asynchronously. This strategy is commonly adopted in database design.

## Distributed cache
When the system gets to a certain scale, we need to distribute the cache to multiple machines.

The general strategy is to maintain a hash table that maps each resource to the corresponding machine. Therefore, when requesting resource A, from this hash table we know that machine M is responsible for cache A and direct the request to M. At machine M, it works similar to local cache discussed above. Machine M may need to fetch and update the cache for A if it doesn’t exist in memory. After that, it returns the cache back to the original server.

## Distributed key-value store

The underline system of Cassandra is a key-value storage system and Cassandra is widely used in many companies like Apple, Facebook etc.

## Basic key-value storage
How would you design a simple key-value storage system in a single machine?

The most straightforward thing is to use a hash table to store key-value pairs, which is how most this kind of systems work nowadays. A hash table allows you to read/write a key-value pair in constant time and it’s extremely easy to use. Most languages have built-in support for this.

However, the downside is also obvious. Using a hash table usually means you need to store everything in memory, which may not be possible when the data set is big. There are two common solutions:

* Compress your data. This should be the first thing to think about and often there are a bunch of stuff you can compress. For example, you can store reference instead of the actual data. You can also use float32 rather than float64. In addition, using different data representations like bit array (integer) or vectors can be effective as well.
* Storing on a disk. When it’s impossible to fit everything in memory, you may store part of the data into disk. To further optimize this, you can think of the system as a cache system. Frequently visited data is kept in memory and the rest is on a disk.
 

## Distributed key-value storage
The most interesting topic is definitely scaling the key-value storage into multiple machines. If you want to support big data, you will implement distributed system for sure. 

Since a single machine doesn’t have enough storage for all the data, the general idea here is to split the data into multiple machines by some rules and a coordinator machine can direct clients to the machine with requested resource. The question is how to split the data into multiple machines and more importantly, what is a good strategy to partition data?

**Sharding**
Suppose all the keys are URLs like http://discussionhive.com and we have 26 machines. One approach is to divide all keys (URLs) based on the first character of the URL (after “www”) to these 26 machines. For example, http://discussionhive.com will be stored at machine G and http://blog.interviewquestions.com will be stored at machine B. So what are disadvantages of this design?

Let’s ignore cases where URL contains ASCII characters. A good sharding algorithm should be able to balance traffic equally to all machines. In other words, each machine should receive equal requests ideally. Apparently, the above design doesn’t work well. First of all, the storage is not distributed equally. Probably there are much more URLs starting with “a” than “z”. Secondly, some URLs are much more popular like Facebook and Google.

In order to balance the traffic, you’d better make sure that keys are distributed randomly. Another solution is to use the hash of URL, which usually have much better performance. There are many things tp consider while designing the distributed system. When splitting data into multiple machines, it’s important to balance the traffic. That’s why it’s better to make sure that keys are distributed randomly. This can be achieved using **consistent hashing.** 

## System availability
To evaluate a distributed system, one key metric is system availability. For instance, suppose one of our machines crashes for some reason (maybe hardware issue or program bugs), how does this affect our key-value storage system?

Apparently, if someone requests resources from this machine, we won’t be able to return the correct response. You may not consider this issue when building a side project. However, if you are serving millions of users with tons of servers, this happens quite often and you can’t afford to manually restart the server every time. This is why availability is essential in every distributed system nowadays. How do we address this issue?

The most common solution is **replication.** By setting machines with duplicate resources, we can significantly reduce the system downtime. If a single machine has 10% of chance to crash every month, then with a single backup machine, we reduce the probability to 1% when both are down.

## Replica VS sharding
At first glance, replica is quite similar to sharding. So what’s the relation of these two? And how would you choose between replica and sharding when designing a distributed key-value store?

First of all, we need to be clear about the purpose of these two techniques. Sharding is basically used to splitting data to multiple machines since a single machine cannot store too much data. Replica is a way to protect the system from downtime. With that in mind, if a single machine can’t store all the data, replica won’t help. 

## Consistency
By introducing replicas, we can make the system more robust. However, one issue is about consistency. Let’s say for machine A1, we have replica A2. How do you make sure that A1 and A2 have the same data? For instance, when inserting a new entry, we need to update both machines. But it’s possible that the write operation fails in one of them. So over time, A1 and A2 might have quite a lot inconsistent data, which is a big problem.

* First approach is to keep a local copy in coordinator. Whenever updating a resource, the coordinator will keep the copy of updated version. So in case the update fails, the coordinator is able to re-do the operation.

* Another approach is **commit log.** If you have been using Git, the concept of commit log should be quite familiar to you. Basically, for each node machine, it’ll keep the commit log for each operation, which is like the history of all updates. Another example is **MongoDB.** The oplog (operations log) is a special capped collection that keeps a rolling record of all operations that modify the data stored in your databases. MongoDB applies database operations on the primary and then records the operations on the primary’s oplog. The secondary members then copy and apply these operations in an asynchronous process. All replica set members contain a copy of the oplog, which allows them to maintain the current state of the database.

* Suppose when the requested resource locates in A1, A2 and A3, the coordinator can ask from all three machines. If by any chance the data is different, the system can resolve the conflict on the fly.

It’s worth to note that all of these approaches are not mutually exclusive. You can definitely use multiple ones based on the application. 

## Read throughput
Usually, key-value storage system should be able to support a large amount of read requests. So what approaches will you use to improve read throughput?

To improve read throughput, the common approach is always taking advantage of memory. If the data is stored in disk inside each node machine, we can move part of them in memory. A more general idea is to use cache. 
