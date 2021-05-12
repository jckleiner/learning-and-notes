

### Terminal Formatting
```bash
    $ echo $PS1
    $ PS1='\n\[`[ $? = 0 ] && X=2 || X=1; tput setaf $X`\]\h\[`tput sgr0`\]:$PWD\n\$'
    export PS1="\\w:\$(git branch 2>/dev/null | grep \'^*\' | colrm 1 2)\$ "
```

### Usefull programs
```bash
    $ tree .
    $ yum install mc
    $ htop
```

### Run any one of the following command on Linux to see open ports:


netstat [address_family_options] [--tcp|-t] [--udp|-u] [--raw|-w] [--listening|-l] [--all|-a] [--numeric|-n] [--numeric-hosts][--numeric-ports][--numeric-ports] [--symbolic|-N] [--extend|-e[--extend|-e]] [--timers|-o] [--program|-p] [--verbose|-v] [--continuous|-c] [delay]

netstat -tulpan
netstat [--tcp|-t] [--udp|-u] [--listening|-l] [--program|-p] [--all|-a] [--numeric|-n]

---> IMPORTANT: -n flag is for numeric values, 
    if it's not set, the output can be like '::::webcache'
    if it's set, then it will show the port number '::::8080'

    ALWAYS USE *TULPAN*

    sudo lsof -i -P -n | grep LISTEN
    sudo lsof -i :8080 | grep LISTEN
    sudo netstat -tulpan | grep LISTEN
    sudo lsof -i:22 ## see a specific port such as 22 ##
    sudo nmap -sTU -O IP-address-Here


### Use 'll' instead of 'ls -l'

### Find IPs (Not every IP address is the correct one!):
```bash
    $ hostname -I
```
### Accessing CentOS with IP did not respond, closed firewall
```bash
    $ service firewalld stop
```
### Query Packages with RedHat Package Manager:  
```bash
    $ rpm -qa | grep -i http
```
### Search in files for text recursively:
Show output:
```bash
    $ grep -rnw '/path/to/somewhere/' -e 'pattern' 
    $ grep -rnwl -> just file names  
```

### How to find files in Unix:
```bash
    $ locate ?
    $ which ?
    $ find ?
    $ find / -name 'pg_hba.conf' 2>/dev/null
    TODO: find name with wildcards?
    TODO: find ignore case?
    TODO: fuzzy find?
  ```  
    
### Find in package manager:
```bash
    $ yum provides */netstat
    $ yum whatprovides */netstat
    $ yum list postgre*
    $ yum list installed | grep post
```
### See all running services
```bash
    $ ls -1 /etc/rc$(runlevel| cut -d" " -f2).d/S*
```
### Disk space human readable
```bash
    $ df -Ph /
```
### Bash Scripting Tips
```bash
    Ctrl C traps, order is important
    echo -e "bla \n bla" enable backslash 

            #!/bin/bash
            function finish() {
                    deactivate
                    echo -e "\nCkan stopped."
            }
            trap finish SIGINT
            echo -e "Starting ckan..."
            . /usr/lib/ckan/default/bin/activate
            cd /usr/lib/ckan/default/src/ckan
            paster serve /etc/ckan/default/development.ini
```
    # Systemctl service timed out during start
        if the shell script runs an endless loop and does not exit, set Type to simple
        otherwise set Type to oneshot

    https://stackoverflow.com/questions/525592/find-and-replace-inside-a-text-file-from-a-bash-command

### scp
```bash
    Copy file from remote to local: 
        scp -i id_rsa -r root@109.75.188.38:/var/www/dodp/public/fileadmin .
    Copy file from local to remote:
        scp -i id_rsa foobar.txt root@109.75.188.38:/root/
```

### rsync
    # uses the key in ~/.ssh/id_rsa, use 'ssh -o StrictHostKeyChecking=no -i ~/.ssh/id_rsa_another' to give another identity file
    # created the test dir if it does not exitst
    # copies everything inside /lmwr-dotsource/ to /test/
    # hard and soft links ???
    -> rsync -uavz --delete -e 'ssh -o StrictHostKeyChecking=no' ./lmwr-dotsource/ ansible@lmwr-edge01.test-server.ag:/home/ansible/test/

### nmap
    -> yum install nmap
    -> nmap ip 
        * Checks all the open ports?

### Compress a whole directory to a .tar.gz archive

order is important, use 'STAR' instead?
    https://unix.stackexchange.com/questions/231265/tar-and-its-key-letters-is-it-a-bug-or-feature

    tar -zcvf archive-name.tar.gz directory-name

    tar -vzf archive-name.tar.gz directory-name
            -z : Compress archive using gzip program in Linux or Unix
            -c : Create archive on Linux
            -v : Verbose i.e display progress while creating archive
            -f : Archive File name

    # Exclude 'exFolder', the trailing / might be important!
    tar -zcv --exclude=deleteme/exFolder -f deleteme.tar.gz deleteme

### Extract .tar.gz archive
    tar -vzfx prog-1-jan-2005.tar.gz
            -x: Extract files from given archive


### Mysqldump 
    mysqldump -u [username] -p [databaseName] > [filename]-$(date +%F).sql
    mysql -u [username] -p [databaseName] < [filename].sql

### Postgres Normal Dump vs Custom Format Dump

    * IMPORTANT: For CKAN DB migration, custom format dump did not work, normal dump and restore worked. Custom format dump files are significantly smaller than normal dump files

    Normal Dump and Restore:
        * Dump: pg_dump -U postgres datastore_database > datastore_database_dump
        * Restore: psql -U postgres datastore_database < /vm-shared/new_dumps/datastore_database_dump

    Custom format dump and restore: 
        * Dump: pg_dump -U postgres -d ckan_database > ckan_database.dump --format=custom
        * Restore: pg_restore -U postgres --clean --if-exists -d ckan_database -1 /vm-shared/database_dumps/ckan_database.dump

### Postgres Restore
    sudo -u postgres pg_restore --clean --if-exists -d ckan_database < /root/ckan_database.dump
    sudo -u postgres pg_restore --clean --if-exists -d datastore_database < /vm-shared/datastore_database.dump

### Commandline navigation
```bash
    ctrl+a for going to 'anfang'
    ctrl+e for going to 'ende'
```
### Monitor file changes in bash, instead of opening the file again to see if it updated
```bash
    tail -f ./myFile    
```

### head, tail, less, more, cat
 * $ head myNotes.txt (gives the first 10 lines of a file)
 * $ head -n 25 myNotes.txt (first 24 lines of a file)
 * $ ps aux | head -n 25
 * $ head -c -50 /etc/passwd (print the first 50 BYTES)
 
 * tail -n 25 myNotes.txt (last 25 lines of a file)
 * tail -c 50 myNotes.txt (last 50 BYTES)
 * looking at the end of a log file, keeps the file open and prints new entries:
    * tail -f apache.log

 * less index.html -> Opens a different view and the file can be scrolled thru
 * more index.html -> Fills the terminal with the beginning of the file, showing the percentage shown.

 * cat


### Bash
    A; B    # Run A and then B, regardless of success of A
    A && B  # Run B if and only if A succeeded
    A || B  # Run B if and only if A failed
    A &     # Run A in background.

### Systemctl and journalctl 

    Show running services:  systemctl | grep running
    Display log messages (from start):  sudo journalctl -u <serviceName>
    Display log messages (from end, keeps open like tail -f):   sudo journalctl -u <serviceName> -f
    last 100 lines:     journalctl --unit=my.service -n 100 --no-pager    


### VSCODE

* Install Vs Code on Linux
    https://linuxize.com/post/how-to-install-visual-studio-code-on-centos-7/

* Shortcuts: 
    CTRL+ALT+P -> Command Palette
    CTRL+, -> Settings 

* Change Keyboard Shortcuts:
    CTRL+ALT+P -> "open keyboard shortcuts"
        - CTRL + 7  -> Toggle Block Comment
        - CTRL + 7  -> Toggle Line Comment
        - CTRL + D  -> Delete Line
        - ALT + D   -> Duplicate Line
 
* Config:
    Window size: "window.zoomLevel": -0.4
    Global word wrap: File -> Preferences -> Settings -> Search for "editor.wordWrap" -> ON
    Tab min width: Settings -> "tabSizing" -> shrink
    Terminal font size -> "terminal.font" -> 12
    Change to git bash -> Command Palette -> "Select Default Shell" -> git bash
 
* Extensions to download:
    * Vim
    * Live Server
    * Html Css Support (suggestion for css class names)
    * Bracket Pair Colorizer 2
    * Git lens
    * REST client
    * Markdown All In One :: CTRL+SHIFT+V -> View MD Preview, switch to white mode: 
    * Prettier :: Right click -> Format Document

* Color Theme
    Monokai Gray, Monokai Crete, Default theme, Monokai, One Maokai,

* File Icons:
    Webstorm Icons, SC Material, Simple Icons
    View -> Command Palette -> ">icon" -> Preferences: File icon theme

* Navigation File Indentation
    Settings -> "workbench.tree" -> indent -> 18
    -> "explorer.compactFolders" enabled by default -> disable

* VsCode setttings.json

* Changing VsCode Theme
    - Custom themes are defined under "C:\Users\kleiner\.vscode\extensions\laurenttreguier.monokai-grey-0.2.8\themes\monokai-grey-color-theme.json"
    - You can go to preferences search -> "Developer: inspect TM scope" and then click on the element you want to get the identifier
    - In settings.json, you can customize the color and font for each of these identifiers, either for just a specific theme or in general:

        "editor.tokenColorCustomizations": {
          "[Monokai Grey]": { // for specific themes 
            "textMateRules": [
                // the 'scope' is the identifier you found from the inspect tool
                {
                    "name": "All Strings",
                    "scope": "string",
                    "settings": {
                        "foreground": "#bcec69",
                        "fontStyle": "italic",
                    }
                }
            ]
        }


### Some Vim Binds
    yy  Y   yank/copy

    dd  D   delete line
    
    x       delete letter where the cursor is on

### Docker Stop Compose

    Stop containers
    docker-compose stop

    Remove stopped containers 
    docker-compose rm -f

    Start containers again, build image?
    docker-compose up --force-recreate -d

### Docker Remove All 

    To delete all containers including its volumes use,

    docker rm -vf $(docker ps -a -q)

    To delete all the images,

    docker rmi -f $(docker images -a -q)

    Remember, you should remove all the containers before removing all the images from which those containers were created.

### MAC 

    install composer (same as linux):
        1. curl -sS https://getcomposer.org/installer | php
        2. move composer.phar to  /usr/local/bin/
        3. alias composer="php /usr/local/bin/composer.phar"

    Show open ports?
        # shows the process which listenes on port 80
            sudo lsof -i:80
        # shows all processes which listen on a port
            sudo lsof -PiTCP -sTCP:LISTEN
        ... pma_agent takes port 8000 on restart

    'service' command on Mac
        Show all services:
            sudo launchctl list

    Modify PATH variable
        sudo nano /etc/paths

 # APACHE 
    ==> httpd
    DocumentRoot is /usr/local/var/www.
    DocumentRoot "/Users/kleiner/develop/"
    <Directory "/Users/kleiner/develop/">

    Apache CONF: 
        /usr/local/etc/httpd/httpd.conf 
    to 8080 and in
    /usr/local/etc/httpd/extra/httpd-ssl.conf to 8443 so that httpd can run without sudo.

    To have launchd start httpd now and restart at login:
    brew services start httpd
    Or, if you don't want/need a background service you can just run:
    apachectl start

    Start/Stop APACHE:
        sudo apachectl start
        sudo apachectl restart
    Error Log:
        tail -f /usr/local/var/log/httpd/error_log

    PHP Config for Typo3
    /private/etc php.ini

 # php.ini
    /etc/private/php.ini
    After installing php 7.2:
        /usr/local/etc/php/7.2/php.ini
        /usr/local/opt/php@7.2 ???????????

 # intl installation
    brew install php@7.3
    brew link php@7.3
    (/usr/local/Cellar/php@7.3/7.3.15)
    Then you need to OPEN A NEW TERMINAL to make it effective.
    Then double check the PHP binary path,
    which php

    make sure it's
    /usr/local/bin/php
    instead of
    /usr/bin/php

    php -m | grep intl
    /usr/local/etc/php/7.3/php.ini -> enable intl extension extension=intl
    sudo apachectl stop
    sudo apachectl start


    To enable PHP in Apache add the following to httpd.conf and restart Apache:
    LoadModule php7_module /usr/local/opt/php@7.2/lib/httpd/modules/libphp7.so

    <FilesMatch \.php$>
        SetHandler application/x-httpd-php
    </FilesMatch>

 # Brew PHP

 brew install php@7.2

    Finally, check DirectoryIndex includes index.php
        DirectoryIndex index.php index.html

    The php.ini and php-fpm.ini file can be found in:
        /usr/local/etc/php/7.2/

    php@7.2 is keg-only, which means it was not symlinked into /usr/local,
    because this is an alternate version of another formula.

    If you need to have php@7.2 first in your PATH run:
    echo 'export PATH="/usr/local/opt/php@7.2/bin:$PATH"' >> ~/.zshrc
    echo 'export PATH="/usr/local/opt/php@7.2/sbin:$PATH"' >> ~/.zshrc

    For compilers to find php@7.2 you may need to set:
    export LDFLAGS="-L/usr/local/opt/php@7.2/lib"
    export CPPFLAGS="-I/usr/local/opt/php@7.2/include"


    To have launchd start php@7.2 now and restart at login:
    brew services start php@7.2
    Or, if you don't want/need a background service you can just run:
    php-fpm
    ==> Summary
    /usr/local/Cellar/php@7.2/7.2.28: 514 files, 74.8MB

    brew link --force  php@7.2


# php-fpm

    php-fpm was taking up the port 9000 which I needed for sonarqube. A kill didnt work because it restarted immidiately. So i executed these commands:

        launchctl unload -w ~/Library/LaunchAgents/homebrew.mxcl.php@7.2.plist

# Git

  remove local branches which are not on remote
    
        git fetch -p && git branch -vv | awk '/: gone]/{print $1}' | xargs git branch -D

# FIRST Spirit

    Java 8,9 or 10 is required. 11 wont work

    With the new Update maybe 11 works also?
    
    To start, downd FIRSTspirit.jnlp and start
        -> javaws FIRSTspirit.jnlp
        -> '/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/bin/javaws' FIRSTspirit.jnlp

# nginx conf
    cd /etc/nginx/conf.d/

# Change Timezone for Browser (Chrome)
  Option 1: 
    Create a new empty directory for a separate Chrome user profile. E.g. with
      * mkdir ~/chrome-profile
    You specify the TZ environment variable. You can see the valid timezones for example here, in column TZ.

    To start Chrome, use these commands:
        for Mac OS X: 
          TZ='US/Pacific' open -na "Google Chrome" --args "--user-data-dir=$HOME/chrome-profile"
        for Linux: 
          TZ='US/Pacific' google-chrome "--user-data-dir=$HOME/chrome-profile"

  Option 2: 
    Google Chrome reads the environment variables to get the timezone its running in. Apparently all instances of Chrome share the value (not tested). In order to force a timezone, we need to a) set the environment variable to the timezone we want, b) kill all existing instances of Chrome, c) Print the current timezone to confirm, and d) Start Chrome with the intended timezone.
    The below shell script is tested with OS X but should work for others too.

    export TZ='US/Pacific'

    killall Chrome
    date
    open /Applications/Google\ Chrome.app

  Option 3 (WORKED):
    DevTools -> 3 Dots -> More Tools -> Sensors -> Location
    Looks like the browser ignores the current time of the computer and fetches the timezone externally
        If computer time is 15:00 and the timezone is set as America/Los_Angeles, 
        moment().format() will display the actual time in America/Los_Angeles and take 15:00 into consideration

# Mac crop image
  open it with photos -> edit -> crop -> give aspect ratio

# Mac merge 2 images
  select images -> open all with preview -> select background image and ctrl + C -> File -> new clipboard -> go to other image copy and paste it on background image

# LM - when network not working try:

    docker system prune 
    or
    docker network prune


# Git squash in commandline
    If you want to squash the last 3 commits into 1:
        1. git rebase -i HEAD~3
        2. change the last (3-1) commits from 'pick' to 'squash' or 's' and save
        3. on the next file: comment out the commit messages you don't want to see/adjust it and save
        4. If remote has already the old commits override the remote branch with 'git push --force origin <branch name>'


## Vagrant
`vagrant reload` will reboot the VM, if the VM was provisioned already in the next run reload will skip those by default.
`vagrant reload --provision` will reboot the VM and run the provision steps if any.

Change to root while in user vagrant: `sudo su`


# Resolve hostname to ip 
`ping <hosname>` 
`nslookup <hostname>`
don't give the protocol, else it cannot find the ip
 * Works: `ping h2905222.stratoserver.net` or 
`ping www.h2905222.stratoserver.net`
 * Does not work: `ping http://h2905222.stratoserver.net/`


### Adding a new user with `useradd`
https://linuxize.com/post/how-to-create-users-in-linux-using-the-useradd-command/

The general syntax for the useradd command is as follows:

    useradd [OPTIONS] USERNAME

    useradd -m -G sudo -s /usr/bin/zsh userName 

Only root or users with sudo privileges can use the useradd command to create new user accounts.

When invoked, useradd creates a new user account according to the options specified on the command line and the default values set in the `/etc/default/useradd` file. The variables defined in this file differ from distribution to distribution, which causes the useradd command to produce different results on different systems.

`useradd` also reads the content of the `/etc/login.defs` file. This file contains configuration for the shadow password suite such as password expiration policy, ranges of user IDs used when creating system and regular users, and more.
How to Create a New User in Linux.

The command adds an entry to the `/etc/passwd`, `/etc/shadow`, `/etc/group` and `/etc/gshadow` files.

* **Home Directory**: Use the `-m (--create-home)` option to create the user home directory as `/home/username`
* **Different Home Directory**: If you want to create the user’s home directory in other location, use the `-d (--home)` option.
* **Primary Group**: The `-g (--gid)` option allows you to create a user with a specific initial login group or primary group. If a user has no group specified, the user will be assigned to a new group whith the same username
* **Specify Secondary Groups**: There are two types of groups in Linux operating systems Primary group and Secondary (or supplementary) group. Each user can belong to exactly one primary group and zero or more secondary groups. You to specify a list of supplementary groups which the user will be a member of with the `-G (--groups)` option.
* **Give sudo privilages**: Add the user to the group `sudo` with `-G sudo`. Notice that we use capital G, which indicates we add new secondry groups!
* **Specific Login Shell**: By default, the new user’s login shell is set to the one specified in the `/etc/default/useradd` file. In some distributions the default shell is set to `/bin/sh` while in others it is set to `/bin/bash`.
The `-s (--shell)` option allows you to specify the new user’s login shell.
For example, to create a new user named username with `/usr/bin/zsh` as a login shell type: ```sudo useradd -s /usr/bin/zsh username```
Check the user entry in the /etc/passwd file to verify the user’s login shell:
`sudo cat /etc/passwd`  ->  `username:x :1001:1001::/home/username:/usr/bin/zsh`


To be able to log in as the newly created user, you need to set the user password. To do that run the passwd command followed by the username:

`sudo passwd username`

### Remove user
In most Linux distributions, when removing a user account with userdel, the user home and mail spool directories are not removed. Use the `-r (--remove)` option to force userdel to remove the user’s home directory and mail spool:

    userdel -r username

## TODO sudo usermod -a -G sudo <username>


**TODO**: configure lf 1. cd where you switch 2. mkdir
**TODO**: enable `code`
**TODO**: always show hidden files finder
**TODO**: (bare repo?) add dotfiles to my repo with lf config, zsh config etc when everything is done
**TODO**: Ssh document with sshd_config
**TODO**: Linux file attributes
