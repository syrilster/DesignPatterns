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
    * This can be avoided by using HTTPS instead of HTTP and having session timeouts.
* **XSS - Cross Site Scripting** - Compromising the site by injecting custom javascript to change the behaviour. Like redirect the current user to a similar looking site, or adding custom CSS to manipulate the site behavior or in e-commerce site to increase a product rating etc.
    * Could be avoided by proper validation of the input to avoid scripts entered in free form text fields and by checking against a whitelist chars.
    * Proper encoding of the output.
* **Insecure Direct Object References** - A user can try to access a resource they are not supposed to access by guessing the next user id if this information is present in the URL. Example below a user can try by hitting the same URL with /MyProfile/4
    ```
    http://localhost:3421/Users/MyProfile/3
    ```
