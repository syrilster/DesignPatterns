## Why Cloud Storage?
* Availability: The motto of cloud storage services is to have data availability anywhere anytime. Users can access their files/photos from any device whenever and wherever they like.
* Reliability and Durability: Another benefit of cloud storage is that it offers 100% reliability and durability of data. Cloud storage ensures that users will never lose their data, by keeping multiple copies of the data stored on different geographically located servers.
* Scalability: Users will never have to worry about getting out of storage space. With cloud storage, you have unlimited storage as long as you are ready to pay for it.

##  Requirements and Goals of the System
Here are the top-level requirements for our system:

* Users should be able to upload and download their files/photos from any device.
* Users should be able to share files or folders with other users.
* should support automatic synchronization between devices, i.e., after updating a file on one device, it should get synchronized on all devices.
* The system should support storing large files up to a GB.
* ACID-ity is required. Atomicity, Consistency, Isolation and Durability of all file operations should be guaranteed.
Our system should support offline editing. Users should be able to add/delete/modify files while offline, and as soon as they come * online, all their changes should be synced to the remote servers and other online devices.

## Some Design Considerations
* We should expect huge read and write volumes.
* Read to write ratio is expected to be nearly the same.
* Internally, files can be stored in small parts or chunks (say 4MB), this can provide a lot of benefits e.g. all failed operations shall only be retried for smaller parts of a file. If a user fails to upload a file, then only the failing chunk will be retried.
* We can reduce the amount of data exchange by transferring updated chunks only.
* By removing duplicate chunks, we can save storage space and bandwidth usage.
* Keeping a local copy of the metadata (file name, size, etc.) with the client can save us a lot of round trips to the server.
* For small changes, clients can intelligently upload the diffs instead of the whole chunk.

## High Level Design
The user will specify a folder as the workspace on their device. Any file/photo/folder placed in this folder will be uploaded to the cloud, and whenever a file is modified or deleted, it will be reflected in the same way in the cloud storage. The user can specify similar workspaces on all their devices and any modification done on one device will be propagated to all other devices to have the same view of the workspace everywhere.

At a high level, we need to store files and their metadata information like File Name, File Size, Directory, etc., and who this file is shared with. So, we need some servers that can help the clients to upload/download files to Cloud Storage and some servers that can facilitate updating metadata about files and users. We also need some mechanism to notify all clients whenever an update happens so they can synchronize their files.

As shown in the diagram below:
* Block servers will work with the clients to upload/download files from cloud storage.
* Metadata servers will keep metadata of files updated in a SQL or NoSQL database. 
* Synchronization servers will handle the workflow of notifying all clients about different changes for synchronization.

![hdd dropbox](https://user-images.githubusercontent.com/6800366/37654722-1f26be36-2c69-11e8-8f38-4be5b3d36ad5.png)

## Component Design

**Client**
The Client Application monitors the workspace folder on user’s machine and syncs all files/folders in it with the remote Cloud Storage. The client application will work with the storage servers to upload, download and modify actual files to backend Cloud Storage. The client also interacts with the remote Synchronization Service to handle any file metadata updates e.g. change in the file name, size, modification date, etc.

Here are some of the essential operations of the client:
* Upload and download files.
* Detect file changes in the workspace folder.
* Handle conflict due to offline or concurrent updates.

**How do we handle file transfer efficiently?**
As mentioned above, we can **break each file into smaller chunks** so that we transfer only those chunks that are modified and not the whole file. Let’s say we divide each file into fixed size of 4MB chunks. In our metadata, we should also keep a record of each file and the chunks that constitute it.

**Should we keep a copy of metadata with Client?**
Keeping a local copy of metadata not only enable us to do offline updates but also saves a lot of round trips to update remote metadata.

**How can clients efficiently listen to changes happening on other clients?**
One solution could be that the clients periodically check with the server if there are any changes. The problem with this approach is that we will have a delay in reflecting changes locally as clients will be checking for changes periodically compared to server notifying whenever there is some change. If the client frequently checks the server for changes, it will not only be wasting bandwidth, as the server has to return empty response most of the time but will also be keeping the server busy. Pulling information in this manner is not scalable too.

A solution to above problem could be to use **HTTP long polling.** With long polling, the client requests information from the server with the expectation that the server may not respond immediately. If the server has no new data for the client when the poll is received, instead of sending an empty response, the server holds the request open and waits for response information to become available. Once it does have new information, the server immediately sends an HTTP/S response to the client, completing the open HTTP/S Request. Upon receipt of the server response, the client can immediately issue another server request for future updates.

**Based on the above considerations we can divide our client into following four parts:**

* **Internal** Metadata Database will keep track of all the files, chunks, their versions, and their location in the file system.

* **Chunker** will split the files into smaller pieces called chunks. It will also be responsible for reconstructing a file from its chunks. Our chunking algorithm will detect the parts of the files that have been modified by the user and only transfer those parts to the Cloud Storage; this will save us bandwidth and synchronization time.

* **Watcher** will monitor the local workspace folders and notify the Indexer (discussed below) of any action performed by the users, e.g., when users create, delete, or update files or folders. Watcher also listens to any changes happening on other clients that are broadcasted by Synchronization service.

* **Indexer** will process the events received from the Watcher and update the internal metadata database with information about the chunks of the modified files. Once the chunks are successfully submitted/downloaded to the Cloud Storage, the Indexer will communicate with the remote Synchronization Service to broadcast changes to other clients and update remote metadata database.

![dropbox comp](https://user-images.githubusercontent.com/6800366/37657076-f6cd308a-2c6f-11e8-92a5-4cfe8b14dfec.png)

## Metadata Database
The Metadata Database is responsible for maintaining the versioning and metadata information about files/chunks, users, and workspaces. The Metadata Database can be a relational database such as MySQL, or a NoSQL database service such as DynamoDB. Regardless of the type of the database, the Synchronization Service should be able to provide a consistent view of the files using a database, especially if more than one user work with the same file simultaneously. Using a relational database can simplify the implementation of the Synchronization Service as they natively support ACID properties.

Metadata Database should be storing information about following objects:
* Chunks
* Files
* User
* Devices
* Workspace (sync folders)

##  Synchronization Service
* The Synchronization Service is the component that processes file updates made by a client and applies these changes to other subscribed clients. It also synchronizes clients’ local databases with the information stored in the remote Metadata DB. The Synchronization Service is the most important part of the system architecture due to its critical role in managing the metadata and synchronizing users’ files.
* If a client was offline for a period, it polls the system for new updates as soon as it becomes online. When the Synchronization Service receives an update request, it checks with the Metadata Database for consistency and then proceeds with the update. Subsequently, a notification is sent to all subscribed users or devices to report the file update.

**Differencing algorithm to send part updates**
The Synchronization Service should be designed in such a way to transmit less data between clients and the Cloud Storage to achieve better response time. To meet this design goal, the Synchronization Service can employ a **differencing algorithm** to reduce the amount of the data that needs to be synchronized. Instead of transmitting entire files from clients to the server or vice versa, we can just transmit the difference between two versions of a file. Therefore, only the part of the file that has been changed is transmitted. This also decreases bandwidth consumption and cloud data storage for the end user. As described above we will be dividing our files into 4MB chunks and will be transferring modified chunks only. Server and clients can calculate a hash (e.g., SHA-256) to see whether to update the local copy of a chunk or not. On server if we already have a chunk with a similar hash (even from another user) we don’t need to create another copy, we can use the same chunk. 

* To be able to provide an efficient and scalable synchronization protocol we can consider using a communication middleware between clients and the Synchronization Service. The messaging middleware should provide scalable message queuing and change notification to support a high number of clients using pull or push strategies. This way, multiple Synchronization Service instances can receive requests from a **global request Queue**, and the communication middleware will be able to balance their load.

## Message Queuing Service
An important part of our architecture is a messaging middleware that should be able to handle a substantial number of requests. The Message Queuing Service supports asynchronous and loosely coupled message-based communication between distributed components of the system. The Message Queuing Service should be able to efficiently store any number of messages in a highly available, reliable and scalable queue.

Message Queuing Service will implement two types of queues in our system. 
* The Request Queue is a global queue, and all client will share it. Clients’ requests to update the Metadata Database will be sent to the Request Queue first, from there Synchronization Service will take it to update metadata. 
* The Response Queues that correspond to individual subscribed clients are responsible for delivering the update messages to each client. Since a message will be deleted from the queue once received by a client, we need to create separate Response Queues for each subscribed client to share update messages.

![dropbox queue](https://user-images.githubusercontent.com/6800366/37695231-b5d0d58a-2cf3-11e8-8545-0440d1aab364.png)

## Component Diagram

The sequence below shows the interaction between the components of the application in a scenario when Client A updates a file that is shared with Client B and C, so they should receive the update too. If the other clients were not online at the time of the update, the Message Queuing Service keeps the update notifications in separate response queues for them until they become online later.

* Client A uploads chunks to cloud storage.
* Client A updates metadata and commits changes.
* Client A gets confirmation, and notifications are sent to Clients B and C about the changes.
* Client B and C receive metadata changes and download updated chunks.

![drop box comp diag](https://user-images.githubusercontent.com/6800366/37695396-fb9b2998-2cf4-11e8-849f-20867ab2cffc.png)



