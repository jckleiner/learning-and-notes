## SSH

#### SSH keepalive

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

 
