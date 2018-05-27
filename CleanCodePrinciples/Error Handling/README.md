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
