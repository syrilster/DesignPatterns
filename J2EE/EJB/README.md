## EJB
* Standardized way to implement solutions for a server side business logic.
* EJB's are POJOs, but managed by the EJB container.

## Types of Enterprise Java Beans
* Stateless session Bean - Contains bussiness logic but no client state.
* Stateful session Bean - Contains bussiness logic and maintains client state.
* Singleton session Bean - Instantiated once per application.
* Message Driven Bean - Listen for messages on a JMS queue or topic.


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


