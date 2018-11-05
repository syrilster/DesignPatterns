## OWASP
* The Open Web Application Security Project.
* A online community which produces freely-available articles, methodologies, documentation, tools, and technologies 
in the field of web application security.
* To make sure that the internet is a safer place for all of us.

## Top Vulnerabilities
* **Injection** - Using SQL injection or XML fragment injection. Example: Adding a ' to end of the URL will end the SQL statement as a invalid input and sometimes the query used will be displayed on the screen if exceptions arent well handled. The below will run an update statement on the products table.
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
* **Insecure Direct Object References** - A user can try to access a resource they are not supposed to access by guessing the next user id if this information is present in the URL. Example below a user can try by hitting the same URL with /MyProfile/4 to get access to an other account.
    ```
    http://localhost:3421/Users/MyProfile/3
    ```
    * Can be avoided by having correct authentication and authorization mechanisms.
*  **Security Misconfiguration** - If some security settings were turned off and if it was not enabled again this can be a potential issue as users can gain access to critical server variables or some cross site scripting settings etc.
* **Sensitive data Exposure** - This is not just about displaying sesitive content on the UI but also about how it is actually stored in DB. For example: passwords and credit card information should always be encrypted using reputed Algorithm along with the introduction of simple hashing and salting techniques to make it difficult for an hacker to crack. 
  * If the data is not encrypted and a hacker gets hold of a **SQL backup file**, the password and credit card information can be compromised. So the hacker does not necessarily need to have access to SQL server itself.
* **Missing function level access control** - Attackers might be able to guess the API URL and this should not expose any critical functionality. Disabling this option on the UI itself is not enough. For example: Appending /Edit or such actions should not lead to the actual edit page itself for an un authorized user.
* **Cross Site Request Forgery** - Cross-Site Request Forgery (CSRF) is an attack that forces an end user to execute unwanted actions on a web application in which they're currently authenticated. 
  * CSRF attacks specifically target state-changing requests, not theft of data, since the attacker has no way to see the response to the forged request. 
  * With a little help of social engineering (such as sending a link via email or chat), an attacker may trick the users of a web application into executing actions of the attacker's choosing. 
  * If the victim is a normal user, a successful CSRF attack can force the user to perform state changing requests like transferring funds, changing their email address, and so forth. If the victim is an administrative account, CSRF can compromise the entire web application.
* **Using components with known exploits in action** - Products and framweorks used in the application development can have security loopholes. This should be know in prior and custom actions/solutions should be developed to avoid malicious activities.
* **Unvalidated redirects and forwards in action** -  A Site may be vulnerable to attackers putting in different redirect URL's to access restricted pages or redirect a user to a different website. Example: A US government website was hacked and the users were redirected to a fake site using URL shorterner service by making it look like a legitimate site.
  * This risk can be mitigated by validating the redirected URL before the actual redirection itself.
  * By passing a token which has a list of URL's relative to the current website and not the full URL.
