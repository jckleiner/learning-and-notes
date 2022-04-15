FROM jenkins/jenkins:lts-jdk11

# disable the jenkins GUI setup wizard
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

ENV CASC_JENKINS_CONFIG /var/jenkins_home/configuration-as-code.yaml

# install plugins
COPY plugins.yaml /usr/share/jenkins/ref/plugins.yaml
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.yaml

# copy the config for the Jenkins Configuration as Code Plugin
COPY configuration-as-code.yaml /var/jenkins_home/configuration-as-code.yaml

# ARG DOCKER_GROUP_NAME
# ARG DOCKER_GROUP_ID

# ###### install ansible and docker ######
# USER root

# # RUN chown jenkins:jenkins /var/run/docker.sock
# # RUN groupadd -g $DOCKER_GROUP_ID $DOCKER_GROUP_NAME && usermod -aG $DOCKER_GROUP_NAME jenkins

# # RUN apt update && apt install -y ansible # installs ansbile 2.7
# # Install ansible 2.9.24 - https://docs.ansible.com/ansible/latest/installation_guide/intro_installation.html
# RUN echo "deb http://ppa.launchpad.net/ansible/ansible/ubuntu bionic main" | tee -a /etc/apt/sources.list && \
#     apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 93C4A3FD7BB9C367 && \
#     apt update && \
#     apt install -y ansible

# # install docker
# RUN apt install -y apt-transport-https ca-certificates curl gnupg2 software-properties-common && \
#     curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - && \
#     add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable" && \
#     apt update && \
#     apt install -y docker-ce && \
#     usermod -aG docker jenkins
# USER jenkins
# ###### / install ansible and docker ######



# # tells where the config for the JCasC-Plugin is
# ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml

# # copy the .txt file which contains a list of plugins to install
# COPY --chown=jenkins:jenkins plugins.txt /usr/share/jenkins/ref/plugins.txt

# # Jenkins CasC configuration
# COPY casc.yaml /var/jenkins_home/casc.yaml

# # run install-plugins.sh with the list of these plugins
# RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# USER root

# COPY --chown=jenkins:jenkins ./chown-of-docker-socket.sh .

# ENTRYPOINT [ "./chown-of-docker-socket.sh" ]

