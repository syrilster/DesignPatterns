## OWASP
* The Open Web Application Security Project.
* A online community which produces freely-available articles, methodologies, documentation, tools, and technologies 
in the field of web application security.
* To make sure that the internet is a safer place for all of us.

## Top Vulnerabilities
* **Injection** - Using SQL injection or XML fragment injection. Example: Adding a ' to end of the URL will end the SQL statement being a invalid input and sometimes the query used will be displayed on the screen if exceptions arent well handled. The below will run a update statement on the products table.
  ```
  http://localhost:3421/products/Search/a';UPDATE products set price = 1 where ID = 1;--
  ```
* **Broken Authentication/Session Management**
    * Passwords stored in DB should always be encoded.
    * Also a user might pass various different input to find out the encoding logic itself. This can be avoided by introducing randmoness using salting.
    * A session can be hijacked by an other user using the cookie information and requesting the request again to gain access.
