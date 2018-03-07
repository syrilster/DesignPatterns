To utilize full scalability and redundancy, we can try to balance the load at each layer of the system. We can add LBs at three places:

* Between the user and the web server
* Between web servers and an internal platform layer, like application servers or cache servers
* Between internal platform layer and database.

## Ways to implement LB:
* **Smart Client**
A smart client will take a pool of service hosts and balances load across them. It also detects hosts that are not responding to avoid sending requests their way. Ex: WiFi - Smart client load balancing distributes the client load between neighboring APs, improving overall operational efficiency.
* **Hardware Load Balancers**
The most expensive–but very high performance–solution to load balancing is to buy a dedicated hardware load balancer. If you require 100 percent uptime for your services, a server load balancer or application delivery controller (ADC) is the way to go. Get to know the leading load balancer hardware, software and cloud solutions from vendors like F5, Cisco, Citrix and Kemp Technologies.
* **Software Load Balancers**
If we want to avoid the pain of creating a smart client, and since purchasing dedicated hardware is excessive, we can adopt a hybrid approach, called software load-balancers.

  HAProxy is one of the popular open source software LB. Load balancer can be placed between client and server or between two server side layers. If we can control the machine where the client is running, HAProxy could be running on the same machine. Each service we want to load balance can have a locally bound port (e.g., localhost:9000) on that machine, and the client will use this port to connect to the server. This port is, actually, managed by HAProxy; every client request on this port will be received by the proxy and then passed to the backend service in an efficient way (distributing load). If we can’t manage client’s machine, HAProxy can run on an intermediate server.

## Load Balancing Algorithms:
* Round Robin: A simple method to balance load and providing fault tolerance. When requests are received, the addresses of server are returned in a rotating sequential manner. 
* Weighted Round Robin: This builds on the Round Robin load balancing method. In the weighted version each server in the pool is given a static numerical weighting according to their specifications. Servers with higher ratings get more requests sent to them.
* Least Connections: Neither round robin nor weighted round robin takes server load into consideration. This algorithm takes into consideration the least number of connections each server has The current request goes to the server that is servicing the least number of active sessions at the current time.
* Weighted Least Connections does to least connections what weighted round robin does to round robin. i.e. it introduces a weight, based on capacity of each server. So it’s a combination of weight and the least number of active connections. If two servers have the same number of active connections, then the server with the higher weighting will be allocated the new request.
* Agent based adaptive load balancing: Each server, has an agent that reports on its current load to the load balancer. This real time information is used when deciding which server is best suited to handle a request. This is used in conjunction with other techniques such as Weighted Round Robin and Weighted Least Connection.
* Weighted Response Time: This method uses the response information from a server health check to determine the server that is responding fastest at a particular time. The subsequent server access request is then sent to that server. This ensures that any servers that are under heavy load, and which will respond more slowly, are not sent new requests. This allows the load to even out on the available server pool over time.
* Source IP Hash: This uses an algorithm that takes the source and destination IP address of the client and server and combines them to generate a unique hash key. This key is used to allocate the client to a particular server. As the key can be regenerated if the session is broken this method of load balancing can ensure that the client request is directed to the same server that it was using previously. This is useful if it’s important that a client should connect to a session that is still active after a disconnection. For example, to retain items in a shopping cart between sessions.
