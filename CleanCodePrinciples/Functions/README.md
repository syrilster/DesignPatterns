## Functions
* Should be small - Ideally 4 or 5 lines long !!
* Functions should do one thing. They should do it well. They should do it only.
* After all. the reason we write function is to decompose a larger concept into a set of steps at the next level of abstraction.
* **One level of abstraction per function** - we need to make sure that statements within our function are all at the same level of abstraction. Never mix concepts which are at a very high level of abstracion such as getHtml(); others that are low level, such as .append("\n").
* Mixing levels of abstraction within a function is always confusing. Readers may not be abke to tell whether a particular statement is an essential concept or a detail.
* Use descriptive names - A long descriptive name is better than a long descriptive comment.

## Switch statements
* By their nature, switch statements always do N things.
* Make sure that each swicth statement is buried in a low-level class and is never repeated.
* The above can be achieved using polymorphism.
* General rule for switch statements is that they can be tolerated if they appear only once, are used to create polymorphic objects, and are hidden behind an inheritance relationship so that the rest of the system can't see them.

## Function Arguments
* The ideal number of arguments for a function is zero (niladic).
* Next comes one (monadic), followed closely by two (dyadic).
* Three arguments should be be avoided where possible. More than three requires special justification and shouldnt be used anyway.
* Arguments are harder from a testing point of view. Imagine the difficulty of writing all the test cases to ensure that all the various combinations of arguments work properly.
* If there are no arguments, this is trivial.
* **Flag Arguments**: Passing a boolean into a function is truly terrible practice. It immediately complicates the signature of the method, loudly proclaiming that this function does more than one thing. It does one thing if the flag is true and another if the flag is false.

## Command Query Seperation
* Functions should either do something or answer something, but not both.
* A function --> change state of an object or return some information about that object.
* Prefer Exceptions to returning error codes: Returning error codes from command functions is subtle violation of command query seperation.
* Error handling is one thing: Functions should do one thing. Error handling is one thing. Thus, a function that handles errors should do nothing else.

## Extract Try/Catch Blocks
Try/Catch blocks are ugly and they confuse the structure of the code and mix error processing with normal processing. In the below snippet, delete function is all about error processing.

```
public void delete(Page page) {
  try {
    deletePageAndAllReferences(page);
  } catch(Exception e) {
    logError(e);
  }
}

private void deletePageAndAllReferences(Page page) throws Exception {
// Actual deletion logic here
}

private void logError(Exception e) {
  logger.log(e.getMessage());
}

```
