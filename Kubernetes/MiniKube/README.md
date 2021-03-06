# Minikube & Docker Container Images, for testing Kubernetes locally on Mac.
* Install minikube, kubectl-cli on Mac using brew
* Start minikube using:
  ```
  minikube start --vm-driver=none --bootstrapper=kubeadm --extra-config=apiserver.authorization-mode=RBAC
  ```
* To set the context to use minikube we will use the following command:
  ```
  kubectl config use-context minikube
  ```
* verify that kubectl is configured to communicate with your cluster:
  ```
  kubectl cluster-info
  ```
* Spring Cloud Kubernetes requires access to Kubernetes API in order to be able to retrieve a list of address of pods running for a single service. Execute the following command:
  ```
  kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default
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
# References:
* https://medium.com/@shivraj.jadhav82/microservice-using-spring-boot-docker-and-kubernetes-or-minikube-in-linux-mint-5b0859770baf
* https://medium.com/@brianbmathews/getting-started-with-minikube-docker-container-images-for-testing-kubernetes-locally-on-mac-e39adb60bd41
* Spring Feign Kubernetes: https://github.com/nhatthai/spring-feign-kubernetes
* Related to endpoint resolution in minikube: https://medium.com/@nieldw/rbac-and-spring-cloud-kubernetes-847dd0f245e4

# Errors:
* operation: [get] for kind: [endpoints] with name: [hello-service] in namespace: [default] failed: This is related to minikube RBAC. Starting minikube like below solved the issue:
  ```
  minikube start --vm-driver=none --bootstrapper=kubeadm --extra-config=apiserver.authorization-mode=RBAC
  kubectl create clusterrolebinding add-on-cluster-admin --clusterrole=cluster-admin --serviceaccount=kube-system:default
  ```
