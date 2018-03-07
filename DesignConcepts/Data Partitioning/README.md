Data partitioning (also known as sharding) is a technique to break up a big database (DB) into many smaller parts. It is the process of splitting up a DB/table across multiple machines to improve the manageability, performance, availability and load balancing of an application. The justification for data sharding is that, after a certain scale point, it is cheaper and more feasible to scale horizontally by adding more machines than to grow it vertically by adding beefier servers.

##  Partitioning Methods
* **Sharding (Horizontal Partitioning)** 
Sharding is the process of distributing data across multiple servers for storage. MongoDB uses sharding to manage massive data growth. With an increase in the data size, a single machine may not be able to store data or provide an acceptable read and write throughput.

  **Pros:** 
  * Sharding supports horizontal scaling and thus is capable of distributing data across multiple machines.
  * allows you to add more servers to your database to support data growth and automatically balances data and load across various servers.
  * A cluster can increase its capacity and input horizontally. For example, to insert data into a particular record, the application needs to access only the shard that holds the record. If a database has a 1 terabyte data set distributed amongst 4 shards, then each shard may hold only 256 Giga Byte of data. If the database contains 40 shards, then each shard will hold only 25 Giga Byte of data.
  
* **Vertical Partitioning** 
 In this scheme, we divide our data to store tables related to a specific feature to their own server. For example, if we are building Instagram like application, where we need to store data related to users, all the photos they upload and people they follow, we can decide to place user profile information on one DB server, friend lists on another and photos on a third server.

Vertical partitioning is straightforward to implement and has a low impact on the application. The main problem with this approach is that if our application experiences additional growth, then it may be necessary to further partition a feature specific DB across various servers (e.g. it would not be possible for a single server to handle all the metadata queries for 1 billion photos by 100 million users).

  
