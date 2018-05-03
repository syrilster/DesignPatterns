## Elastic Search
* ElasticSearch is an Open Source (Apache 2), Distributed, RESTful, Search Engine built on top of Apache Lucene.
* Open source search agent based on Apache lucene which includes full text search and uses REST Api.
* Real time distributed and analytics engine.
* Used for Single page applications(SPA) projects.

## Elastic Search Internals

* ES has cluster of nodes and within the cluster there are ES indexes spanning multiple nodes through shards.
* Each shard is essentially a Lucene index which is a full text search library on which ES is built on.
* Lucene index has segments -> Segments have data structures called inverted index, stored fields, document values etc.

## Inverted Index:
* Consists of a sorted dictionary (For the phrase frequency) and postings containing the document having this term or phrase.
Example consider a text file as below:
1. Winter is coming
2. Ours is the fury
3. The choice is yours

| Phrase        | Frequency     | docs  |
| ------------- |:-------------:| -----:|
| Choice        | 1             |     3 |
| coming        | 1             |     1 |
| fury          | 1             |     2 |
| is            | 3             | 1,2,3 |

Steps involved in search:
* Look in the sorted dictionary and then return (intersect or union) the documents.
* Index by lower casing the text and splitting or tokenizing based on white spaces.
* For a prefix search - i.e starting with "c" its a binary search in the dictionary.
* For wild card search like *ours --> ours, yours. 
* want to search for suffix? Then index the reversed words i.e suffix -> xiffus
* Geo location data search ? Lucene converts this to a Geo hash
* Numeric search? 123 -> {1-hundreds, 12-tens, 123}
* To handle searches with typo in phrases, ES uses Lucene's fuzzy queries based on Levenshtein edit distance.

## Stored Fields 
* Key value store for Given a document search the content. i.e given a doc get the title of it.
Example: ES by default stores the entire JSON source using this data structure.

1. _source = {"words": "winter is coming ..."} 
2. _source = {"words": "ours is the ..."} 
3. _source = {"words": "The choice is yours ..."} 

* This is not helpful when you need to read millions of values from a field. Example: To sort or to aggregate.

## Document Values
* Highly optimized columnar store for storing values of the same type.
* Used when you need to sort or aggregate millions of values.
* Uses Field cache to have all values of a particular field loaded in memory.

Example: 

1 : 14

2 : 42

3 : 33

* Inverted index + Stored Fields + Document values = Segments.
* Segments are immutable. i.e. they never change. Example when a delete or update happens a re-indexing is required. So never have real time data like counters.
* Lucene compresses data.
* Having multiple shards means that data from different nodes needs to be merged to get the fiinal doc.
* Log kind of data with timestamp can be partioned as one Lucene index per day. This will reduce the search time as each day is partitioned.

![es](https://user-images.githubusercontent.com/6800366/39579775-2f98a4e6-4f05-11e8-8ef5-3a2f92e89e57.PNG)


------- Setup

Jest is a Java HTTP Rest client for ElasticSearch.

JestClient : It is an interface for the application to interact with the elastic index/server.

    Get, Delete, Search, SearchScroll, Index, DeleteIndex, JestResult

JestClientFactory : Factory to get the JestClient instance

ElasticSearchClient: Abstract class and a MR wrapper for JestClient, provides the necessary interface to play with the ElasicSearch index of a specific type.

PatientDirectory: Concrete implementation for ElasticSearchClient to play with the Patient index.

    overrides,

        delete: to augment event audit

#######



------- Indexing

PatientDirectoryLoaderTask: Job to populate the index by reading the patient records from the DB.

                            Indexes the patient records in a batch(1000) and has the support to retry once per batch in case of failure.

PatientDirectoryState: Class to indicate the state of the index. Ex: It may not be ready if the index population is in process.

                       Also tracks the percentage of completion while the indexing is in process.

PatientDirectoryLoadTaskDataHelper : Helper class to load all the required attributes for the required associations of a Patient to be indexed.

PatientDirectorySerializer : Utility Class to serialize & Deserialize the patient model to/from a json string.

------- Search

PaitentDirectoryQueryModel : QueryModel class with attributes exposed for querying the Patient index. This can also take the SubjectFilter  and map it to the attribures in the query model.  

PatientDirectoryMDB : Listener class for the JMS queue (jms/PatientDirectoryMDB)

MirthResultsServer.getPatientDirectory() : An instance to the PatientDirectory is made available in MirthResultsServer class as a static reference.


JEST API: Jest provides its own Java API’s and also allows you to use ES Java API’s to build queries which are then submitted to the RESTful endpoints.

JestClientFactory : Factory to get the JestClient instance. MR2 ElasticSearchClient is using this.

ElasticSearchClient: contains low-level methods to access the Elasticsearch REST API.

PatientDirectory: Provides concrete implementation of API's such as indexing, search, executeBulk, delete, audit etc. This also has the information regarding ES settings and mappings file.

PatientDirectoryLoaderTask: A multi threaded job which takes care of the initial patient data model to be indexed.

com.mirth.results.patientdirectory.sync.threads - Configure the number of threads. Default 5.
com.mirth.results.patientdirectory.sync.bulk - Configure the batch size. Default 1000.
MirthResultsSessionLocal: Provides with methods such as syncDataWithElasticSearch() which can be called in an action bean. This is required when there is an update/delete to an existing model.
