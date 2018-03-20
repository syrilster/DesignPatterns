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
