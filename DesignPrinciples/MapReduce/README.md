## Two main Hadoop components
* HDFS - Storage built over Hbase.
* Map Reduce - For processing.

## Map Reduce Overivew
* This helps in processing data parallely in distributed environment.
* Example: Process Big Data on hadoop HDFS --> Map Reduce --> Result.
* Why do we need Map Reduce for this? It is because the Big Data stored in HDFS is stored as chunks in different data Nodes. The data does not reside in one single location like a traditional RDBMS.

## Map Reduce Use cases
* Index and search (Inverted Index)
* Analytics (Join, Selection)
* Recommendation (Ex: Sort)
* Classification (Top N records)

## Map Reduce Advantage
* Data is processed in parallel among several different clusters and hence processing is fast.
* Moving data to processing is costly due to network latency. In MapReduce, processing is moved to the place where data resides.
* The slave machines showed below do the processing parallely and send the results to the master. These results are not as big as the data itself and hence consume less bandwidth.
* The master(resource manager) then aggregates the results and sends the final result to the client who submitted the job.

![mr advantage](https://user-images.githubusercontent.com/6800366/41507162-92c39c16-724a-11e8-9a5b-2a2dae155ead.PNG)

## Map reduce in detail
A MapReduce framework (or system) is usually composed of three operations (or steps):
* **Map:** each worker node applies the map function to the local data, and writes the output to a temporary storage. A master node ensures that only one copy of redundant input data is processed.
* **Shuffle:** worker nodes redistribute data based on the output keys (produced by the map function), such that all data belonging to one key is located on the same worker node.
* **Reduce:** worker nodes now process each group of output data, per key, in parallel.
* Below is the example of a Map Reduce word count process.

  ![mr word count](https://user-images.githubusercontent.com/6800366/41507253-b31fdc48-724c-11e8-82dd-3b9857f0b33b.PNG)

* MapReduce can be applied to significantly larger datasets than "commodity" servers can handle – a large server farm can use MapReduce to sort a petabyte of data in only a few hours. The parallelism also offers some possibility of recovering from partial failure of servers or storage during the operation: if one mapper or reducer fails, the work can be rescheduled – assuming the input data is still available.

## Performance consideration
* MapReduce programs are not guaranteed to be fast. The main benefit of this programming model is to exploit the optimized shuffle operation of the platform, and only having to write the Map and Reduce parts of the program. 
* In practice, the author of a MapReduce program has to take the shuffle step into consideration; in particular the partition function and the amount of data written by the Map function can have a large impact on the performance and scalability. 
* For processes that complete quickly, and where the data fits into main memory of a single machine or a small cluster, using a MapReduce framework usually is not effective. 
* Since MR frameworks are designed to recover from the loss of whole nodes during the computation, they write interim results to distributed storage. This crash recovery is expensive, and only pays off when the computation involves many computers and a long runtime of the computation.
* On such problems, implementations keeping all data in memory and simply restarting a computation on node failures or —when the data is small enough— non-distributed solutions will often be faster than a MapReduce system.




