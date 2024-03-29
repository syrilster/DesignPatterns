**Horizontal scaling:** Add or remove EC2 servers from a fleet of servers that are supporting an application. For this use the Autoscaling group and cloud watch metrics to execute an autoscaling policy.

![ec2 autoscaling](https://user-images.githubusercontent.com/6800366/37917259-8ba1fc16-313b-11e8-8917-06ebf05cad90.PNG)

As show above Cloud watch health check, AutoScaling and ELB work together to create Elasticity in the autoscaling environment.

## Elastic Load Balancers
* Classic Load Balancer - Basic Load balancing at request and connection level.
* Application Load Balancer - Best suited for load balancing HTTP and HTTPS traffic and it provides advanced request routing of modern appliaction architectures, including microservices and containers.
* Network Load Balancers - Best suited for load balancing of TCP traffic where extreme performance is required.

## How does Auto Scaling work 

* **Launch configuration (what?)** - What needs to be launched? An AMI or and EC2 instance etc.
* **Auto Scaling Group (where?)** - Where to launch the group. Like AZ, subnet etc.
* **Auto Scaling policy (When?)** - Specifies when to increase or decrease EC2 instances based on cloud watch alarms. Example: Based on CPU utilization.
* A schedule action - Tells Auto Scaling to perform a scaling action at a certain time in the future. Example a black friday sale on Amazon website.

## Auto Scaling Groups

* Contain a collection of EC2 instances that share similar characteristics.
* Instances in an Auto Scaling group are treated as a logical grouping for the purpose of instance scaling and management.
* Minimum Size: Smallest number of EC2's that this group should never go below.
* Desired Capacity: The ideal EC2 instances that we would like to run at any point in time.
* Maximum Size: This should always be set to protect from having too many EC2 instances spun up erroneously.
* Scaling out as and when required based on cloud watch metrics. This will never go beyond the maximum size defined.

## Auto Scaling basic lifecycle

Launching an EC2 instance into AS group can be because of the following:

* Triggered due to a cloud watch metric.
* User Scheduled Event i.e. predicted scaling. Example a black friday sale.

**Load Balancer Configurations**

* LB will have a listener which is default. Example a HTTP port 80 listener forwarding the request to a target group and it can also have aditional listeners to have special conditional logic. i.e. domain based routing to a different target group.
* Can have the Availability Zone configured. i.e. Load shared between these configured AZ for high availability.
* Security Group to restrict access.
* Target group are just simple EC2 instances to serve the incoming requests.

**Auto Scaling Group configs**

* Launch configuration(what part): Mention AMI or EC2 instance type, AZ, Security Group,IAM role, custom scripts etc.
* Autoscaling groups(where and when parts)
* Scaling policies: Reactive actions to be done when a cloud watch metric is met.
* Create Scheduled Action - For configuring the black friday sale.

## Amazon ECS (Elastic Container Service)
* Fully managed container orchestration service to manage containers in AWS.
* Amazon ECS for Kubernetes - For running and building kuberbetes infrastructure
* Amazon Elastic Container Registery - Fully managed docker container registery to store, manage and deploy docker containers.
* AWS Fargate - For deploying and managing without any configs for container management. i.e. Simply upload the container image and Fargate will manage everything.

## AWS lambda
* Serverless. Pay only for the compute time.
* Zero administration. Focus in only on writing the code.
* Can specify auto trigger other AWS services like S3







