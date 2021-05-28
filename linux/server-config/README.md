## New Server Configuration (Ubuntu 18.04)

### 0. Update the system
 * `sudo apt update && sudo apt upgrade`

### 1. Change system language (if needed)
 * `man usermod` -> if not English then change language
 * Install English language packages: `sudo apt-get install language-pack-en language-pack-en-base manpages`
 * Regenerating the supported locale list: `sudo dpkg-reconfigure locales` choose `en_US.UTF-8`
 * Change the current default locale: `sudo update-locale LANG=en_US.UTF-8 LANGUAGE= LC_MESSAGES= LC_COLLATE= LC_CTYPE=`
 * Set bashrc or zshrc profile: 
  ```
    echo "export LANGUAGE=en_US.UTF-8
    export LANG=en_US.UTF-8
    export LC_ALL=en_US.UTF-8">>~/.bash_profile
  ```
 * `sudo reboot`
 * Run `locale` to check your current locale.

### 2. Create a new user with sudo privileges
 * Create a new user with a home directory and sudo privileges:  `useradd -m -G sudo -s /bin/bash <USERNAME>`
 * Add sudo privileges to an existing user: `usermod -aG sudo <USERNAME>`
 * Set password for user: `sudo passwd <USERNAME>`

### 3. SSH configuration
TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO 
 * disable root login with ssh
 * can only login with newly created user with ssh
 * password vs public key

### 4. Install and configure a Firewall (UFW)
 * `sudo apt install ufw`
 * Make sure ufw is disabled, otherwise if the ssh connection breaks, you won't be able to log back in: `service --status-all`
 * Allow SSH connections: `sudo ufw allow ssh` same as `sudo ufw allow 22` (allow all connections on port 22)
 * Start service and enable on system startup: `sudo ufw enable`
 * Make sure service is running: `sudo ufw status verbose`

### 6. Install Docker
 * `sudo apt install do`
 * `sudo apt install apt-transport-https ca-certificates curl software-properties-common`
 * `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`
 * `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"`
 * `sudo apt update`
 * Make sure you are about to install from the Docker repo instead of the default Ubuntu repo: `apt-cache policy docker-ce`. The output should have the URLs ` https://download.docker.com/linux/ubuntu`. Notice that docker-ce is not installed, but the candidate for installation is from the Docker repository for Ubuntu 18.04 (bionic). 
 * Finally, install Docker: `sudo apt install docker-ce`
 * Docker should now be installed, the daemon started, and the process enabled to start on boot. Check that it’s running: `sudo systemctl status docker`

By default, the docker command can only be run the root user or by a user in the docker group, which is automatically created during Docker’s installation process. If you attempt to run the docker command without prefixing it with sudo or without being in the docker group, you’ll get an output like this:

    Output
    docker: Cannot connect to the Docker daemon. Is the docker daemon running on this host?.
    See 'docker run --help'.

 * If you want to avoid typing sudo whenever you run the docker command, add your username to the docker group: `sudo usermod -aG docker <USERNAME>`
 * To apply the new group membership, log out of the server and back in, or type the following: `su - <USERNAME>`
 * Confirm that your user is now added to the docker group by typing: `id -nG [<USERNAME>]`

### 7. Install Docker Compose
 * `sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`
 * `sudo chmod +x /usr/local/bin/docker-compose`
 * `docker-compose --version`

### 8. Pull the project repository and start the application/configuration
 * We need git to pull our repository: `sudo apt install git`
 * Pull repo and start app

### 9. Enable Firewall Ports when http and https service is setup
 * Allow HTTP connections: `sudo ufw allow http` (`sudo ufw allow 80`)
 * Allow HTTPS connections: `sudo ufw allow https` (`sudo ufw allow 443`)



## TODOs
 * Ansible playbook for this
