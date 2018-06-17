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

![mr advantage](https://user-images.githubusercontent.com/6800366/41507162-92c39c16-724a-11e8-9a5b-2a2dae155ead.PNG)



