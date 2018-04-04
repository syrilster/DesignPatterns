![zuul design](https://user-images.githubusercontent.com/6800366/38312769-36ac2752-3840-11e8-8c29-7f3f8f7b5162.PNG)

You can only throttle for so  long. After some time CPU is going to max out, couple of instances are going to fall over and the remaining servers are taking way more traffic.

## What is Non-Blocking
* Non-Blocking I/O primitives - Need to know when a socket is ready to write to or read from. For Example java has NIO
* Asynchronous Programming - To be able to handle deffered execution. For example Queues.
* Event-loop pattern - A webserv which should be able to support both of the above. For example : **Netty** is a NIO client server framework which enables quick and easy development of network applications such as protocol servers and clients. It greatly simplifies and streamlines network programming such as TCP and UDP socket server.

![event loop](https://user-images.githubusercontent.com/6800366/38316528-90962756-3848-11e8-99b1-998ad71f3d20.PNG)


## What changes?

Changing the Filter interface of tomcat or netty from: 
* apply(I message) to Observable<T> applyAsync(I message)
  
![filter chain changes](https://user-images.githubusercontent.com/6800366/38316696-eaf8401c-3848-11e8-9681-c919505d38d8.PNG)

## Additional Information

https://www.youtube.com/watch?v=2oXqbLhMS_A&feature=youtu.be

