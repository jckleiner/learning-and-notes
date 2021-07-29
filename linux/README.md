# GNU Linux


### Adding a new user with `useradd` (vs `adduser`)
https://linuxize.com/post/how-to-create-users-in-linux-using-the-useradd-command/

    useradd is native binary compiled with the system. But, adduser is a perl script which uses useradd binary in back-end.

    adduser is more user friendly and interactive than its back-end useradd. There's no difference in features provided.


The general syntax for the useradd command is as follows:

    useradd [OPTIONS] USERNAME

    # create new user with: home folder, sudo privileges and zsh as their default shell
    useradd -m -G sudo -s /usr/bin/zsh userName

    # create new user with sudo privileges and a password in one line
    PASS="123456"
    useradd -m -G sudo -p $(openssl passwd -crypt $PASS) userName

Only root or users with sudo privileges can use the useradd command to create new user accounts.

When invoked, useradd creates a new user account according to the options specified on the command line and the default values set in the `/etc/default/useradd` file. The variables defined in this file differ from distribution to distribution, which causes the useradd command to produce different results on different systems.

`useradd` also reads the content of the `/etc/login.defs` file. This file contains configuration for the shadow password suite such as password expiration policy, ranges of user IDs used when creating system and regular users, and more.
How to Create a New User in Linux.

The command adds an entry to the `/etc/passwd`, `/etc/shadow`, `/etc/group` and `/etc/gshadow` files.

* **Home Directory**: Use the `-m (--create-home)` option to create the user home directory as `/home/username`
* **Different Home Directory**: If you want to create the user’s home directory in other location, use the `-d (--home)` option.
* **Primary Group**: The `-g (--gid)` option allows you to create a user with a specific initial login group or primary group. If a user has no group specified, the user will be assigned to a new group whith the same username
* **Specify Secondary Groups**: There are two types of groups in Linux operating systems Primary group and Secondary (or supplementary) group. Each user can belong to exactly one primary group and zero or more secondary groups. You can specify a list of supplementary groups which the user will be a member of with the `-G (--groups)` option.
* **Give sudo privilages**: Add the user to the group `sudo` with `-G sudo`. Notice that we use capital G, which indicates we add new secondry groups!
* **Specific Login Shell**: By default, the new user's login shell is set to the one specified in the `/etc/default/useradd` file. In some distributions the default shell is set to `/bin/sh` while in others it is set to `/bin/bash`.
The `-s (--shell)` option allows you to specify the new user’s login shell.
For example, to create a new user named username with `/usr/bin/zsh` as a login shell type: ```sudo useradd -s /usr/bin/zsh username```
Check the user entry in the /etc/passwd file to verify the user’s login shell:
`sudo cat /etc/passwd`  ->  `username:x :1001:1001::/home/username:/usr/bin/zsh`


To be able to log in as the newly created user, you need to set the user password. To do that run the `passwd` command followed by the username:

`sudo passwd username`

### Remove user
In most Linux distributions, when removing a user account with userdel, the user home and mail spool directories are not removed. 
Use the `-r (--remove)` option to force userdel to remove the user’s home directory and mail spool:

    userdel -r username

## TODO sudo usermod -a -G sudo <username>
 * `-G (--groups)`: Assings the list of given groups as the secondary group list for the given user. 
    If `-a` flag is present, the given groups will be appended to the already present secondary group list of the user. 
    If not it OVERRIDES all the currently present secondary groups of the user. 
    So `usermod -G sudo jimmy` will override and `jimmy` will only have one secondary group, which is `sudo`
 * `-a (--append)`: Can only used with `usermod`. Append the group to the list of secondary groups the user belongs to

You can either create a user in one command with sudo privileges and a specific shell, for example `useradd -m -G sudo -s /bin/bash jimmy`.
This creates a primary group for the user named `jimmy` and assigns `sudo` as the secondary group.
With `useradd` we don't need `-a (--append)` since the user is not present and therefore has no groups assigned to him yet.

`groups` print a list of all groups the currently logged in user belongs to:
 * The first group is the primary group.

`groups userName` get a list of all groups a specific user belongs to, provide the username to the groups command as an argument.

## Super User / Root
 * The simplest and most straightforward method of obtaining root privileges is to directly log into your server as the root user.
   Logging in directly as root is usually not recommended, because it is easy to begin using the system for non-administrative tasks, which is dangerous.
 * The next way to gain super-user privileges allows you to become the root user at any time, as you need it.
   We can do this by invoking the `su` command, which stands for "substitute user", after which, you will be dropped into a root shell session.
   Type `exit` to return to your normal shell
 * The final, way of obtaining root privileges is the `sudo` command. The sudo command allows you to execute one-off commands with root privileges, without the need to spawn a new shell. Unlike `su`, the sudo command will request the password of the current user, not the root password.



## Sudoers File and the sudo group
Adding a user to the `sudo` group gives them sudo privileges because it is defined in the sudoers file `/etc/sudoers` (Ubuntu).
But the user will still be asked for a password before he can execute the command with sudo privileges.
Use `sudo visudo` and add this line: `YOUR_USERNAME_HERE ALL=(ALL) NOPASSWD: ALL`. This way the user won't have to enter a password when using sudo.
We could also give different permissions to a specific user but that's generally not a good idea.

 * This file MUST be edited with the `visudo` command as root. Because improper syntax in the `/etc/sudoers` file can leave you with a broken system where it is impossible to obtain elevated privileges, it is important to use the `visudo` command to edit the file. The `visudo` command opens a text editor like normal, but it validates the syntax of the file upon saving. This prevents configuration errors from blocking sudo operations, which may be your only way of obtaining root privileges.
 * Consider adding local content in `/etc/sudoers.d/` instead of directly modifying this file. Files within that directory follow the same rules as the `/etc/sudoers` file itself. Any file that does not end in `~` and that does not have a `.` in it will be read and appended to the sudo configuration.
  This is mainly meant for applications to alter sudo privileges upon installation. Putting all of the associated rules within a single file in the `/etc/sudoers.d` directory can make it easy to see which privileges are associated with which accounts and to reverse credentials easily without having to try to manipulate the `/etc/sudoers` file directly.

An example `/etc/sudoers` file

```
  Defaults        env_reset       # resets the terminal environment to remove any user variables
  
  # Send email notices of bad sudo password attempts to the configured mailto user. By default, this is the root account.
  Defaults        mail_badpass
  
  # The PATH that will be used for sudo operations
  Defaults        secure_path="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/snap/bin"
  
  # User privilege specification
  root    ALL=(ALL:ALL) ALL

  # Members of the admin group may gain root privileges
  # This group exists under Ubuntu for compatibility reasons. Up untill Ubuntu 11.10, admins used the admin group 
  # but in Ubuntu 12.04 this was changed and the sudo group was added
  %admin ALL=(ALL) ALL

  # Allows people in group wheel to run all commands
  %wheel  ALL=(ALL)   ALL

  # Allow members of group sudo to execute any command
  %sudo   ALL=(ALL:ALL) ALL

  #includedir /etc/sudoers.d
```
(For more: https://www.digitalocean.com/community/tutorials/how-to-edit-the-sudoers-file)

`root   ALL=(ALL:ALL) ALL`
 * The first field indicates the username that the rule will apply to (root).
 * The first "ALL" indicates that this rule applies to all hosts.
 * The second "ALL" indicates that the root user can run commands as all users.
 * The third "ALL" indicates that the root user can run commands as all groups.
 * The last "ALL" indicates these rules apply to all commands.

### Ports, External Incoming Requests and Stuff
Linux systems/servers does not have a mechanism enabled by default which blocks incoming TCP requests (for instance a firewall is not installed and enabled by default).

Which means, the operating system receives the incoming requests and looks if any programs or services are installed which are LISTENING on the port of the incoming request. If yes, then the request is forwarded to that service and the service can process and send a response back.

Which also means many different services can be accessed EXTERNALLY IF they are LISTENING also for external request. For example "listening on 0.0.0.0:5000 (or :::5000)" means AFAIK, a service/program is listening (and will accept that request / the OS will forward that request to this service) for requests which can come from ANY IP over the internet and the port should be 5000. If we have a Node application which listens on 0.0.0.0:5000, we could access that app directly from the outside world by calling the ip/domain name of the server and the port.

Most such applications which listen on different tcp ports generally only listen on certain ports for 127.0.0.1. So only requests which come from the same machhine will be forwarded and processed by that service. But some services also directly listen on 0.0.0.0. That's why it is a good idea to enable a firewall, for cases when the user is not aware that a program also listens for external requests.

This is also the reason many projects include a "reverse proxy" (apache or nging) listening on port 80 and 443, which then redirects that request to a service which listens ONLY for local requests on 127.0.0.1 (meaning sent by the current machine) on a different port. A Http server is much more configurable and powerful than just making the service (for example a node app or a docker container) listen on 0.0.0.0. 
Same concept for load balancing and so on. Also a http server will handle encrypted requests, it will decrypt them and forward them to each service.


## Firewall

Out of the box, Ubuntu ships with no TCP or UDP ports open, hence the belief that there's no reason to run Uncomplicated Firewall (ufw) by default. I agree, though, that having ufw disabled is a strange decision. My reasoning being that inexperienced users are feasibly going to install things like Samba, Apache and such like as they experiment with the system put before them. If they don't understand the implications of this, they will expose themselves to malicious bevaviour on the internet.

 * `UFW` – it is probably the most user-friendly firewall available in Linux. If you are a complete newbie or you just want to use your Linux without going to deep into its settings, use UFW.
 * `iptables` - which is a more advanced but probably a proper way to configure the Linux Firewall. If you really want to learn Linux and you aim to become a Linux expert, you need to learn iptables.

### UFW (Uncomplicated Firewall)
(https://www.digitalocean.com/community/tutorials/how-to-set-up-a-firewall-with-ufw-on-ubuntu-18-04)

UFW, or Uncomplicated Firewall, is an interface to iptables that is geared towards simplifying the process of configuring a firewall. While iptables is a solid and flexible tool, it can be difficult for beginners to learn how to use it to properly configure a firewall. If you’re looking to get started securing your network, and you’re not sure which tool to use, UFW may be the right choice for you.

 * The service ufw is disabled after installation. So even if the ssh connection breaks after the installation, you will still be able to access the server. (`service --status-all`)

If your Ubuntu server has IPv6 enabled, ensure that UFW is configured to support IPv6 so that it will manage firewall rules for IPv6 in addition to IPv4.

The first rules to define are your default policies. These rules control how to handle traffic that does not explicitly match any other rules.
 * By default, UFW is set to deny all incoming connections (`ufw default deny incoming`) and allow all outgoing connections (`ufw default allow outgoing`). 

Allowing SSH Connections: `sudo ufw allow ssh` same as `sudo ufw allow 22` (allow all connections on port 22). If you configured your SSH daemon to use a different port, you will have to specify the appropriate port. For example, if your SSH server is listening on port 2222, you can use this command to allow connections on that port.

Different applications can register their profiles with `UFW` upon installation. These profiles allow UFW to manage these applications by name. OpenSSH, the service allowing us to connect to our server now, has a profile registered with UFW.


### TODOs
  - rel noopener (how can a malicious site use Window.opener?) nofollow noreferrer
  - TURN/STUN server?
  - PPAs?
  - Free domain names? "es gibt ja freie .tk .ml .gq und mehr top level domains"
  - DNS check: `https://www.whatsmydns.net/#NS/<yourDomain.com>`
  - how-does-apt-get-really-work: https://unix.stackexchange.com/questions/377736/how-does-apt-get-really-work
  - `su` means super user or switch user/substitute user? What other options/flags are there?
  - different "workspaces" for x-server? cmd + f1,f2,f3...?
  - Ansible server setup (https://www.digitalocean.com/community/tutorials/how-to-use-ansible-to-automate-initial-server-setup-on-ubuntu-18-04)