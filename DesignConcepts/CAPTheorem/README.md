## Distributed Systems

A collection of independent computers that appear to its users as one computer. i.e a bunch of computers working together serving some goal and looking to its user as just one computer doing all the work.

## Characteristics:
* Computers Operate Concurrently.
* Computers fail independently.
* These computers don’t share a global clock.

Example of distributed systems: Amazon.in, tons of servers operating behind the scene to answer each request from users. Casandra DB, distributed key value store.

In a distributed system, node i.e computers are connected using LAN/WAN. LAN implemented using Fiber Optic, Co-axial cable and WAN using satellite communication. Most important point in a distributed system is that there is no physical memory shared between the nodes. So the only way for communication between the nodes is by sending messages on LAN/WAN.


![cap](https://user-images.githubusercontent.com/6800366/37402406-710e8dbc-27b1-11e8-987c-bb07cf2e3e94.png)
