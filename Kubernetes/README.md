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
# Deploying a containerized web application
* Refer: https://cloud.google.com/kubernetes-engine/docs/tutorials/hello-app
* This shows you how to package a web application in a Docker container image, and run that container image on a Google Kubernetes Engine cluster as a load-balanced set of replicas that can scale to the needs of your users.
# Deploying spring boot application in GKE using yaml
* Create a container cluster using below command:
    ```
    gcloud container clusters create currency-conversion-service --num-nodes=2
    ```
* Prepare a yaml file for application deployment like below:
    ```
    apiVersion: "extensions/v1beta1"
    kind: "Deployment"
    metadata:
      name: "currency-conversion-app"
      namespace: "default"
      labels:
        app: "currency-conversion-app"
    spec:
      replicas: 2
      selector:
        matchLabels:
          app: "currency-conversion-app"
      template:
        metadata:
          labels:
            app: "currency-conversion-app"
        spec:
          containers:
          - name: "ccs-sha256"
            image: "gcr.io/spring-boot-projects/ccs@sha256:01c840326017dde6da713009ee3cb7f3bcf492a3a5f7dfcdd3b9f2599578b808"
    ```
* To apply the deployment using the yaml file:
    ```
    kubectl apply -f ces-deployment.yaml
    ```
* To check the deployments
    ```
    kubectl get deployments
    NAME                DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
    currency-exchange   1         1         1            1           2h
    ```
* Expose the deployed app as a service using yaml
    ```
    apiVersion: "v1"
    kind: "Service"
    metadata:
      name: "currency-exchange-service"
      namespace: "default"
      labels:
        app: "currency-exchange"
    spec:
      ports:
      - protocol: "TCP"
        port: 8000
      selector:
        app: "currency-exchange"
      type: "NodePort"     
    ```
* Expose the deployed app with a LB to public internet using yaml
    ```
    apiVersion: "v1"
    kind: "Service"
    metadata:
      name: "currency-conversion-app-service"
      namespace: "default"
      labels:
        app: "currency-conversion-app"
    spec:
      ports:
      - protocol: "TCP"
        port: 8100
        targetPort: 8100
      selector:
        app: "currency-conversion-app"
      type: "LoadBalancer"
      loadBalancerIP: ""
    ```
# Errors:
* Forbidden!Configured service account doesn't have access. Service account may have been revoked. endpoints "hello-service" is forbidden: https://github.com/fabric8io/fabric8/issues/6840
