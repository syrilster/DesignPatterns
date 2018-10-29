## Fundamental principle of Unit Testing
* **Verify that a known, fixed input produces a known, fixed output.** Example when testing a function which calculates the square root, pass an input as 9 as everybody knows that the output should be 3. Don't try to pass a input as 2 as the output is in decimals and nobody is sure of it. (Atleast after 4 decimal points !!)
* Never generate random input. Always use fixed values. Because if your random input fails a test then that needs to be recorded for you to fix the test.
* Don't use constants from the Model code. Always use String literals in your tests. Because the constants in the model class can change and this will break the tests.
* Don't access the network and prefarably not the file system.
* Avoid testing things beyond the context of the UT. Example a timezone. Always inject this into the test.

## Three Laws of TDD
* 
