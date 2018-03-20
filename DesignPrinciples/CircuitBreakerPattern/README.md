## The problem
* In a distributed environment, calls to remote resources and services can fail due to transient faults, such as slow network connections, timeouts, or the resources being overcommitted or temporarily unavailable. These faults typically correct themselves after a short period of time, and a robust cloud application should be prepared to handle them by using a strategy such as the Retry pattern.
* However, there can also be situations where faults are due to unanticipated events, and that might take much longer to fix. These faults can range in severity from a partial loss of connectivity to the complete failure of a service. In these situations it might be pointless for an application to continually retry an operation that is unlikely to succeed, and instead the application should quickly accept that the operation has failed and handle this failure accordingly.
* In the case of cloud services, the goal is to allow a cloud application to continue to function to the degree possible when a service outage occurs in only part of the application. Achieving this goal becomes more complicated as complex applications are built by using many microservices.

* The Circuit Breaker pattern is a framework that provides a graceful degradation of service rather than a total service failure.

## Why use the Circuit Breaker pattern?
* The Circuit Breaker pattern, popularized by Michael Nygard in his book, Release It!, can prevent an application from repeatedly trying to execute an operation that's likely to fail. Allowing it to continue without waiting for the fault to be fixed or wasting CPU cycles while it determines that the fault is long lasting. The Circuit Breaker pattern also enables an application to detect whether the fault has been resolved. If the problem appears to have been fixed, the application can try to invoke the operation.

* When a development team uses the Circuit Breaker pattern, they can focus on what to do when a dependency is unavailable, instead of simply detecting and managing failures. For example, if a team is developing a website page and retrieves content from ContentMicroservice for a single widget on the page, they can make the page available but provide no content for the widget when ContentMicroservice is unavailable. The page can continue to function without the failing service.

* Teams can use the pattern to minimize damage when only part of the application is down. The pattern also helps teams decide which actions to take for failures in dependent microservices.

## Circuit breaker vs Retry pattern

The purpose of the Circuit Breaker pattern is different than the Retry pattern. The Retry pattern enables an application to retry an operation in the expectation that it'll succeed. The Circuit Breaker pattern prevents an application from performing an operation that is likely to fail. An application can combine these two patterns by using the Retry pattern to invoke an operation through a circuit breaker. However, the retry logic should be sensitive to any exceptions returned by the circuit breaker and abandon retry attempts if the circuit breaker indicates that a fault is not transient.

## Principles of Resiliency
Here are some of the key principles that informed our thinking as we set out to make the API more resilient.

* A failure in a service dependency should not break the user experience for members
* The API should automatically take corrective action when one of its service dependencies fails
* The API should be able to show us what’s happening right now, in addition to what was happening 15–30 minutes ago, yesterday, last week, etc

## When to use this pattern

**Use this pattern:**
* To prevent an application from trying to invoke a remote service or access a shared resource if this operation is highly likely to fail.

**This pattern isn't recommended:**

* For handling access to local private resources in an application, such as in-memory data structure. In this environment, using a circuit breaker would add overhead to your system.
* As a substitute for handling exceptions in the business logic of your applications.

## Implementing the Circuit Breaker pattern
To implement the Circuit Breaker pattern, each invocation of a remote service requires the caller to extend an abstract class. The class provides the logic to manage the execution of the action and call a fallback method when the service is unavailable.

The method can respond to the failure in several ways:

* **Fail silently:** Return null or an empty set. In the earlier example, this method would work.

* **Fail quickly:** Throw an exception. For example, if an authentication service is unavailable, no new users can log in to the application. However, anyone who was already logged in can continue to use the application.

* **Best effort:** Return a close approximation of the requested data. For example, if a cached copy of the requested data exists, that copy can be used instead of data from the remote service if that service fails. In this way, users can still proceed.

The Circuit Breaker framework monitors communications between the services and provides quality of service analysis on each circuit through a health monitor. Teams can define criteria to designate when outbound requests will no longer go to a failing service but will instead be routed to the fallback method. Criteria can include success/failure ratios, latent response ratios, and pool size. The fallback method is called until the failing service is functional again.

## Additional Reads
https://medium.com/netflix-techblog/fault-tolerance-in-a-high-volume-distributed-system-91ab4faae74a
