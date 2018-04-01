Cache system is a widely adopted technique in almost every applications today. In addition, it applies to every layer of the technology stack. For instance, at network area cache is used in DNS lookup and in web server cache is used for frequent requests.

In short, a cache system stores common used resources (maybe in memory) and when next time someone requests the same resource, the system can return immediately. It increases the system efficiency by consuming more storage space.

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


In case you want a cache system to retain values based on how often they were accessed: 

LFU (Least Frequently Used)
cache design which counts how often an item is needed. Those that are used least often are discarded first. 

ARC (Adaptive Replacement Cache)
which balances between LRU and LFU, to give improved result. It dynamically updates the size of protected segment and the probationary segment so to make optimum use of available cache space.



