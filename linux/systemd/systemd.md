# Systemd

## /etc/systemd/system/ vs /usr/lib/systemd/system/ vs /lib/systemd/system/
Both `/etc/systemd/system/` and `/usr/lib/systemd/system/` are directories that contain systemd unit files, which are used to define and manage system services, sockets, timers, and other system resources.

However, there are some key differences between the two directories:

 1. `/etc/systemd/system/` is used to store user-defined unit files and overrides for system unit files. These files are intended to be modified by system administrators to customize system behavior.
 2. `/usr/lib/systemd/system/` is used to store unit files that are provided by the installed packages on the system. These files should not be modified by system administrators, as they may be overwritten when the package is updated.
 3. Systemd loads unit files from `/etc/systemd/system/` directory before the files from `/usr/lib/systemd/system/` directory. This means that if there is a user-defined unit file with the same name as a system-provided unit file, the user-defined file will take precedence.
 4. `/usr/lib/systemd/system/` directory contains additional directories such as /usr/lib/systemd/system-preset/ which contains preset unit files that define default behavior of certain services.

In summary, `/etc/systemd/system/` is for customizing or overriding system-wide unit files, while `/usr/lib/systemd/system/` is for package-provided unit files that should not be modified.

## What happens if the same service file exists in both `/etc/systemd/system/` and `/usr/lib/systemd/system/`?
If the same service file exists in both `/etc/systemd/system/` and `/usr/lib/systemd/system/`, then the version in `/etc/systemd/system/` takes precedence over the version in `/usr/lib/systemd/system/`.

## systemd using wrong service file

I've created a `/etc/systemd/system/varnish.service` file but when I ran `sudo systemctl start varnish` it still used the service file from `/lib/systemd/system/varnish.service`

To solve this a reload is needed: `systemctl daemon-reload`

## What does `systemctl daemon-reload` do?
When you make changes to a systemd unit file, such as modifying the service configuration, you need to run the "systemctl daemon-reload" command to reload the daemon configuration. This ensures that the changes are picked up by the systemd manager.

The "systemctl daemon-reload" command reloads the systemd configuration files and the unit files, and refreshes the systemd process to reflect any changes made. After running this command, any modifications made to the system's unit files will be available to the systemd manager.

It's important to note that **running this command will not start or stop any services**. It simply reloads the configuration files, making any changes you made to them available to systemd.

## Systemd specifiers - Nasty bug when a text in unit file contains %
There are many specifiers in systemd unit files which start with `%`, for example `%n` resolves to the full unit name. (See https://www.freedesktop.org/software/systemd/man/systemd.unit.html#Specifiers)
If you have a password which you put in your unit file for example via jinja template, there is a risk of a sneaky bug.
If that password will contain a specifier, it will be resolved and your password will be wrong. 

For example if the password is `123%n456`, it will be resolved to `123my_service_name456`
 * Use `%%` in place of `%` to specify a single percent sign.
 * This won't even be spotted if you just read the resolved unit file because the password will look correct (for example `123%n456`) but if you'll use or print the password, it will be `123my_service_name456`

## ...