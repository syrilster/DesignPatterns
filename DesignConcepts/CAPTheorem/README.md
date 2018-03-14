## Distributed Systems

A collection of independent computers that appear to its users as one computer. i.e a bunch of computers working together serving some goal and looking to its user as just one computer doing all the work.

## Characteristics:
* Computers Operate Concurrently.
* Computers fail independently.
* These computers don’t share a global clock.

Example of distributed systems: Amazon.in, tons of servers operating behind the scene to answer each request from users. Casandra DB, distributed key value store.

In a distributed system, node i.e computers are connected using LAN/WAN. LAN implemented using Fiber Optic, Co-axial cable and WAN using satellite communication. Most important point in a distributed system is that there is no physical memory shared between the nodes. So the only way for communication between the nodes is by sending messages on LAN/WAN.

## CAP Theorem

CAP theorem states that it is impossible for a distributed software system to simultaneously provide more than two out of three of the following guarantees (CAP): Consistency, Availability and Partition tolerance. When we design a distributed system, trading off among CAP is almost the first thing we want to consider. CAP theorem says while designing a distributed system we can pick only two of:

* **Consistency:** All nodes see the same data at the same time. Consistency is achieved by updating several nodes before allowing further reads.
* **Availability:** Every request gets a response on success/failure. i.e At no point in time the system shall ask the user to come back later. Availability is achieved by replicating the data across different servers.
* **Partition Tolerance:** System continues to function even if the communication fails between nodes. This is a property of a distributed system to recover from node failures. i.e. Some nodes being cut off from the system (Ex: A network cable cut off, power outage in a data center) and when they come back online it should work seamlessly.


![cap](https://user-images.githubusercontent.com/6800366/37402406-710e8dbc-27b1-11e8-987c-bb07cf2e3e94.png)
