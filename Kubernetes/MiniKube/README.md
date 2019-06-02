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
* Open Kubernetes dashboard using:
  ```
  minikube dashboard
  ```
* Instead of pushing your Docker image to a registry, you can simply build the image using the same Docker host as the Minikube VM, so that the images are automatically present. To do this we need to make sure we are using the Minikube Docker daemon, you can do this by running the following:
  ```
  eval $(minikube docker-env)
  ```
* Later, when you no longer wish to use the Minikube host, you can undo this change by running:
  ```
  eval $(minikube docker-env -u)
  ```
* Build a Docker image, using the Minikube Docker daemon. Now the Minikube VM can run the docker image you have built.
  ```
  docker build -t currency-exchange-service:v1 .
  ```
* To launch the service. This automatically opens up a browser window using a local IP address that serves your app endpoint
  ```
  minikube service currency-exchange-service
  ```
