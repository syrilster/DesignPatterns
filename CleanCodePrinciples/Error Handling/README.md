## Use Exceptions Rather than Return Codes
* Back in the distant past some languages did'nt have exceptions so they choose to have error flag or returned an error code.
    ```
    if(record.getStatus() != DEVICE_SUSPENDED){
        //Do something here
    } else {
        logger.log("Device Suspended.");
    }
    ```
* Above code clutter the caller. The caller must check for errors immediately after the call and its easy to forget.
* Throwing exceptions is much more easier and cleaner.

## Write your Try-Catch-Finally Statement First
* In a way, try blocks are like transactions. Your catch has to leave your program in a consistent state, no matter what happens in the try.
* It is a good practice to start with a try-catch-finally statement when you are writing code that could throw exceptions.
* This helps to define what the user of that code should expect, no matter what goes wrong with the code that is executed in the try.

```
@Test(expected = StorageException.class)
public void retrieveSectionShouldThrowOnInvalidFileName() {
    sectionStore.retrieveSection("invalid - file");
}
```

* Try to write tests like the above thats force exceptions, and then add behavior to your handler to satisfy your tests. This helps in TDD to build the logic we need.

## Use Unchecked Exceptions
* The debate is over. For years Java programmers have debated over the benefits and liabilities of checked exceptions.
* It is clear now that checked exceptins are'nt necessary for the production of robust software. C# does not have checked exceptions. Neither do C++, python and ruby.
* Checked exceptions is a violation of Open/Closed principle. Example: If you throw a checked exception from a method in your code and the catch is 3 levels above, you must declare that exception in the signature of each method between you and the catch.
* This means that a change at a low level of the software can force signature changes on many higher levels. The changed modules must be rebuilt and redeployed, even though nothing they care about changed.
* Encapsulation is broken because all functions in the path of a throw must know about details of that low-level exception.
* Checked exceptions can sometimes be useful if you are writing a critical library: you must catch them. But in general application development the dependency costs outweigh the benefits.

## Provide Context with Exceptions
* Each exception that you throw should provide enough context to determine the source and excat location of an error.
* Having stack trace is not just enough. Stack trace can't tell you the intent of the operation that failed.
* Create informative error messages and pass them along with your exceptions. Mention the operation that failed and the type of failure.

