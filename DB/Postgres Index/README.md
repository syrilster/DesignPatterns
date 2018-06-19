Postgres has a number of index types. Each of these indexes can be useful, but which one to use depends on:
* The data type and then sometimes 
* The underlying data within the table, and 
* The types of lookups performed

## Type of Indexes

* **B-Tree:**  A B-tree index creates a tree that will keep itself balanced and even. When it goes to look something up based on that index it will traverse down the tree to find the key the tree is split on and then return you the data you’re looking for. Using an index is much faster than a sequential scan because it may only have to read a few pages as opposed to sequentially scanning thousands of them (when you’re returning only a few records).

  If you run a standard CREATE INDEX it creates a B-tree for you. B-tree indexes are valuable on the most common data types such as text, numbers, and timestamps. If you’re just getting started indexing your database and aren’t leveraging too many advanced Postgres features within your database, using standard B-Tree indexes is likely the path you want to take.

* **Generalized Inverted Index (GIN):** Generalized Inverted Indexes, commonly referred to as GIN, are most useful when you have data types that contain multiple values in a single column.

  From the Postgres docs: “GIN is designed for handling cases where the items to be indexed are composite values, and the queries to be handled by the index need to search for element values that appear within the composite items. For example, the items could be documents, and the queries could be searches for documents containing specific words.”

  The most common data types that fall into this bucket are:
  * hStore
  * Arrays
  * Range types
  * JSONB
  One of the beautiful things about GIN indexes is that they are aware of the data within composite values. But because a GIN index has specific knowledge about the data structure support for each individual type needs to be added, as a result not all datatypes are supported.
* **Generalized Inverted Seach Tree (GiST):** GiST indexes are most useful when you have data that can in some way overlap with the value of that same column but from another row. The best thing about GiST indexes: if you have say a geometry data type and you want to see if two polygons contained some point. In one case a specific point may be contained within box, while another point only exists within one polygon. The most common datatypes where you want to leverage GiST indexes are:
  * Geometry types
  * Text when dealing with full-text search  
  A GiST index is lossy, meaning that the index might produce false matches, and it is necessary to check the actual table row to eliminate such false matches. (PostgreSQL does this automatically when needed.) This doesn’t mean you’ll get wrong results, it just means Postgres has to do a little extra work to filter those false positives before giving your data back to you.

  Special note: GIN and GiST indexes can often be beneficial on the same column types. One can often boast better performance but larger disk footprint in the case of GIN and vice versa for GiST. When it comes to GIN vs. GiST there isn’t a perfect one size fits all, but the broad rules above apply
  
* Space partitioned GiST (SP-GiST)
* Block Range Indexes (BRIN)
* Hash
