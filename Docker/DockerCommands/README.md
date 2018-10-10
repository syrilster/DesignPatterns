Docker has been installed on a t2.large EC2 instance type which is behind a security group with no public access to the internet.

Below is the DockerFile used to build the Jenkins image: (This will install Jenkins 2.6 along with java 8 and maven 3.5.2)
```
FROM jenkins
MAINTAINER Syril Sadasivan

USER root
ARG MVN_VERSION=3.5.4
ARG USER_HOME_DIR="/opt"
ARG MVN_MD5=89eea39183139e5f8a0c1601d495b3b6

RUN wget http://apache.claz.org/maven/maven-3/$MVN_VERSION/binaries/apache-maven-$MVN_VERSION-bin.tar.gz

RUN mkdir /opt/maven \
&& tar -xzf apache-maven-$MVN_VERSION-bin.tar.gz \
&& cp -r apache-maven-$MVN_VERSION /opt/maven \
&& rm -f apache-maven-$MVN_VERSION-bin.tar.gz

ENV MAVEN_HOME /opt/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

RUN wget --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz  \
  && mkdir -p /opt/java8 \
  && tar -xzf jdk-8u181-linux-x64.tar.gz -C /opt/java8 --strip-components=1 \
  && rm -f jdk-8u181-linux-x64.tar.gz

ENV PATH /opt/java8/bin:$PATH

COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt
```
Once the DockerFile is ready, build an image to start the container
```
docker build -t anyname--file DockerFile <docker-file-location>
Example: docker build -t mirthjenkins --file DockerFile /home/ec2-user/jenkins_home/docker/jenkins/
```
Now the image is build and can be verified using the below command
```
docker images
```

mirthjenkins is the image file that docker build has given us.

Create and manage volumes
```
docker volume create jenkins_data
docker volume ls
docker volume inspect jenkins_data
```
Now that the image is ready, we can run it using the below commands. Note that we are mounting the volume from the host machine with the container.
```
docker run -d --name=<anyname> -p <host:port>:<container:port> source=<volume_name>,target=<path_in_container> <image_name:tag>
Example: docker run -d -p 80:8080 --name=jenkins-master --mount source=jenkins_data,target=/var/jenkins_home mirthjenkins
Verify the image is running using

docker ps 
```

The jenkins image is running in the docker container with container id d73cf5e0e4c6

Command to bash to the docker container
```
docker exec -it <container_id> /bin/bash
Example: docker exec -it 38e5f956d470 /bin/bash
```
Command to remove a previously created docker image
```
docker rmi -f <image_id>
```

Command to copy a file from your host to docker container
```
docker cp <file_to_be_copied> container_id:<location_where_file_need_to_be_placed>
Example: docker cp test.xml 38e5f956d470:/opt/
```
Commands to start/stop a docker container:
```
docker ps -a to check the container which are not running

docker start <container_id>

docker stop <container_id>
```

