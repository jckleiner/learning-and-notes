## SSH


### ssh_config vs sshd_config

The `sshd_config` is the ssh daemon (or ssh server process) configuration file. As you've already stated, this is the file you'll need to modify to change for example the server port, disable login with password, disable root login etc.

Whereas, the `ssh_config` file is the ssh client configuration file. The client configuration file only has bearing on when you use the ssh command to connect to another ssh host. So, in this case, you don't need to modify it. It will be other client machines connecting to your server.


### SSH keepalive

* `ServerAliveInterval`: number of seconds that the client will wait before sending a null packet to the server (to keep the connection alive).
* `ClientAliveInterval`: number of seconds that the server will wait before sending a null packet to the client (to keep the connection alive).

Setting a value of 0 (the default) will disable these features so your connection could drop if it is idle for too long.

ServerAliveInterval seems to be the most common strategy to keep a connection alive. To prevent the broken pipe problem, here is the ssh config I use in my `~/.ssh/config`(the client) file:
To enable sending a keep-alive signal for all hosts, place the following contents in the configuration file:
```
    Host *
        ServerAliveInterval 60
        ServerAliveCountMax 10
```

 * Linux: `sudo service ssh restart`
 * Mac (?): `sudo launchctl stop com.openssh.sshd && sudo launchctl start com.openssh.sshd`

 
