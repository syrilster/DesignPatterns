## Inheritance
* "IS-A" kind of a relationship. To reuse functionality across different classes.
* **Interface:** A java interface provides an abstraction. Implementations of this interface which do not exposed any other methods are employing information hiding; we cannot tell how they are implemented.
    Using an interface we can define the specification which can be implemented later. Example: JPA is an interface and hibernate or eclipselink will implement it in their own way.

## Polymorphism
The classic example is the Shape class and all the classes that can inherit from it (square, circle, dodecahedron, irregular polygon, splat and so on).

With polymorphism, each of these classes will have different underlying data. A point shape needs only two co-ordinates (assuming it's in a two-dimensional space of course). A circle needs a center and radius. A square or rectangle needs two co-ordinates for the top left and bottom right corners and (possibly) a rotation. An irregular polygon needs a series of lines.

By making the class responsible for its code as well as its data, you can achieve polymorphism. In this example, every class would have its own Draw() function and the client code could simply do:

```
Shape shape = new Circle();
shape.Draw() --> to get the correct behavior for any shape.
```

This is in contrast to the old way of doing things in which the code was separate from the data, and you would have had functions such as drawSquare() and drawCircle().

## Encapsulation
* Encapsulation, strictly speaking, means something different; it means collecting a bunch of stuff together and putting it in one box, or capsule. The box may or may not have opaque walls, so this may or may not involve information hiding. In practice a "class" will both encapsulate (ie bundle code and data together) and hide information (namely, implementation detail), and some people get so used to doing both at once they no longer bother to distinguish. Example: access modifiers can be public, protected or private.
* If encapsulation was the same thing as information hiding," then one might make the argument that "everything that was encapsulated was also hidden. This is obviously not true. For example, even though information may be encapsulated within record structures and arrays, this information is usually not hidden (unless hidden via some other mechanism).

## Abstraction
* An abstraction denotes the essential characteristics of an object that distinguish it from all other kinds of object and thus provide crisply defined conceptual boundaries, relative to the perspective of the viewer.
*  Abstraction is most often used as a complexity mastering technique. For example, we often hear people say such things as: "just give me the highlights" or "just the facts, please." What these people are asking for are abstractions.

## Coupling and Cohesion
* Coupling between modules/components is their degree of mutual interdependence; lower coupling is better.
* Cohesion of a single module/component is the degree to which its responsibilities form a meaningful unit; higher cohesion is better. Single Responsibility principle.

## Inheritance vs Composition

**Disadvantages of Inheritance:**

* Can't change the implementation inherited from super classes at runtime (obviously because inheritance is defined at compile time).
* Inheritance exposes a subclass to details of its parent's class implementation, that's why it's often said that inheritance breaks encapsulation (in a sense that you really need to focus on interfaces only not implementation, so reusing by sub classing is not always preferred).
* The tight coupling provided by inheritance makes the implementation of a subclass very bound up with the implementation of a super class that any change in the parent implementation will force the sub class to change.
* Excessive reusing by sub-classing can make the inheritance stack very deep and very confusing too.
* On the other hand **Object composition** is defined at runtime through objects acquiring references to other objects. In such a case these objects will never be able to reach each-other's protected data (no encapsulation break) and will be forced to respect each other's interface. And in this case also, implementation dependencies will be a lot less than in case of inheritance.

