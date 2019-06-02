# Minikube & Docker Container Images, for testing Kubernetes locally on Mac.
* Install minikube, kubectl-cli on Mac using brew
* Start minikube using:
  ```
  minikube start — vm-driver=hyperkit
  ```
* To set the context to use minikube we will use the following command:
  ```
  kubectl config use-context minikube
  ```
* verify that kubectl is configured to communicate with your cluster:
  ```
  kubectl cluster-info
  ```
