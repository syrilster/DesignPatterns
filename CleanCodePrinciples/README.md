## Functions
* Should be small - Ideally 4 or 5 lines long !!
* Functions should do one thing. They should do it well. They should do it only.
* After all. the reason we write function is to decompose a larger concept into a set of steps at the next level of abstraction.
* **One level of abstraction per function** - we need to make sure that statements within our function are all at the same level of abstraction. Never mix concepts which are at a very high level of abstracion such as getHtml(); others that are low level, such as .append("\n").
* Mixing levels of abstraction within a function is always confusing. Readers may not be abke to tell whether a particular expression is an essential cincept or a detail.
* Use descriptive names - A long descriptive name is better than a long descriptive comment.

## Switch statements
* By their nature, switch statements always do N things.
* Make sure that each swicth statement is buried in a low-level class and is never repeated.
* The above can be achieved using polymorphism.
* General rule for switch statements is that they can be tolerated if they appear only once, are used to create polymorphic objects, and are hidden behind an inheritance relationship so that the rest of the system can't see them.
