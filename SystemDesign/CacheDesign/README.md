Cache system is a widely adopted technique in almost every applications today. In addition, it applies to every layer of the technology stack. For instance, at network area cache is used in DNS lookup and in web server cache is used for frequent requests.

In short, a cache system stores common used resources (maybe in memory) and when next time someone requests the same resource, the system can return immediately. It increases the system efficiency by consuming more storage space.

## LRU
The way LRU cache works is quite simple. When the client requests resource A, the  following happens:

* If A exists in the cache, we just return immediately.
* If not and the cache has extra storage slots, we fetch resource A and return to the client. In addition, insert A into the cache.
* If the cache is full, we kick out the resource that is least recently used and replace it with resource A.

