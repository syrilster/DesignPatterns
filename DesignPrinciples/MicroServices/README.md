## Overview

**Monolithic application** - Has various system capabilities providing a plethora of services at one place. wr.t scaling you need to take the entire service and distribute to multiple machines.

**Microservices** - Each of the above capabilites is put as a seperate process. And instead of having one single process, have a network of communicating processes. Example a unix command: ps -ef | grep java. Multiple unix commands communicating using a pipe command. wr.t scaling this is a flexible approach as different services can be scaled to different machines so some services can get more resources required when compared to others.

![micro vs monolith](https://user-images.githubusercontent.com/6800366/38226207-ff181b00-3715-11e8-9c95-62888cc6bfaa.PNG)


![micro vs monolith](https://user-images.githubusercontent.com/6800366/38198456-8e38812e-36aa-11e8-9924-7fd3b5b93490.PNG)


## Common characteristics of microservices

* **Componentization via Services:** 
    * Components should be independently replaceable.
    * Components should be independently upgradeabe.
In the MicroServices world services are out-of-process components who communicate with a mechanism such as a web service request, or remote procedure call. One main reason for using services as components (rather than libraries) is that services are **independently deployable.**
* **Organized around Business Capabilities**:

* **Products not Projects**: Microservice model advocates the notion that a team should own a product over its full lifetime. A common inspiration for this is Amazon's notion of "you build, you run it" where a development team takes full responsibility for the software in production. This brings developers into day-to-day contact with how their software behaves in production and increases contact with their users, as they have to take on at least some of the support burden.

    There's no reason why this same approach can't be taken with monolithic applications, but the smaller granularity of services can make it easier to create the personal relationships between service developers and their users.
    
* **Smart endpoints and dumb pipes**: 

    When building communication structures between different processes, some approaches (SOA) stress putting significant smarts into the communication mechanism itself. A good example of this is the Enterprise Service Bus (ESB), where ESB products often include sophisticated facilities for message routing, choreography, transformation, and applying business rules(BPEL).

    The microservice community favours an alternative approach: smart endpoints and dumb pipes. Applications built from microservices aim to be as decoupled and as cohesive as possible - they own their own domain logic and act more as filters in the classical Unix sense - receiving a request, applying logic as appropriate and producing a response. These are choreographed using simple RESTish protocols rather than complex protocols such as WS-Choreography or BPEL or orchestration by a central tool.

    Microservice teams use the principles and protocols that the world wide web (and to a large extent, Unix) is built on. Often used resources can be cached with very little effort on the part of developers or operations folk.

    **The second approach** in common use is messaging over a lightweight message bus. The infrastructure chosen is typically dumb (dumb as in acts as a message router only) - simple implementations such as RabbitMQ or ZeroMQ don't do much more than provide a reliable asynchronous fabric - the smarts still live in the end points that are producing and consuming messages in the services.

    In a monolith, the components are executing in-process and communication between them is via either method invocation or function call. The biggest issue in changing a monolith into microservices lies in changing the communication pattern. A naive conversion from in-memory method calls to RPC leads to chatty communications which don't perform well. Instead you need to replace the fine-grained communication with a coarser -grained approach.




