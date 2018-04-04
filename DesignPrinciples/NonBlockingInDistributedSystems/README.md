![zuul design](https://user-images.githubusercontent.com/6800366/38312769-36ac2752-3840-11e8-8c29-7f3f8f7b5162.PNG)

You can only throttle for so  long. After some time CPU is going to max out, couple of instances are going to fall over and the remaining servers are taking way more traffic.

## What is Non-Blocking
* Non-Blocking I/O primitives - Need to know when a socket is ready to write to or read from. For Example java has NIO
* Asynchronous Programming - To be able to handle deffered execution. For example Queues.
* Event-loop pattern - A webserv which should be able to support both of the above. For example : **Netty** is a NIO client server framework which enables quick and easy development of network applications such as protocol servers and clients. It greatly simplifies and streamlines network programming such as TCP and UDP socket server.
