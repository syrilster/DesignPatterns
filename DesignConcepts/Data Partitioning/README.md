Data partitioning (also known as sharding) is a technique to break up a big database (DB) into many smaller parts. It is the process of splitting up a DB/table across multiple machines to improve the manageability, performance, availability and load balancing of an application. The justification for data sharding is that, after a certain scale point, it is cheaper and more feasible to scale horizontally by adding more machines than to grow it vertically by adding beefier servers.

##  Partitioning Methods
* **Sharding (Horizontal Partitioning)** 
Sharding is the process of distributing data across multiple servers for storage. MongoDB uses sharding to manage massive data growth. With an increase in the data size, a single machine may not be able to store data or provide an acceptable read and write throughput.
  **Pros:** 
  * Sharding supports horizontal scaling and thus is capable of distributing data across multiple machines.
  * allows you to add more servers to your database to support data growth and automatically balances data and load across various servers.
  * A cluster can increase its capacity and input horizontally. For example, to insert data into a particular record, the application needs to access only the shard that holds the record. If a database has a 1 terabyte data set distributed amongst 4 shards, then each shard may hold only 256 Giga Byte of data. If the database contains 40 shards, then each shard will hold only 25 Giga Byte of data.

  
