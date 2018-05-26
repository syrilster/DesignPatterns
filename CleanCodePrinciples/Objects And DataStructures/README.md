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

## The Law of Demeter
* There is a well know heuristic called the law of Demeter that says a module should not know about the innards of the objects it manipulates.
* This means that an object should not expose its internal structure through accessors because to do so is to ecpose, rather than to hide, its internal structure.
* Example: This code violates the law of demeter: final String outputdir = ctxt.getOptions().getScratchDir().getAbsolutePath();
* Above code is often called code wrecks as it chains method calls and this is usually considered sloppy style and should be avoided. It is usually better to split it as below:

```
Options opts = ctxt.getOptions();
File scratchDir = opts.getScratchDir();
final String outputDir = scratchDir.getAbsolutePath();
```
* Whether this is a violation of Demeter depends on whether or not ctxt, options and scratchDir are objects or datastructure. If they are objects, then their internal structure should be hidden rather than exposed, and so the knowledge of the innards is a clear violation of the law of Demeter.
* Its not a violation if the code is like below as its a datastructure

```
final String outputDir = ctxt.options.scratchDir.absolutePath;
```
