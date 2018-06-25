## Some payara related insights

I found out that there are in fact 2 properties that limit the size of the POST data.

* max-post-size-bytes, can be configured also in admin console, setting it to -1 will remove size limits and accept any request
* max-form-post-size-bytes (as @figolino suggested)
this cannot be configured in Admin console, only via asadmin command
this is only being applied to requests with content type application/x-www-form-urlencoded, therefore it is ignored for most form file uploads (here is the code in Grizzly)
