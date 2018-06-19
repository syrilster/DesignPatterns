Postgres has a number of index types. Each of these indexes can be useful, but which one to use depends on:
* The data type and then sometimes 
* The underlying data within the table, and 
* The types of lookups performed

## Type of Indexes

* B-Tree:  A B-tree index creates a tree that will keep itself balanced and even. When it goes to look something up based on that index it will traverse down the tree to find the key the tree is split on and then return you the data you’re looking for. Using an index is much faster than a sequential scan because it may only have to read a few pages as opposed to sequentially scanning thousands of them (when you’re returning only a few records).

  If you run a standard CREATE INDEX it creates a B-tree for you. B-tree indexes are valuable on the most common data types such as text, numbers, and timestamps. If you’re just getting started indexing your database and aren’t leveraging too many advanced Postgres features within your database, using standard B-Tree indexes is likely the path you want to take.

* Generalized Inverted Index (GIN)
* Generalized Inverted Seach Tree (GiST)
* Space partitioned GiST (SP-GiST)
* Block Range Indexes (BRIN)
* Hash
