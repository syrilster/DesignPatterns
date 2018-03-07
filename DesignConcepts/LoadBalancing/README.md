To utilize full scalability and redundancy, we can try to balance the load at each layer of the system. We can add LBs at three places:

* Between the user and the web server
* Between web servers and an internal platform layer, like application servers or cache servers
* Between internal platform layer and database.

## Ways to implement LB:
* Smart Client
A smart client will take a pool of service hosts and balances load across them. It also detects hosts that are not responding to avoid sending requests their way. Ex: WiFi - Smart client load balancing distributes the client load between neighboring APs, improving overall operational efficiency.
* Hardware Load Balancers
The most expensive–but very high performance–solution to load balancing is to buy a dedicated hardware load balancer. If you require 100 percent uptime for your services, a server load balancer or application delivery controller (ADC) is the way to go. Get to know the leading load balancer hardware, software and cloud solutions from vendors like F5, Cisco, Citrix and Kemp Technologies.


