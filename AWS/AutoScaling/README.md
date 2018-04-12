**Horizontal scaling:** Add or remove EC2 servers from a fleet of servers that are supporting an application. For this use the Autoscaling group and cloud watch metrics to execute an autoscaling policy.

![ec2 autoscaling](https://user-images.githubusercontent.com/6800366/37917259-8ba1fc16-313b-11e8-8917-06ebf05cad90.PNG)

As show above Cloud watch health check, AutoScaling and ELB work together to create Elasticity in the autoscaling environment.

## Elastic Load Balancers
* Classic Load Balancer - Basic Load balancing at request and connection level.
* Application Load Balancer - Best suited for load balancing HTTP and HTTPS traffic and it provides advanced request routing of modern appliaction architectures, including microservices and containers.
* Network Load Balancers - Best suited for load balancing of TCP traffic where extreme performance is required.

## How does Auto Scaling work 

* **Launch configuration(what?)** - What needs to be launched? An AMI or and EC2 instance etc.
* **Auto Scaling Group(where?)** - Where to launch the group. Like AZ, subnet etc.
* **Auto Scaling policy(When?)** - Specifies when to increase or decrease EC2 instances based on cloud watch alarms. Example: Based on CPU utilization.
* A schedule action - Tells Auto Scaling to perform a scaling action at a certain time in the future. Example a black friday sale on Amazon website.

**Auto Scaling Groups**  - 
