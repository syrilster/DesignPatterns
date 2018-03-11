## Components in the Java Memory Model
* Heap - Objects and references, references to class which got loaded, static variables.
* Stack - Local variables, method parameters, method flow etc.
* Method Area - Part of heap area which has information of references to class which got loaded during class loading.
* Native memory - All other language specific code. Ex: Socket connections details.
* PC Register - Sequence of instructions which needs to be executed next. One PC register per thread. Tells the thread what to do next.

## JMM
Heap - Young generation, Old generation
* Young Generation - All new objects created here. Minor GC happens here when this area is full.
    * Eden - All new objects in here. 
    * Survivor 1 - Objects which did not get GC'ed moves here. 
    * Survivor 2 - Objects which did not get GC'ed moves here.
* Old/Tenured Generation - Objects which survived the young generation GC's after several lifecycles(Threshold can be defined) move here. i.e long lived objects. Major GC happens here. 

## GC Steps
* Marking - Identify the objects not in use.
* Normal Deletion - Objects not in use will be removed from heap.
* Deletion + Compacting - Once objects are removed from heap, group the free memory location together so that the performance of memory allocation of subsequent new object creation will be faster.

## Garbage Collection Types
* Serial GC - For simple standalone applications with low memory footprints. Not suitable for applications where lot if threads are running.
* Parallel GC - Can spawn n number of threads based on the CPU cores. Also called throughput collectors as it used the CPU cycles to speed up the GC. Uses in thread for old generation and multiple threads for young generation.
* Concurrent Mark Sweep - Low pause collectors. Minor GC uses parallel GC algo and CMS is used for old generation area. Uses concurrency to have less stop the world pauses.
* G1 Garbage first GC - Divides the heap space into multiple equally sized areas and when a GC is required it first collects the area with lesser live data.

## PermGen vs Meta Space
