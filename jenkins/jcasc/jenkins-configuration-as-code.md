


* https://www.digitalocean.com/community/tutorials/how-to-automate-jenkins-setup-with-docker-and-jenkins-configuration-as-code
* https://piotrminkowski.com/2018/09/24/running-jenkins-server-with-configuration-as-code/


`-v /var/run/docker.sock:/var/run/docker.sock`
`-v /var/run/docker.sock.raw:/var/run/docker.sock`


* https://stackoverflow.com/questions/36185035/how-to-mount-docker-socket-as-volume-in-docker-container-with-correct-group
* https://github.com/docker/for-mac/issues/4755
* https://stackoverflow.com/questions/44999000/permission-denied-error-invoking-docker-on-mac-host-from-inside-docker-ubuntu-co


### /var/run/docker.sock: connect: permission denied
An `ls -la /var/run/docker.sock` inside the container shows the owner is `root:root`.
This is because bind-volumes will by default have root as the owner. There is currently no way to change this (see: Add ability to mount volume as user other than root: https://github.com/moby/moby/issues/2259).

**Workaround**: Since the file is mounted at runtime, we have to set the root user as the user of the container (not jenkins like before) and then change the owner of `/var/run/docker.sock` to `jenkins:jenkins` at runtime by modifying the original `ENTRYPOINT` script for `jenkins/jenkins:lts-jdk11` and add `chown jenkins:jenkins /var/run/docker.sock` at the beginning of the script. 


`docker build -t jckleiner/hate-jenkins .`

`docker run -d -v /var/run/docker.sock.raw:/var/run/docker.sock --name jenkins -p 8080:8080 --env-file=.env jckleiner/hate-jenkins`
`docker run -d --name jenkins -p 8080:8080 --env-file=.env jckleiner/hate-jenkins`

`docker stop jenkins && docker rm jenkins && docker build -t jckleiner/hate-jenkins . && docker run -d -v /var/run/docker.sock.raw:/var/run/docker.sock --name jenkins -p 8080:8080 --env-file=.env jckleiner/hate-jenkins`

    docker stop jenkins && \
    docker rm jenkins && \
    docker build -t jckleiner/hate-jenkins . && \
    docker run -d -v /var/run/docker.sock:/var/run/docker.sock --name jenkins -p 8080:8080 --env-file=.env jckleiner/hate-jenkins

### Documentation for JCasC
The documentation for all the possible elements can be found in `Jenkins > Manage Jenkins > Configuration as Code > Documentation`

### Export JCasC Configuration
You can export a `yml` file for an existing jenkins instance. That file will then show most of the configuration you did on the instance as a casc yml file. 
But this is intended to be used only as a starting point and as a help.

## Todo

 * When no  `JENKINS_ADMIN_ID` and `JENKINS_ADMIN_PASSWORD` given, the first access prompts a user creation form. Can that be disabled?
 * How to create folders? 
 * Can I create jobs/pipelines?
 * How to do the user creation? Best practices?
 * Docker in docker with socket binding
 * Remove all unused plugins, and check recommended plugins
 * Write versions to plugins? ':latest' is not great