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
