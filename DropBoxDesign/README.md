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

As shown in the diagram below, Block servers will work with the clients to upload/download files from cloud storage, and Metadata servers will keep metadata of files updated in a SQL or NoSQL database. Synchronization servers will handle the workflow of notifying all clients about different changes for synchronization.

![hdd dropbox](https://user-images.githubusercontent.com/6800366/37654722-1f26be36-2c69-11e8-8f38-4be5b3d36ad5.png)


