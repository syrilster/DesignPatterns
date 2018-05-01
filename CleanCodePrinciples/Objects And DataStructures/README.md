## Data Abstraction
* Hiding implementation is all about abstractions.
* A class must not simply push its variables out through getters and setters. Rather it exposes abstract interfaces that allow its users to maipulate the essence of data, 
without having to know the implementation.

Example:
```
public interface Vehicle {
	double getFuelTankCapacityInGallons();
	double getGallonsOfGasoline();
}
```
```
public interface Vehicle {
	double getPercentFuelRemaining();
}
```

Second one is preferrable as we dont want to expose details of data. Rather we want to express our data in abstract terms.
