Distributed Hash Table (DHT) is one of the fundamental component used in distributed scalable systems. Hash Tables need key, value and a hash function, where hash function maps the key to a location where the value is stored.

**index = hash_function(key)**

Suppose we are designing a distributed caching system. Given ‘n’ cache servers, an intuitive hash function 
would be **‘key % n’**. It is a simple and commonly used approach. But it has two major drawbacks:

* It is NOT horizontally scalable. Whenever a new cache host is added to the system, all existing mappings are broken. It will be a pain point in maintenance if the caching system contains lots of data. Practically it becomes difficult to schedule a downtime to update all caching mappings.
* It may NOT be load balanced, especially for non-uniformly distributed data. In practice, it can be easily assumed that the data will not be distributed uniformly. For the caching system, it translates into some caches becoming hot and saturated while the others idle and almost empty.
In such situations, consistent hashing is a good way to improve the caching system.

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
