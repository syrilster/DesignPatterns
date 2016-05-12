---
layout: pattern
title: Factory Method
categories: Creational
tags: 
 - Java
 - Gang Of Four
---

## Also known as
Virtual Constructor

## Intent

Factory Pattern is one of the Creational Design pattern and it’s widely used in JDK as well as frameworks like Spring and Struts.

Factory design pattern is used when we have a super class with multiple sub-classes and based on input, we need to return one of the sub-class. This pattern take out the responsibility of instantiation of a class from client program to the factory class

---
layout: pattern
title: Abstract Factory
categories: Creational
tags: 
 - Java
 - Gang Of Four
---

## Intent
Abstract Factory is one of the Creational pattern and almost similar to Factory Pattern except the fact that its more like factory of factories.

If you are familiar with factory design pattern in java, you will notice that we have a single Factory class that returns the different sub-classes based on the input provided and factory class uses if-else or switch statement to achieve this.

In Abstract Factory pattern, we get rid of if-else block and have a factory class for each sub-class and then an Abstract Factory class that will return the sub-class based on the input factory class. At first it seems confusing but once you see the implementation, its really easy to grasp and understand the minor difference between Factory and Abstract Factory pattern.
