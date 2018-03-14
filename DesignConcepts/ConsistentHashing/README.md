Distributed Hash Table (DHT) is one of the fundamental component used in distributed scalable systems. Hash Tables need key, value and a hash function, where hash function maps the key to a location where the value is stored.

**index = hash_function(key)**

Suppose we are designing a distributed caching system. Given ‘n’ cache servers, an intuitive hash function 
would be **‘key % n’**. It is a simple and commonly used approach. But it has two major drawbacks:

* It is NOT horizontally scalable. Whenever a new cache host is added to the system, all existing mappings are broken. It will be a pain point in maintenance if the caching system contains lots of data. Practically it becomes difficult to schedule a downtime to update all caching mappings.
* It may NOT be load balanced, especially for non-uniformly distributed data. In practice, it can be easily assumed that the data will not be distributed uniformly. For the caching system, it translates into some caches becoming hot and saturated while the others idle and almost empty.
In such situations, consistent hashing is a good way to improve the caching system.

## What is Consistent Hashing?
Consistent hashing is a very useful strategy for distributed caching system and DHTs. It allows distributing data across a cluster in such a way that will minimize reorganization when nodes are added or removed. Hence, making the caching system easier to scale up or scale down.

In Consistent Hashing when the hash table is resized (e.g. a new cache host is added to the system), only k/n keys need to be remapped, where k is the total number of keys and n is the total number of servers. Recall that in a caching system using the ‘mod’ as the hash function, all keys need to be remapped.

In consistent hashing objects are mapped to the same host if possible. When a host is removed from the system, the objects on that host are shared by other hosts; and when a new host is added, it takes its share from a few hosts without touching other’s shares.

How it works?
As a typical hash function, consistent hashing maps a key to an integer. Suppose the output of the hash function is in the range of [0, 256]. Imagine that the integers in the range are placed on a ring such that the values are wrapped around.

Here’s how consistent hashing works:

* Given a list of cache servers, hash them to integers in the range.
* To map a key to a server,
    * Hash it to a single integer.
    * Move clockwise on the ring until finding the first cache it encounters.
    * That cache is the one that contains the key. See animation below as an example: key1 maps to cache A; key2 maps to cache C.

![consistemt hashing](https://user-images.githubusercontent.com/6800366/37410286-4c6e7412-27c6-11e8-898e-7034b3a29063.PNG)

To add a new server, say D, keys that were originally residing at C will be split. Some of them will be shifted to D, while other keys will not be touched.

To remove a cache or if a cache failed, say A, all keys that were originally mapping to A will fall into B, and only those keys need to be moved to B, other keys will not be affected.

For load balancing, as we discussed in the beginning, the real data is essentially randomly distributed and thus may not be uniform. It may make the keys on caches unbalanced.

To handle this issue, we add “virtual replicas” for caches. Instead of mapping each cache to a single point on the ring, we map it to multiple points on the ring, i.e. replicas. This way, each cache is associated with multiple portions of the ring.

If the hash function is “mixes well,” as the number of replicas increases, the keys will be more balanced.

## Problem of Eventual Consistency:

Now, even if one node holding the key “google.com” goes down, the value could be found in one of the other nodes i.e. 20-30 or 30-40 as the values were replicated. Although this looks like a great technique it has a problem related to consistency. What if one of the nodes did not get updated? (Simply because it was not available at that point of time). This is when eventual consistency comes into picture. In a real distributed data store like Cassandra, consistency is configurable by the system designers as per the consistency semantics. System designers need to take a call on whether to have eventual consistency or to have strong consistency.

## What is the consistency model of Amazon Dynamo DB? (From the FAQ’s of Amazon Dynamo DB)

When reading data from Amazon Dynamo DB, users can specify whether they want the read to be eventually consistent or strongly consistent:

Eventually Consistent Reads (Default) – the eventual consistency option maximizes your read throughput. However, an eventually consistent read might not reflect the results of a recently completed write. Consistency across all copies of data is usually reached within a second. Repeating a read after a short time should return the updated data.

Strongly Consistent Reads — in addition to eventual consistency, Amazon Dynamo DB also gives you the flexibility and control to request a strongly consistent read if your application, or an element of your application, requires it. A strongly consistent read returns a result that reflects all writes that received a successful response prior to the read.

## When to use consistent hashing?

* Scale (i.e. best suited for large business)
* Transactional Data – Business transactions i.e. data which is changing a lot.
* High Availability – Always on.

## Optimizing Consistent Hashing:

Check existence of a particular value in a node using a Bloom Filter. Bloom Filter is an extremely fast way to test the existence of data in a set. A bloom filter lets us know if an item exists in set or not. This is a good way of avoiding unnecessary I/O. In a bloom filter false positives are possible but false negative are not reported.
