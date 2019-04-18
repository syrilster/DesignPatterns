## what is kubernetes? 
* Container orchestrator.
* Groups containers that make up an application into logical units for easy management and discovery.

## How powerful is kuberneteses (K8)
* Horizontal scaling - scale containers automatically
* Self healing - Restarts the containers, replaces the containers when a node dies and automatically kills old containers which do not respond to user queries. Also has configurable health checks.
* Load balancing and self discovery - No need for the user to modify code/configs for the service discovery as K8 assigns ip address for containers and single DNS for a set of containers so that they can be load balanced.
* Automated rollouts and rollback - Progressive changes to application by K8 via health checks and monitoring to make sure high availability of the application.
* Storage Orchestration - Can automatically mount storage systems based on user choice be it local or a cloud EBS of AWS or any other network storage.
* Batch execution - CI workloads i.e replace failed containers.
* Configuration management - Deploy containers without rebuilding the image via custom configs.

# Deploying spring boot applications in Kubernetes (GCP)
* Refer: https://cloud.google.com/kubernetes-engine/docs/tutorials/hello-app
* Create a container cluster using below command:
    ```
    gcloud container clusters create currency-conversion-service --num-nodes=2
    ```
