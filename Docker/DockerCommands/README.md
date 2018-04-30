Docker has been installed on a t2.large EC2 instance type which is behind a security group with no public access to the internet.

Below is the DockerFile used to build the Jenkins image: (This will install Jenkins 2.6 along with java 8 and maven 3.5.2)
```
FROM jenkins
MAINTAINER Syril Sadasivan

USER root
ARG MAVEN_VERSION=3.5.2
ARG USER_HOME_DIR="/opt"
ARG SHA=707b1f6e390a65bde4af4cdaf2a24d45fc19a6ded00fff02e91626e3e42ceaff
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /opt/maven /opt/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha256sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /opt/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /opt/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /opt/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

RUN wget --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u162-b12/0da788060d494f5095bf8624735fa2f1/jdk-8u162-linux-x64.tar.gz \
  && mkdir -p /opt/java8 \
  && tar -xzf jdk-8u162-linux-x64.tar.gz -C /opt/java8 --strip-components=1 \
  && rm -f jdk-8u162-linux-x64.tar.gz

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

