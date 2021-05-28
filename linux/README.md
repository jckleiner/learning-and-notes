# GNU Linux


### Adding a new user with `useradd` (vs `adduser`)
https://linuxize.com/post/how-to-create-users-in-linux-using-the-useradd-command/

    useradd is native binary compiled with the system. But, adduser is a perl script which uses useradd binary in back-end.

    adduser is more user friendly and interactive than its back-end useradd. There's no difference in features provided.


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
* **Specify Secondary Groups**: There are two types of groups in Linux operating systems Primary group and Secondary (or supplementary) group. Each user can belong to exactly one primary group and zero or more secondary groups. You can specify a list of supplementary groups which the user will be a member of with the `-G (--groups)` option.
* **Give sudo privilages**: Add the user to the group `sudo` with `-G sudo`. Notice that we use capital G, which indicates we add new secondry groups!
* **Specific Login Shell**: By default, the new user's login shell is set to the one specified in the `/etc/default/useradd` file. In some distributions the default shell is set to `/bin/sh` while in others it is set to `/bin/bash`.
The `-s (--shell)` option allows you to specify the new user’s login shell.
For example, to create a new user named username with `/usr/bin/zsh` as a login shell type: ```sudo useradd -s /usr/bin/zsh username```
Check the user entry in the /etc/passwd file to verify the user’s login shell:
`sudo cat /etc/passwd`  ->  `username:x :1001:1001::/home/username:/usr/bin/zsh`


To be able to log in as the newly created user, you need to set the user password. To do that run the passwd command followed by the username:

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

## TODO difference between having `sudo` as secondary group vs sudoers???
 * sudoers???

## Super User / Root
 * The simplest and most straightforward method of obtaining root privileges is to directly log into your server as the root user.
   Logging in directly as root is usually not recommended, because it is easy to begin using the system for non-administrative tasks, which is dangerous.
 * The next way to gain super-user privileges allows you to become the root user at any time, as you need it.
   We can do this by invoking the `su` command, which stands for "substitute user", after which, you will be dropped into a root shell session.
   Type `exit` to return to your normal shell
 * The final, way of obtaining root privileges is the `sudo` command. The sudo command allows you to execute one-off commands with root privileges, without the need to spawn a new shell. Unlike `su`, the sudo command will request the password of the current user, not the root password.



## Sudoers File and the sudo group
Adding a user to the `sudo` group gives them sudo privileges because it is defined in the sudoers file `/etc/sudoers` (Ubuntu).
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



### TODOs
  - `su` means super user or switch user/substitute user? What other options/flags are there?
  - different "workspaces" for x-server? cmd + f1,f2,f3...?