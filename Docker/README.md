* Virtualization vs containerization
* Docker?
* Docker Architecture
* Few Examples

* Docker is a computer program that performs operating-system-level virtualization also known as containerization.

## Advantages of docker
* Light weight compared to a traditional VM.
* Based on open standards. Can be run on any OS like windows, Linux, Mac etc or on cloud.
* Isolates the need for tight coupling from the underlying infrastructure.

## Hypervisors: 
A hypervisor is a program that would enable you to host several different virtual machines on a single hardware. Each one of these virtual machines or operating systems you have will be able to run its own programs, as it will appear that the system has the host hardware's processor, memory and resources. In reality, however, it is actually the hypervisor that is allocating those resources to the virtual machines.

## Containerization: 
* Light weight, stand alone, executable package.
* A type of virtualization but runs on same OS. Shorter boot times. All containers run on same host. Classic example of RAM division
* Platform agnostic and also integration point of view.
* S/w can be installed all in one container or multiple light weight isolated containers

## Docker Engine: 
* REST API + socket.io(websocket i.e. long polling) + TCP connection
* CLI --> REST API --> Docker Deamon --> Container Details
* windows needs a docker toolbox

## Docker Images and Containers
Docker Registery to store images (docker hub or local registery)

**client**: docker build, docker pull, docker run
**docker_host**: Container + images 
**registery**: Cloud or local repo

```
docker pull <image-name:tag> 
docker run <image-id>
docker images
docker ps
docker ps -a
```
## Build own docker image
* From --> Base image from which the container id built
* RUN --> command to execute on this image

docker build command

Docker Run command Ex: docker run --name anyname -p <host-port>:<container:port> <image_name:tag>

start/stop containers
delete container / images

## Moving a Docker container to another machine
* docker commit
* docker save mynewimage > /tmp/mynewimage.tar
* docker run <container ID/imagename>

or

* docker export somename | gzip > image.tar.gz
* tar xvzf image.tar.gz | docker import - image-name
* docker run -t -i image-name /bin/bash

## Why orchestration (aka Managing containers)
The orchestrator will take care of two things:

* The timing of container creation - as containers need to be created by order of dependencies and
* Container configuration in order to allow containers to communicate with one another - and for that the orchestrator needs to pass runtime properties between containers. 

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

