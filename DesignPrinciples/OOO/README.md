* Inheritance: "IS-A" kind of a relationship. To reuse functionality across different classes.
* Interface: A java interface provides an abstraction. Implementations of this interface which do not exposed any other methods are employing information hiding; we cannot tell how they are implemented.
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
