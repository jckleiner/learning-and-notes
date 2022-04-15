FROM jenkins/jenkins:lts-jdk11

# disable the jenkins GUI setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

ENV CASC_JENKINS_CONFIG /var/jenkins_home/configuration-as-code.yaml

USER root

# install additional packages
RUN apt-get update && \
    apt-get install -y python3 python3-pip && \
    pip install jenkins-job-builder

USER jenkins

# install plugins
COPY plugins.yaml /usr/share/jenkins/ref/plugins.yaml
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.yaml

# copy the config for the Jenkins Configuration as Code Plugin
COPY --chown=jenkins:jenkins configuration-as-code.yaml /var/jenkins_home/configuration-as-code.yaml
ADD --chown=jenkins:jenkins jobs /var/jenkins_home/jobs
ADD --chown=jenkins:jenkins logic /var/jenkins_home/logic


