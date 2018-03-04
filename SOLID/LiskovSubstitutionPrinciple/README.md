## Derived classes must be usable through the base class interface without the need for the user to know the difference.

![liskov](https://user-images.githubusercontent.com/6800366/36943799-8b5a7a5e-1fb5-11e8-8d06-215172e3f0dc.PNG)


* So according to LSP, ostrich should never have derived from the bird base class. Instead it should have derived 
from a base class called NonFlyingBird so that the Penguin could also inherit it.

## Note: We can make changes to the Bird base class in order to accommodate the ostrich. But that is going to violate the OCP 
principle.
