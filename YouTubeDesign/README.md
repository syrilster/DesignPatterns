## Requirements and Goals of the System

**Functional Requirements:**

* Users should be able to upload videos.
* Users should be able to share and view videos.
* Users can perform searches based on video titles.
* Our services should be able to record stats of videos, e.g., likes/dislikes, total number of views, etc.
* Users should be able to add and view comments on videos.

**Non-Functional Requirements:**

* The system should be highly reliable, any video uploaded should not be lost.
* The system should be highly available. Consistency can take a hit (in the interest of availability), if a user doesn’t see a video for a while, it should be fine.
* Users should have real time experience while watching videos and should not feel any lag.

Not in scope: Video recommendation, most popular videos, channels, and subscriptions, watch later, favorites, etc.

## System APIs
We can have SOAP or REST APIs to expose the functionality of our service. Following could be the definitions of the APIs for uploading and searching videos:

```
uploadVideo(api_dev_key, video_title, vide_description, tags[], category_id, default_language, 
                        recording_details, video_contents)
```

**Parameters:**
* api_dev_key (string): The API developer key of a registered account. This will be used to, among other things, throttle users based on their allocated quota.
* video_title (string): Title of the video.
* video_description (string): Optional description of the video.
* tags (string[]): Optional tags for the video.
* category_id (string): Category of the video, e.g., Film, Song, People, etc.
* default_language (string): For example English, Mandarin, Hindi, etc.
* recording_details (string): Location where the video was recorded.
* video_contents (stream): Video to be uploaded.

Returns: (string)
A successful upload will return HTTP 202 (request accepted), and once the video encoding is completed, the user is notified through email with a link to access the video. We can also expose a queryable API to let users know the current status of their uploaded video.
```
searchVideo(api_dev_key, search_query, user_location, maximum_videos_to_return, page_token)
```
**Parameters:**
* api_dev_key (string): The API developer key of a registered account of our service.
* search_query (string): A string containing the search terms.
* user_location (string): Optional location of the user performing the search.
* maximum_videos_to_return (number): Maximum number of results returned in one request.
* page_token (string): This token will specify a page in the result set that should be returned.

Returns: (JSON)
A JSON containing information about the list of video resources matching the search query. Each video resource will have a video title, a thumbnail, a video creation date and how many views it has.

## High Level Design
At a high-level we would need following components:

* Processing Queue: Each uploaded video will be pushed to a processing queue, to be de-queued later for encoding, thumbnail generation, and storage.
* Encoder: To encode each uploaded video into multiple formats.
* Thumbnails generator: We need to have a few thumbnails for each video.
* Video and Thumbnail storage: We need to store video and thumbnail files in some distributed file storage.
* User Database: We would need some database to store user’s information, e.g., name, email, address, etc.
* Video metadata storage: Metadata database will store all the information about videos like title, file path in the system, uploading user, total views, likes, dislikes, etc. Also, it will be used to store all the video comments.

![youutbe hdd](https://user-images.githubusercontent.com/6800366/37888191-95db6ecc-30e3-11e8-9594-2126a3966936.png)

##Database Schema
**Video metadata storage - MySql**
Videos metadata can be stored in a SQL database. Following information should be stored with each video:

VideoID
Title
Description
Size
Thumbnail
Uploader/User
Total number of likes
Total number of dislikes
Total number of views

For each **video comment**, we need to store following information:

CommentID
VideoID
UserID
Comment
TimeOfCreation

**User data storage - MySql**
UserID, Name, email, address, age, registration details etc.

