Relational databases are structured and have predefined schemas, like phone books that store phone numbers and addresses. Non-relational databases are unstructured, distributed and have a dynamic schema, like file folders that hold everything from a person’s address and phone number to their Facebook ‘likes’ and online shopping preferences.

## SQL
Relational databases store data in rows and columns. Each row contains all the information about one entity, and columns are all the separate data points. Some of the most popular relational databases are MySQL, Oracle, MS SQL Server, SQLite, Postgres, MariaDB, etc.

## NoSQL
Following are most common types of NoSQL:

**Columnar DB:**
* Limit the amount of data accessed per query by reading only the needed columns. i.e Store each column in a seperate file.
* Column repreesenttion compresses well. Each column can be compressed using a different scheme.
    Example : If a table is sorted by stock symbol, run length encoding can be used.
    APPL, APPL, APPL, IBM, IBM --> APPL(3), IBM(2)
* But the above compression example can't be used for all columns. like for example a stock price column which is floating point and changes too often. Here a different compression technique should be applied.
* For analytic SQL queries, column representation can provide order of magnitude reductions in query time.

**Key-Value Stores:** Data is stored in an array of key-value pairs. The ‘key’ is an attribute name, which is linked to a ‘value’. Well-known key value stores include Redis, Voldemort and Amazon Dynamo DB.

**Document Databases:** In these databases data is stored in documents, instead of rows and columns in a table, and these documents are grouped together in collections. Each document can have an entirely different structure. Document databases include the CouchDB and MongoDB.

**Graph Databases:** These databases are used to store data whose relations are best represented in a graph. Data is saved in graph structures with nodes (entities), properties (information about the entities) and lines (connections between the entities). Examples of graph database include Neo4J and InfiniteGraph.
