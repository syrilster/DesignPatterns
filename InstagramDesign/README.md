**Instagram** is a social networking service, which enables its users to upload and share their pictures and videos with other users. Users can share either publicly or privately, as well as through a number of other social networking platforms, such as Facebook, Twitter, Flickr, and Tumblr.

## Functional Requirements

Users should be able to upload/download/view photos.
Users can perform searches based on photo/video titles.
Users can follow other users.
The system should be able to generate and display a user’s timeline consisting of top photos from all the people the user follows.

## Some Design Considerations
The system would be read-heavy, so we will focus on building a system that can retrieve photos quickly.

* Practically users can upload as many photos as they like. Efficient management of storage should be a crucial factor while designing this system.
* Low latency is expected while reading images.
* Data should be 100% reliable. If a user uploads an image, the system will guarantee that it will never be lost.

## Important Read
https://www.scribd.com/document/89025069/Mike-Krieger-Instagram-at-the-Airbnb-tech-talk-on-Scaling-Instagram

## High Level System Design
At a high-level, we need to support two scenarios, one to upload photos and the other to view/search photos. Our service would need some block storage servers to store photos and also some database servers to store metadata information about the photos.

![insta design](https://user-images.githubusercontent.com/6800366/37567179-38a5655a-2ae9-11e8-9fd6-5b6aa7547d93.png)

## Database Schema
* We need to store data about users, their uploaded photos, and people they follow. Photo table will store all data related to a photo, we need to have an index on (PhotoID, CreationDate) since we need to fetch recent photos first.

![insta db](https://user-images.githubusercontent.com/6800366/37567382-2ea79232-2aec-11e8-988c-48b7b35fa31e.png)

One simple approach for storing the above schema would be to use an RDBMS like MySQL since we require joins. But relational databases come with their challenges, especially when we need to scale them. For details, please take a look at SQL vs. NoSQL.

* We can store photos in a distributed file storage like HDFS or S3.

* We can store the above schema in a distributed key-value store to enjoy benefits offered by NoSQL. All the metadata related to photos can go to a table, where the ‘key’ would be the ‘PhotoID’ and the ‘value’ would be an object containing PhotoLocation, UserLocation, CreationTimestamp, etc.

* We also need to store relationships between users and photos, to know who owns which photo. Another relationship we would need to store is the list of people a user follows. For both of these tables, we can use a wide-column datastore like Cassandra. For the ‘UserPhoto’ table, the ‘key’ would be ‘UserID’ and the ‘value’ would be the list of ‘PhotoIDs’ the user owns, stored in different columns. We will have a similar scheme for the ‘UserFollow’ table.

* Cassandra or key-value stores in general, always maintain a certain number of replicas to offer reliability. Also, in such data stores, deletes don’t get applied instantly, data is retained for certain days (to support undeleting) before getting removed from the system permanently.

