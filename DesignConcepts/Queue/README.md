Queues are used to effectively manage requests in a large-scale distributed system. In small systems with minimal processing loads and small databases, writes can be predictably fast; however, in more complex and large systems writes can take an almost non-deterministically long time. For example, data may have to be written in different places on different servers or indices, or the system could simply be under high load. In such cases where individual writes (or tasks) may take a long time, achieving high performance and availability requires different components of the system to work in an asynchronous way; a common way to do that is with queues.

**JMS (ActiveMQ is a JMS broker implementation)** can be used as a mechanism to allow asynchronous request processing. You may wish to do this because the request take a long time to complete or because several parties may be interested in the actual request. Another reason for using it is to allow multiple clients (potentially written in different languages) to access information via JMS. ActiveMQ is a good example here because you can use the STOMP protocol to allow access from a C#/Java/Ruby client.

JNDI is a service that provides a set of objects to be used by application. This service is usually provided by application server or web server or a dedicated LDAP server. If the tutorial you are trying to follow explains the JMS tutorial in the context of web application, then most likely there are some setups to be done in the application server (e.g. Glassfish, JBoss) or web server (e.g. Tomcat). The way to access JNDI also depends on the provider. Usually, this involves a configuration file (either properties file, or XML file). Another alternative to use JMS is to utilize a dedicated JMS provider such as ActiveMQ. This way, you don't need any application server. Your application can just be a standalone java application (i.e. not necessarily a web application).

**Application server (domain.xml)**
```
<admin-object-resource res-adapter="jmsra" res-type="javax.jms.Queue" jndi-name="jms/NotificationMDB">
   <property name="Name" value="NotificationMDB"></property>
</admin-object-resource>

<servers>
    <server config-ref="server-config" name="server">
      <resource-ref ref="jdbc/dbresourcename"></resource-ref>
      <resource-ref ref="jms/NotificationMDB"></resource-ref>
    </server>
</servers>
```
**In session Bean (To create a connection and start using)**
```
@Resource(mappedName = "jms/QueueConnectionFactory")
private ConnectionFactory queueConnectionFactory;
@Resource(mappedName = "jms/PatientDirectoryMDB")
private Queue patientDirectoryMDB;
```
## Queue vs Topic

**Queue:**
* Point-to-point model
* Only one consumer gets the message
* Messages have to be delivered in the order sent
* A JMS queue only guarantees that each message is processed only once.
* The Queue knows who the consumer or the JMS client is. The destination is known.
* The JMS client (the consumer) does not have to be  active or connected to the queue all the time to receive or read the message.

**Topic:**
* Publish/subscribe model
* Multiple clients subscribe to the message
* There is no guarantee messages have to be delivered in the order sent
* There is no guarantees that each message is processed only once. -- As this can be sensed from the model 
* The Topic, have multiple subscribers and there is a chance that the topic does not know all the subscribers. The destination is unknown.
* The subscriber / JMS client needs to the active when the messages are produced by the producer, unless the subscription was a durable subscription. 

