## Webservices

* Web Services are client and server applications that communicate over the World Wide Web's (WWW) Hypertext Transfer Protocol (HTTP). As described by the World Wide Web Consortium (W3C), web services provide a standard means of interoperating between software applications running on a variety of platforms and frameworks.
* Web Services can be thought of as a code on demand. Just like we call functions and methods, web services can be looked upon as calling a function or method over the internet using some sort of protocols and some agreements.
* A web service is a function or method which we can call by sending an HTTP request to a URL, with arguments and the service returns the result back as response.
* The biggest advantage of the web services is that it is platform independent.

## SOAP Web Services
* SOAP is an XML-based protocol. The biggest advantage of using the SOAP Web Service is its own security. SOAP stands for Simple Object Access Protocol.
* SOAP provides an envelope to send a web services messages over the Internet, using the HTTP protocol. The messages are generally in XML format.
* Every application serving SOAP requests, has a WSDL file. WSDL is an XML, and it stands for Web Service Description Language. WSDL describes all the methods available in the web service, along with the request and response types. It describes the contract between service and client.
* SOAP was intended to be a way to do remote procedure calls to remote objects by sending XML over HTTP.

## REST Web Services
* REST stands for Representational State Transfer. REST is not a set of standards or rules, rather it is a **style of software architecture**. The applications which follow this architecture are referred to as RESTful.
* Unlike SOAP which targets the actions, REST concerns more on the resources. 
* REST locates the resources by using URL and it depends on the type of transport protocol(HTTP - GET, POST, PUT, DELETE etc) for the actions to be performed on the resources. 
* The REST service locates the resource based on the URL and performs the action based on the transport action verb. It is more of architectural style and conventions based.

For Example: In a RESTful architecture, this URL http://{serverAddress}/students/studentRollno/07 can be used to: Get student information by sending a REST call of GET type, and the service will return information of student with roll no as 07
The same service can also be used to update the student data, by sending in the new values as Form data in a PUT request.
