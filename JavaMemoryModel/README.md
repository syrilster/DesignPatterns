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

## How does JVM identify live objects?

For this process to work, the JVM has to know the **location in memory of every object reference**. This is a necessary condition for a garbage collector to be precise.

With the **mark and sweep algorithm**, when we run the main() thread, (one thread that will always be there along with other threads), this thread can either be alive or the execution is complete, but it may also be the case that while main() thread is silent, some other threads are working on it. The entire life cycle of the thread is maintained on its stack trace. When the garbage collector finds this that the particular object is no longer more accessible to any threads, i.e. it is unreachable or no references exist to the object, such an object becomes vulnerable to be caught by the garbage collector.

Now, **to make the object unreachable**, it is clear that the references to that object should cease to exist and this can be done by removing the reference to the object either by making the references null or by reassigning a new object to the existing reference for the current object.

**What if the “Reference is Null”?**

All we are doing here, is to nullify the references to an object and once there are no reachable references, the object looses its importance and is no longer needed thus becomes eligible for Garbage Collection.

An example.
```
public class RefrenceGarbage{
public static void main(String [] args) {
StringBuffer greeting = new StringBuffer("Hello");
System.out.println(greeting);
greeting = null;
}
}
```

In the example code above, we can see that before the reference to the StringBuffer object “Hello” is removed or nulled by setting 'greeting' to null, there can be seen on the console the output of println(), which is “Hello”, but it no longer refers to “Hello” after 'greeting' = null;

**Try “Reassigning a Reference Variable”**

Another way, in effect, to remove the reference to a particular object and making it eligible for Garbage Collection is to set the reference variable which currently refers to it to a new object. Thus, re-assigning the reference variable. Example:

```
class RefrenceGarbage{
	public static void main(String [] args) {
		StringBuffer greeting = new StringBuffer("Hello");
		StringBuffer greeting2 = new StringBuffer("Howdy");
		System.out.println(greeting);
		greeting = greeting2;
		}
}
```

**How about Isolating a Reference ?**

This time, we are not removing the references, but just isolating them. Consider a case where a reference to an object is also used to refer to another reference to the same object. Now imagine that two such instances exist and that they refer to each other. If all other references to these two objects are removed, then even though each object still has a valid reference, there will be no way for any live thread to access either object. When the garbage collector runs, it will remove such objects.

However, keep in mind that the garbage collection is not completely within our control and the way the JVM works and selects the algorithm is all left to JVM’s discretion.

## Garbage Collection Types
* Serial GC - For simple standalone applications with low memory footprints. Not suitable for applications where lot of threads are running.
* Parallel GC - Can spawn n number of threads based on the CPU cores. Also called throughput collectors as it uses the CPU cycles to speed up the GC. Uses single thread for old generation and multiple threads for the young generation.
* Concurrent Mark Sweep - Low pause collectors. Minor GC uses parallel GC algo and CMS is used for the old generation area. Uses concurrency to have less stop the world pauses.
* G1 Garbage first GC - Divides the heap space into multiple equally sized areas and when a GC is required it first collects the area with lesser live data.

## PermGen vs Meta Space
**Meta Space**
* Part of native memory i.e OS level memory consuming the OS memory.
* Process sizes can go large since the meta space goes to the OS.
* Too many class loading can bring down the server. Memory used is the OS memory and not allocated on the heap.

**PermGen**
* Used to store objects of methods and classes.
* Results in OutOfMemory error due to lack of permGen space.

**Taking thread dumps**

Using jstack command line tool.

* ps -ef | grep java to get process id of a running java application
* jstack -l <pid> to get thread dump
* jvisual - Visually monitors, troubleshoots, and profiles Java applications.
