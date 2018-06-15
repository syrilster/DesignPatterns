**PostGIS** is a spatial database extender for PostgreSQL object-relational database. It adds support for geographic objects allowing location queries to be run in SQL.
```
SELECT superhero.name
FROM city, superhero
WHERE ST_Contains(city.geom, superhero.geom)
AND city.name = 'Gotham';
```
postgres - support for native full text search.

full text search --> Meaning the ability to identify that run and running are the same.

Implementing a table that has a column with a data type of **tsvector** and I am trying to understand what index would be better to use?

* GIN stands for Generalized Inverted Index. GIN is designed for handling cases where the items to be indexed are composite values, and the queries to be handled by the index need to search for element values that appear within the composite items. 
  For example, the items could be documents, and the queries could be searches for documents containing specific words.

* An inverted index is an index structure storing a set of (key, posting list) pairs, where 'posting list' is a set of documents in which the key occurs. (A text document would usually contain many keys.) The primary goal of Gin indices is support for highly scalable, full-text search in PostgreSQL.

* Gin consists of a B-tree index constructed over entries (ET, entries tree), where each entry is an element of the indexed value (element of array, lexeme for tsvector) and where each tuple in a leaf page is either a pointer to a B-tree over item pointers (PT, posting tree), or a list of item pointers (PL, posting list) if the tuple is small enough.

## Leveraging PostgreSQL Gin index
Suppose we want to implement simple search functionality for a web app. Say, for example, we wanted to search through all users in the system. Also imagine that we have ~ 1 million users currently stored in the system.

The requirements for this search implementation state that we should be able to search via partial matches, and search via multiple columns (e.g. first_name, last_name). More concretely, if we have the following users: "Hank Lillard" and "Lilly Adams", an input query of "Lil" should return both users. We can solve this problem using a Gin index with a special option in order to achieve extremely performant searches.

Example of creating a GIN index with gin_trm_options for trigram search: (A trigram is a group of three consecutive characters taken from a string)

```
CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE INDEX users_search_idx ON users USING gin (first_name gin_trgm_ops, last_name gin_trgm_ops);
```

**What is gin_trgm_ops?**
This option tells Postgres to index using trigrams over our selected columns. A trigram is a data structure that hold 3 letters of a word. Essentially, Postgres will break down each text column down into trigrams and use that in the index when we search against it.

**Caveats**
The only downside of this approach is that the input query must be at least 3 letters, as Postgres will need to be able to extract at least one trigram from the input query in order to use our trigram index.

## If you have a problem that involves finding the things within X distance of other things 
Finding closest things within 1609 meters (~1 mile)

```
SELECT roads.roadname, pois.poiname, ST_Distance(pois.geog, 'POINT(-73.98 40.77)')
 FROM roads INNER JOIN pois 
   ON ST_DWithin(roads.geog, pois.geog, 1609)
 WHERE ts @@ to_tsquery('english', 'oak & tree');
 ```
   
**CARTO (formerly CartoDB)** is a Software as a Service (SaaS) cloud computing platform that provides GIS and web mapping tools for display in a web browser. 
The company is positioned as a Location Intelligence platform due to tools with an aptitude for data analysis and visualization that do not require previous GIS or development experience.



