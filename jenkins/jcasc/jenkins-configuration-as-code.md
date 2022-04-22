





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