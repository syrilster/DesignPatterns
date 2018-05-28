## JMS
* Java Message service - An interface to send messages.
* Message oriented middleware - MOM
* Producer consumer in async way.
* Queue and topic are destinations.
* Queue for delivery to one and only one consumer.
* Topic - Subscription model. All consumers subscribed to a topic get this message.
* Two models
    * point-to-point. (One to One Queue)
    * publish/subscribe (Topic)

More about Queue here.

https://github.com/syrilster/DesignPatterns/tree/94e9c0d0f537709ff175a9c1bbff0d5e90ea87ad/DesignPrinciples/Queue

## More details
* JMS connection factory as JNDI configuration in glassfish.
* Destination resources also need to be registered.
