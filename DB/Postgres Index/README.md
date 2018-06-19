Postgres has a number of index types. Each of these indexes can be useful, but which one to use depends on:
* The data type and then sometimes 
* The underlying data within the table, and 
* The types of lookups performed

## Type of Indexes

* B-Tree
* Generalized Inverted Index (GIN)
* Generalized Inverted Seach Tree (GiST)
* Space partitioned GiST (SP-GiST)
* Block Range Indexes (BRIN)
* Hash
