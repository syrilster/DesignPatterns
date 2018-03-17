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
