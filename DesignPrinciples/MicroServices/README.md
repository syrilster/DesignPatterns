## Overview

**Monolithic application** - Has various system capabilities providing a plethora of services at one place. wr.t scaling you need to take the entire service and distribute to multiple machines.

**Microservices** - Each of the above capabilites is put as a seperate process. And instead of having one single process, have a network of communicating processes. Example a unix command: ps -ef | grep java. Multiple unix commands communicating using a pipe command. wr.t scaling this is a flexible approach as different services can be scaled to different machines so some services can get more resources required when compared to others.

![micro vs monolith](https://user-images.githubusercontent.com/6800366/38226207-ff181b00-3715-11e8-9c95-62888cc6bfaa.PNG)


![micro vs monolith](https://user-images.githubusercontent.com/6800366/38198456-8e38812e-36aa-11e8-9924-7fd3b5b93490.PNG)


## Common characteristics of microservices

* **Componentization via Services:** 
In the MicroServices world services are out-of-process components who communicate with a mechanism such as a web service request, or remote procedure call. One main reason for using services as components (rather than libraries) is that services are **independently deployable.**
* **Organized around Business Capabilities**:

* **Products not Projects**: Microservice model advocates the notion that a team should own a product over its full lifetime. A common inspiration for this is Amazon's notion of "you build, you run it" where a development team takes full responsibility for the software in production. This brings developers into day-to-day contact with how their software behaves in production and increases contact with their users, as they have to take on at least some of the support burden.

There's no reason why this same approach can't be taken with monolithic applications, but the smaller granularity of services can make it easier to create the personal relationships between service developers and their users.




