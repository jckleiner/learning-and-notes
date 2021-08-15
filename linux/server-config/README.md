## New Server Configuration (Ubuntu 18.04)

### 0. Update the system
 * `sudo apt update && sudo apt upgrade`

### 1. Change system language (if needed)

 * `cat /etc/default/locale` should give `LANG="en_US.UTF-8"`
 * If not: `sudo apt-get install -y language-pack-en && sudo update-locale LANG=en_US.UTF-8`
 * and then logout and login again to your SSH session. This will reload the locale configuration for your session.
   Run `cat /etc/default/locale` again. Verify you see only the single line `LANG="en_US.UTF-8"`.
   If you see an additional line `LC_ALL=en_US.UTF-8`, then remove the entry for `LC_ALL` from `/etc/default/locale` and logout and then log back in once more.
 * `sudo systemctl show-environment` and ensure you see `LANG=en_US.UTF-8` in the output.
   If you don’t see this, do `sudo systemctl set-environment LANG=en_US.UTF-8` and run the above `sudo systemctl show-environment` again and confirm you see `LANG=en_US.UTF-8` in the output.

  * TODO: previous steps doesnt change man and apt languages, also what happens for a new user?

<!-- OLD -->
 <!-- * `man usermod` -> if not English then change language
 * Install English language packages: `sudo apt-get install language-pack-en language-pack-en-base manpages`
 * Regenerating the supported locale list: `sudo dpkg-reconfigure locales` choose `en_US.UTF-8`
 * Change the current default locale: `sudo update-locale LANG=en_US.UTF-8 LANGUAGE= LC_MESSAGES= LC_COLLATE= LC_CTYPE=`
 * Set bashrc or zshrc profile (for root): 
  ```
    echo "export LANGUAGE=en_US.UTF-8
    export LANG=en_US.UTF-8
    export LC_ALL=en_US.UTF-8">>~/.bash_profile
  ```
 * `sudo reboot`
 * Run `locale` to check your current locale. -->

### 2. Create a new user with sudo privileges
 * Create a new user with a home directory and sudo privileges:  `useradd -m -G sudo -s /bin/bash <USERNAME>`
 * (alternative) Add sudo privileges to an existing user: `usermod -aG sudo <USERNAME>`
 * Set password for user: `sudo passwd <USERNAME>`

### 3. SSH configuration
TODO TODO TODO TODO 
 * disable root login with ssh
 * should only be able login with newly created user with ssh
 * password and public key

### 4. Install and configure a Firewall (UFW)
 * `sudo apt install ufw`
 * Make sure ufw is disabled, otherwise if the ssh connection breaks, you won't be able to log back in: `service --status-all`
 * Allow SSH connections: `sudo ufw allow ssh` same as `sudo ufw allow 22` (allow all connections on port 22)
 * Start service and enable on system startup: `sudo ufw enable`
 * Make sure service is running: `sudo ufw status verbose`

### 5. Install Docker (Ubuntu)
 * Test if docker is already installed: `docker run hello-world`
 * `sudo apt install apt-transport-https ca-certificates curl software-properties-common`
 * `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -`
 * `sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"`
 * `sudo apt update`
 * `apt-cache policy docker-ce`: Make sure you are about to install from the Docker repo instead of the default Ubuntu repo. 
   The output should have the URLs ` https://download.docker.com/linux/ubuntu`. Notice that docker-ce is not installed, but the candidate for installation is from the Docker repository for Ubuntu 18.04 (bionic). 
 * Finally, install Docker: `sudo apt install docker-ce -y`
 * Docker should now be installed, the daemon started, and the process enabled to start on boot. Check that it’s running: `sudo systemctl status docker`

By default, the docker command can only be run the root user or by a user in the docker group, which is automatically created during Docker’s installation process. If you attempt to run the docker command without prefixing it with sudo or without being in the docker group, you’ll get an output like this:

    Output
    docker: Cannot connect to the Docker daemon. Is the docker daemon running on this host?.
    See 'docker run --help'.

 * If you want to avoid typing sudo whenever you run the docker command, add your username to the docker group: `sudo usermod -aG docker <USERNAME>`
 * To apply the new group membership, logout and in to the user again, or type the following: `su - <USERNAME>`
 * Confirm that your user is now added to the docker group by typing: `id -nG [<USERNAME>]`
 * Test: `docker run hello-world`

### 6. Install Docker Compose
 * Check if already installed: `docker-compose --version`
 * `sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose`
 * `sudo chmod +x /usr/local/bin/docker-compose`
 * `docker-compose --version`

### 7. Pull the project repository and start the application/configuration
 * We need git to pull our repository: `sudo apt install git`
 * Pull repo using the newly created user, make sure you are in that users home directory and start app

### 8. Enable Firewall Ports when http and https service is setup
 * Allow HTTP connections: `sudo ufw allow http` (`sudo ufw allow 80`)
 * Allow HTTPS connections: `sudo ufw allow https` (`sudo ufw allow 443`)

### 9. ZSH and other config stuff
 * TODO

### 10. Nginx as a reverse proxy and Let's Encrypt
 * `sudo apt install nginx`
 * Firewall (80 and 443): `sudo ufw allow 'Nginx Full'`
 * `sudo ufw status`
 * Check: `systemctl status nginx` should say running and enabled

 * `sudo nano /etc/nginx/sites-available/default`

        # increase the default upload limit (1MB)
        client_max_body_size 250M;

        server_name yourdomain.com www.yourdomain.com;

        location / {
            proxy_pass http://localhost:5000; #whatever port your app runs on
            proxy_http_version 1.1;
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header Connection 'upgrade';
            proxy_set_header Host $host;
            proxy_cache_bypass $http_upgrade;
        }

 * `sudo nginx -t`
 * `sudo add-apt-repository ppa:certbot/certbot`
 * Change your A registry if necessary, make sure the domain works and everything is setup and works with http
 * 
        sudo add-apt-repository ppa:certbot/certbot
        sudo apt-get update
        sudo apt-get install python-certbot-nginx
        sudo certbot --nginx -d yourdomain.com -d www.yourdomain.com

        # Only valid for 90 days, test the renewal process with
        certbot renew --dry-run

  * Automate renew: TODO


### Jenkins
TODO
 * Jenkins for health check -> main page, check if 200

### Certificates
The default location to install certificates is `/etc/ssl/certs`, for the key files (server.key) it is `/etc/ssl/private`. This enables multiple services to use the same certificate without overly complicated file permissions.
All generated keys and issued certificates from Certbot can be found in `/etc/letsencrypt/live/$domain` , where `$domain` is the certificate name

## TODOs
 * how-does-apt-get-really-work: https://unix.stackexchange.com/questions/377736/how-does-apt-get-really-work
 * Don't ask user for sudo password? Is it a good idea?
 * TCP and UDP ports, can apps use both ports with each Protocol at the same time? When is a port a UDP port and when is it a TCP port?
 * Automate Certification Renewal
 * Privacy Page
 * SSH configuration, disable root etc.
 * Give credentials and everything
 * Nginx gzip
 * Wasnt firewall active when the container on 8000 started?
 * ZSH and other config stuff
 * Ansible playbook for this
