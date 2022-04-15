
# Jenkins - Configuration as Code and Docker

Sources:
 * https://www.digitalocean.com/community/tutorials/how-to-automate-jenkins-setup-with-docker-and-jenkins-configuration-as-code

## Installing Plugins

### Deprecated way: `install-plugins.sh`
The jenkins docker image comes with an installation script `/usr/local/bin/install-plugins.sh`. This script will look for a file `/usr/share/jenkins/ref/plugins.txt` and will install all the plugins listed in that file.

    COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
    RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

### Recommended way: `jenkins-plugin-cli`
    COPY plugins.yaml /usr/share/jenkins/ref/plugins.yaml
    RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.yaml

## Variables in JCasC
Variables are defined using the `${VARIABLE_NAME}` syntax, and its value can be filled in using:
 1. an environment variable of the same name, 
 2. or a file of the same name that’s placed inside the `/run/secrets/` directory within the container image.

## User Authentication
In the context of Jenkins, a security realm is simply an authentication mechanism; the `local` security realm means to use basic authentication where users must specify their ID/username and password. Other security realms exist and are provided by plugins. For instance, the LDAP plugin allows you to use an existing LDAP directory service as the authentication mechanism. The GitHub Authentication plugin allows you to use your GitHub credentials to authenticate via OAuth.

By default, the Jenkins core installation provides us with three authorization strategies:

 * `unsecured`: every user, including anonymous users, have full permissions to do everything
 * `legacy`: emulates legacy Jenkins (prior to v1.164), where any users with the role admin is given full permissions, whilst other users, including anonymous users, are given read access.
 * `loggedInUsersCanDoAnything`: anonymous users are given either no access or read-only access. Authenticated users have full permissions to do everything. By allowing actions only for authenticated users, you are able to have an audit trail of which users performed which actions.

> Note: A `role` in Jenkins can be a user (for example, daniel) or a group (for example, developers)

> Note: You can explore other authorization strategies and their related plugins in the documentation (https://www.jenkins.io/doc/developer/extensions/jenkins-core/#authorizationstrategy); these include plugins that handle both authentication and authorization.




See the installed plugins: http://localhost:8080/pluginManager/installed

Valid properties are determined by the plugins that are installed. For example, the `jenkins.authorizationStrategy.globalMatrix.permissions` property would only be valid if the `Matrix Authorization Strategy` plugin is installed.
See what configuration-as-code properties are valid: http://localhost:8080/configuration-as-code/reference

### Matrix Authorization Strategy Plugin
All of these authorization strategies are very crude, and does not afford granular control over how permissions are set for different users. Instead, you can use the `Matrix Authorization Strategy` plugin
The Matrix Authorization Strategy plugin allows you to use the jenkins.`authorizationStrategy.globalMatrix.permissions` JCasC property to set global permissions.

```yaml
jenkins:
  securityRealm:
    local: ...
    
  authorizationStrategy:
    globalMatrix:
      permissions:
        - "USER:Overall/Administer:admin"
        - "GROUP:Overall/Read:authenticated"
```

The `globalMatrix` property sets global permissions (as opposed to per-project permissions). The `permissions` property is a list of strings with the format `<group-or-user>:<permission-group>/<permission-name>:<role>`. Here, you are granting the `Overall/Administer` permissions to the admin user. You’re also granting `Overall/Read` permissions to `authenticated`, which is a special role that represents all authenticated users. There’s another special role called `anonymous`, which groups all non-authenticated users together. But since permissions are denied by default, if you don’t want to give anonymous users any permissions, you don’t need to explicitly include an entry for it.

See also this file for other permission examples: https://github.com/jenkinsci/matrix-auth-plugin/blob/master/src/test/resources/org/jenkinsci/plugins/matrixauth/integrations/casc/configuration-as-code.yml

### Authorize Project plugin

Jenkins shows an error:

 * Builds in Jenkins run as the virtual Jenkins SYSTEM user with full Jenkins permissions by default. This can be a problem if some users have restricted or no access to some jobs, but can configure others. If that is the case, it is recommended to install a plugin implementing build authentication, and to override this default.

    ❌ No implementation of access control for builds is present. It is recommended that you install Authorize Project Plugin or another plugin implementing the QueueItemAuthenticator extension point.

This issue in the notifications list relates to build authentication. By default, all jobs are run as the system user, which has a lot of system privileges. Therefore, a Jenkins user can perform privilege escalation simply by defining and running a malicious job or pipeline; this is insecure.

Instead, jobs should be ran using the same Jenkins user that configured or triggered it. To achieve this, you need to install an additional plugin called the `Authorize Project` plugin.

```yaml
security:
  queueItemAuthenticator:
    authenticators:
    - global:
        strategy: triggeringUsersAuthorizationStrategy
```

## Enabling Agent to Controller Access Control

So far we have deployed only a single instance of Jenkins, which runs all builds. However, Jenkins supports distributed builds using an agent/controller configuration. The controller is responsible for providing the web UI, exposing an API for clients to send requests to, and co-ordinating builds. The agents are the instances that execute the jobs.

The benefit of this configuration is that it is more scalable and fault-tolerant. If one of the servers running Jenkins goes down, other instances can take up the extra load.

However, there may be instances where the agents cannot be trusted by the controller. For example, the OPS team may manage the Jenkins controller, whilst an external contractor manages their own custom-configured Jenkins agent. Without the Agent to Controller Security Subsystem, the agent is able to instruct the controller to execute any actions it requests, which may be undesirable. By enabling Agent to Controller Access Control, you can control which commands and files the agents have access to.

```yaml
jenkins:
  securityRealm:
    local: ...

  remotingSecurity:
    enabled: true
```

## Create Jenkins Agent Nodes

* Building on the built-in node can be a security issue. You should set up distributed builds. See the documentation.

If you don’t have a second machine to host the agent, you can create a separate user account on the same computer that hosts your controller and then use that account to run the agent.







