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
* The master then aggregates the results and sends the final result to the client who submitted the job.

![mr advantage](https://user-images.githubusercontent.com/6800366/41507162-92c39c16-724a-11e8-9a5b-2a2dae155ead.PNG)



