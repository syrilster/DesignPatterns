## Fundamental principle of Unit Testing
* **Verify that a known, fixed input produces a known, fixed output.** Example when testing a function which calculates the square root, pass an input as 9 as everybody knows that the output should be 3. Don't try to pass a input as 2 as the output is in decimals and nobody is sure of it. (Atleast after 4 decimal points !!)
* Never generate random input. Always use fixed values. Because if your random input fails a test then that needs to be recorded for you to fix the test.
* Don't use constants from the Model code. Always use String literals in your tests. Because the constants in the model class can change and this will break the tests.
* Don't access the network and prefarably not the file system.
* Avoid testing things beyond the context of the UT. Example a timezone. Always inject this into the test.

## Three Laws of TDD
* Do not write a production code until you have written a failing unit test.
* You may not write more of a unit test than is sufficient to fail, and not compiling is failing.
* You may not write more production code than is sufficient to pass the currently failing test.

## Write your tests first
* It's not just about testing; It's about software development. Helps to think like a user of your library or class etc.
* TDD creates better API because it starts with the user than the used code.
* Test first hides the implementation and avoids exposing internal implementation details. It avoids tightly coupled tests.
* In the below example bug can be noticed if tests were written first. Here the test is not actually running as its not annotated using @Test. In TDD this would have been noticed immediately as the tests should fail first and in this case it will be running.
  ```
  public class ListTest {
    private List<String> list = new ArrayList<>();
    
    public void testAdd(){
      list.add("Foo");
      Assert.assertEquals(1, list.size());
    }
  }
  ```
