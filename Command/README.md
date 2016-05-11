---
layout: pattern
title: Command
categories: Behavioral
tags: 
 - Java
 - Gang Of Four
---

Command Pattern is one of the Behavioral Design Pattern and it’s used to implement lose coupling in a request-response model. In command pattern, the request is send to the invoker and invoker pass it to the encapsulated command object. 

Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

## Typical Use Case

* to keep a history of requests
* implement callback functionality
* implement the undo functionality

## Real world examples

* [java.lang.Runnable](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)
* [Netflix Hystrix](https://github.com/Netflix/Hystrix/wiki)
