Objects or entities should be open for extension, but closed for modification. This simply means that a class should 
be easily extendable without modifying the class itself.

![openclosed](https://user-images.githubusercontent.com/6800366/36943660-cc5095d2-1fb2-11e8-89bc-81e5d57841a9.PNG)


```
void drawShapes(ShapeType shape[], int n) {
    int i;
    for (int i = 0; i < n; i++) {
        ShapeType s = shape[i];
        switch (s.isType()) {
            case square:
                drawSquare();
                break;
            case circle:
                drawCircle();
                break;
        }
    }
}

```
Problems with above code: 
* Having a switch class does not allow for the flexibilty to package this class seperately.
* Code needs a re compilation when a new ShapeType is added.

This can be re-factored as follows:

```
Interface Shape {
    public void draw();
}

Class square implements Shape {
    public void draw(){
        // Draw logic here
    }
}

Class circle implements Shape {
    public void draw(){
        // Draw logic here
    }
}

public void drawAllShapes() {
    for(int i=0; i<n; i++){
        list[i].draw();
    }
}

```



