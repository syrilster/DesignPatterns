postgress - support for native full text search
full text search --> Meaning the ability to identify that run and running are the same.

Implementing a table that has a column with a data type of **tsvector** and I am trying to understand what index would be better to use?

* GIN stands for Generalized Inverted Index. GIN is designed for handling cases where the items to be indexed are composite values, and the queries to be handled by the index need to search for element values that appear within the composite items. 
  For example, the items could be documents, and the queries could be searches for documents containing specific words.

* An inverted index is an index structure storing a set of (key, posting list) pairs, where 'posting list' is a set of documents in which the key occurs. (A text document would usually contain many keys.) The primary goal of Gin indices is support for highly scalable, full-text search in PostgreSQL.

* Gin consists of a B-tree index constructed over entries (ET, entries tree), where each entry is an element of the indexed value (element of array, lexeme for tsvector) and where each tuple in a leaf page is either a pointer to a B-tree over item pointers (PT, posting tree), or a list of item pointers (PL, posting list) if the tuple is small enough.

## If you have a problem that involves finding the things within X distance of other things or finding what things have nothing within X distance do not use ST_Distance for filtering:
Finding closest things within 1609 meters (~1 mile)

SELECT roads.roadname, pois.poiname
 FROM roads INNER JOIN pois 
   ON ST_DWithin(roads.geog, pois.geog, 1609);
   
**CARTO (formerly CartoDB)** is a Software as a Service (SaaS) cloud computing platform that provides GIS and web mapping tools for display in a web browser. 
The company is positioned as a Location Intelligence platform due to tools with an aptitude for data analysis and visualization that do not require previous GIS or development experience.



