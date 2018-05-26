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
