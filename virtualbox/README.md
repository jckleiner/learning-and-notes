## Virtualbox

### .ova vs .vdi Files

**TODO**

### Import / Export

**TODO**

### Moving .vdi Files

Be aware when moving `.vdi` files because Virtualbox keeps a reference to that file in its registry. 
If you right click on the virtual machine and remove it (without removing all files), then the registry will also be removed.

But if you first move the `.vdi` file then VBox can't find it anymore and on deletion it won't update its registry. In that case you have to manually delete the registry entries:

* File -> Virtual Media Manager -> Removed existing images (note, I removed them only from the registry).


### Some Virtualbox errors

**VirtualBox Cannot register the hard disk already exists**

* File -> Virtual Media Manager -> Removed existing images (note, I removed them only from the registry).