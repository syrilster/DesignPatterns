Distributed Hash Table (DHT) is one of the fundamental component used in distributed scalable systems. Hash Tables need key, value and a hash function, where hash function maps the key to a location where the value is stored.

**index = hash_function(key)**

Suppose we are designing a distributed caching system. Given ‘n’ cache servers, an intuitive hash function 
would be ‘key % n’. It is a simple and commonly used approach. But it has two major drawbacks:

* It is NOT horizontally scalable. Whenever a new cache host is added to the system, all existing mappings are broken. It will be a pain point in maintenance if the caching system contains lots of data. Practically it becomes difficult to schedule a downtime to update all caching mappings.
* It may NOT be load balanced, especially for non-uniformly distributed data. In practice, it can be easily assumed that the data will not be distributed uniformly. For the caching system, it translates into some caches becoming hot and saturated while the others idle and almost empty.
In such situations, consistent hashing is a good way to improve the caching system.
