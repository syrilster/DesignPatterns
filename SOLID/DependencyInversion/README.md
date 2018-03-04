Entities must depend on abstractions not on concretions. It states that the high level modules must not depend on the low level module, but they should depend on abstractions (i.e 
it should depend on an interface)

![dependency inversion](https://user-images.githubusercontent.com/6800366/36943960-9bed7418-1fb8-11e8-80ca-b4f719165948.PNG)


* It is always suggested to communicate via Abstraction and not the real implementation
* Depending on the abstraction will help the Test/Mock classes to be independent of implementation.
* It is always east to fake/mock abstractions.
