Requirements and Goals of the System
## Functional Requirements:

* Messenger should support one-on-one conversations between users.
* Messenger should keep track of online/offline statuses of its users.
* Messenger should support persistent storage of chat history.

## Non-functional Requirements:
* Users should have real-time chat experience with minimum latency.
* Our system should be highly consistent; users should be able to see the same chat history on all their devices.
* Messenger’s high availability is desirable; we can tolerate lower availability in the interest of consistency.

## Extended Requirements:
* Group Chats: Messenger should support multiple people talking to each other in a group.
* Push notifications: Messenger should be able to notify users of new messages when they are offline.

## High Level Design
At a high level, we will need a chat server that would be the central piece orchestrating all the communications between users. When a user wants to send a message to another user, they will connect to the chat server and send the message to the server; the server then passes that message to the other user and also stores it in the database.

![fbmesshdd](https://user-images.githubusercontent.com/6800366/37703551-0ddfef5e-2d1c-11e8-9985-e7bac9b0b97e.png)

The detailed workflow would look like this:

* User-A sends a message to User-B through the chat server.
* The server receives the message and sends an acknowledgment to User-A.
* The server stores the message in its database and sends the message to User-B.
* User-B receives the message and sends the acknowledgment to the server.
* The server notifies User-A that the message has been delivered successfully to User-B.

## Detailed Component Design

At the high level our system needs to handle following use cases:

* Receive incoming messages and deliver outgoing messages.
* Store and retrieve messages from the database.
* Keep a record of which user is online or has gone offline and notify all the relevant users about these status changes.



