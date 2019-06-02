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
* This will output the below:
  ```
  Kubernetes master is running at https://192.168.99.102:8443
  KubeDNS is running at https://192.168.99.102:8443/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy
  ```
