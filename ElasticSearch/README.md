* Open source search agent based on Apache lucene which includes full text search and uses REST Api.
* Real time distributed and analytics engine.
* Used for Single page applications(SPA) projects.

Elastic Search: ElasticSearch is an Open Source (Apache 2), Distributed, RESTful, Search Engine built on top of Apache Lucene.

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
