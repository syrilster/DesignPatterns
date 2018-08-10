## EJB
* Standardized way to implement solutions for a server side business logic.
* EJB's are POJOs, but managed by the EJB container.

## Types of Enterprise Java Beans
* **Session beans**, whether stateful or stateless, are not designed to be persistent. The data maintained by stateful session beans is intended to be transitional. It is used solely for a particular session with a particular client.
* Stateless session Bean - Contains bussiness logic but no client state.
* Stateful session Bean - Contains bussiness logic and maintains client state. Stateful session beans are by definition tied to a single client, so for each new client that requests its services, a new instance is created.
* Singleton session Bean - Instantiated once per application.
* Message Driven Bean - Listen for messages on a JMS queue or topic.

## Stateful vs Stateless beans (When to use)
* Stand-alone applications that don't have any internal state-management facilities are a good option for stateful session beans. Mobile devices with very limited client-side memory also create a compelling argument for using stateful session beans. Basically, if you are working with a client that does not have access to the HTTP protocol that allows state to be managed through an HttpSession, then stateful session beans are a good choice. 
* Stateless session beans are the component of choice for creating a front door to your back-end systems. When requests have been processed by the web layer, and back end resources are required, whether it's a matter of writing a message to a message queue, or persisting data using the Java Persistence API, the stateless session bean is the component that should orchestrate all of this.

## EJB specification details how an application server provides the following responsibilities:
* Transaction processing
* Integration with persistance API like JPA
* Concurrency Control
* Event driven programming using JMS and JCA (Java connector architecture)
* Asynchronous method invocation
* Security
* Deployment of software components in and application server

With EJB 3, dependency injection has greatly simplified accessing both EJB resources"such as JDBC DataSource, JMS Objects, and JPA Entity Manager"and services"such as Timer, User Transaction, and Web Services.

http://www.visionsdeveloper.com/blog/page/ejb3-vs-spring-framework.jsp


