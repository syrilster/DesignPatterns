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

For Example: In a RESTful architecture, the URL **http://{serverAddress}/students/studentRollno/07** can be used to: Get student information by sending a REST call of GET type, and the service will return information of student with roll no as 07
The same service can also be used to update the student data, by sending in the new values as Form data in a PUT request.

## Understanding HTTP: The Backbone of REST
* HTTP is all over the internet. Every time we hit a URL in our browser, an HTTP request is sent to the webserver and we receive the HTML content in response. An important thing to note here is that, while the website is usually for the human consumption the REST Api is usually for application consumption. Therefore, while requesting the data from a website, the data should be in a browser readable format, which is HTML, while in case of the REST API, response can be anything like XML/JSON or any other media type.
* Since REST is very much inspired by the HTTP, HTTP can be said as the backbone of the REST.
* HTTP is a TCP/IP based communication protocol, which is used to deliver data (HTML files, image files, query results etc.) accross the World Wide Web.
* One very important point about REST is, that it is not connected to web, and will return no response when tried to access via a browser, although it is based on HTTP.

**The basic features of HTTP are:**

* HTTP is connectionless.
* HTTP is media independent, which means any type of data can be sent through the http.
* HTTP is stateless, neither the server nor the client keeps a track of the last request.
* HTTP makes use of the Uniform Resource Identifier (URI) to identify any given resource and establish a connection. HTTP request and response, use a generic message format of RFC 822 for transferring the data.

**The generic message of any HTTP request and response, consists of the following four items:**

* A start line
* Zero or more Headers
* Empty line indicating the end of the header fields
* Optional message Body.

## HTTP Methods/Verbs
* The most important part of a request is the HTTP Method (verbs). These methods tells the server what to do with the data received in the request. 
* Although there are 8 different types of methods but the 4 most important HTTP methods will be discussed here which are equivalent to the CRUD operations. 

**GET**

The GET method is used to get the information from the server. This is equivalent to the SELECT statement in SQL. There is no message body in the GET request as it is meant to only fetch the data from the server. This is the simplest type, and is used by the browser every time you visit any link, to fetch the data. A GET request should never change the data on server, and should only be used to read data.

GET /students: Returns the students data.

**POST**

This is used to send the data to the server. This can be students information, some XML file or some JPG file. The message body contains the data. POST requests are generally used to create but it can be used to update the existing data as well.

POST /students

The above should be able to create a new student entry, provided student data is sent in appropriate format in the message body. This can be used to update student data as well.

**PUT**

This should be used when we have to replace/update/create the data, which is already present/not present on the server with the data that we pass in the message body.

**DELETE**

This should be used to delete the data on the server. For example, the following URL when sent a DELETE request, should delete the student entry.

DELETE /students/syril

## HATEOAS - Important Concept For Designing
* HATEOAS stands for Hypertext As The Engine Of Application State. 
* A Rest API is said to be perfect if it doesn't need any documentation at all. It should be so crystal clear to the developer who is consuming the service that he/she should not even refer to the documentation. It should be designed like a website, like once we go to the Home page we can navigate to the different resources with the help of hyperlinks.

**How do we achieve this?**
The simple answer is providing as much related links as possible about the resource in the response. Lets take the case of our registration application.

The typical response of the students resource /myApp/students/1:
```
<Student>
    <rollno>10</rollno>
    <firstName>Amit</firstName >
    <lastName>Agarwal</lastName>
    <age>25</age>
</Student>
```
What if we give the additional information to the client, about how to check all the courses that this student (with the given roll no.) has enrolled into.
```
<Student>
    <rollno>10</rollno>
    <firstName>Amit</firstName >
    <lastName>Agarwal</lastName>
    <age>25</age>
    <link rel = “courses” href =”/myApp/students/1/registrations”>
</Student>
```
Now this will be very useful to the client as he will directly get the courses enrolled resource URI information from the students response itself. This is a HATEAOS concept. We can also include more options like, URI to Update student information, Delete the student data etc.

So HATEOAS is a concept to provide links of the related sub resources to the resource which is requested by the client so that is becomes easier for the client to make further calls to the REST API.

## Richardson Maturity Model
* Since REST is just a specification or a way of implementing web services, with no hard coded documentation, how do we know if the API which we have developed is fully RESTful. For this, there is Richardson Maturity Model, which is usually referred to analyse how RESTful the API is.
* **The Richardson Model** is a way to grade your API according to the constraints of the REST. The more the API follows these constraints the more RESTful the API is.
* The Richardson Maturity Model has 4 levels numbered from 0 to 3. Level 0 is not RESTful while Level 3 means your API is fully RESTful.

## Level 3
* This level suggests to use the concept of HATEOAS. The response should contain the logical links of all the resources which the current resource is related to.
* For Example: In case of when the client request for the student information with the roll number 1, then in the response along with the response of the student, a link of all the URI of all the courses which that student has registered should also be sent.
* If an API is at the level 3 this will be known as fully RESTful. This Richardson Model should be used while designing the REST API to make sure the web services are fully RESTful.


