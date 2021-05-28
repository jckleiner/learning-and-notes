### New Server Configuration (Ubuntu 18.04)

#### 1. Change system language
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

#### 2. Create a new user with sudo privileges
 * Create a new user with a home directory and sudo privileges:  `useradd -m -G sudo -s /bin/bash <USERNAME>`
 * Add sudo privileges to an existing user: `usermod -aG sudo <USERNAME>`
 * Set password for user: TODO

#### 3. SSH configuration
TODO
 * disable root login with ssh
 * can only login with newly created user with ssh
 * password vs public key
