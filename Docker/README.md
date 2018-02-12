Virtualization vs containerization
Docker?
Docker Architecture
Some Examples

Hypervisors : A hypervisor is a program that would enable you to host several different virtual machines on a single hardware. Each one of these virtual machines or operating systems you have will be able to run its own programs, as it will appear that the system has the host hardware's processor, memory and resources. In reality, however, it is actually the hypervisor that is allocating those resources to the virtual machines.

Containerization: A type of virtualization but runs on same OS. Shorter boot times. All containers run on same host. Classic example of RAM division
Also integration point of view
S/w can be installed all in one container or multiple light weight isolated containers

Docker Engine: REST API + socket.io(websocket i.e. long polling) + TCP connection
CLI
|
|
REST API
|
Docker Deamon
container 1
container 2

windows needs a docker toolbox

Docker Images and Containers
Docker Registery to store images (docker hub or local registery)

client        docker_host               registery
docker build     container + images       cloud or local repo
docker pull 
docker run

docker pull <image-name:tag> 
docker run <image-id>
docker images
docker ps
docker ps -a

Build own docker image
From --> Base image from which the container id built
RUN --> command to execute on this image

docker build command

Ex: docker run --name anyname -p <host-port>:<container:port> <image_name:tag>


start/stop containers
delete container / images
