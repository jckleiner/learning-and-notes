## Virtualbox

### .ova vs .vdi Files

**OVA (Open Virtual Appliance):**
A file with the .OVA file extension is an Open Virtual Appliance file, sometimes called an Open Virtual Application file or an Open Virtualization Format Archive file. They're used by virtualization programs to store various files associated with a virtual machine (VM).

An Open Virtual Appliance file is stored in the Open Virtualization Format (OVF) as a TAR archive. Some of the files you might find within it include disk images (like VMDKs), an OVF descriptor XML-based text file, ISOs or other resource files, certificate files, and an MF manifest file.

Since the OVF format is a standard, it can be used by a virtual machine program to export the VM data files so that it can be imported into a different application. VirtualBox, for example, can export one of its VMs to an archive package with the .OVA file extension that includes an OVF and VMDK file. 

**VDI (Virtual Disk Image)** is the native format of VirtualBox. Other virtualization software generally don't support VDI, but it's pretty easy to convert from VDI to another format, especially with `qemu-img convert`.

**VMDK** is developed by and for VMWare, but VirtualBox and QEMU (another common virtualization software) also support it. This format might be the the best choice for someone with a goal of having wide compatibility with other virtualization software.

### Import

**Before an import make sure that there are:**
* no folders with the same name in `~/Virtualbox\ VMs` 
* no duplicate/broken registry entries with the same name `File -> Virtual Media Manager`

**Import a VDI File / Create a new VM from an existing VDI file (VirtualBox)**

Create a new virtual machine with the correct operating system. On the "Hard disk" selection, select `use an existing virtual hard disk file -> add` and select your `.vdi` file.

**Import an OVA File (VirtualBox)**

* `File -> Import Appliance -> Select the .ova file`

### Export

* `Right click the VM -> Export to OCI -> Format: OVF 1.0`

### Migrating to other VM-Software
**VMWare**
VMware Workstation can import an ova file.

**KVM / QEMU / etc.**
As far as I understand `.qcow2` is the file type which some of the other popular VM-Softwares use. So the `.ova` file (or the `.vmdk` file inside the `.ova` file to be precise) needs to be converted to a `.qcow2` file.

Ova files are tar archives containing various VM disk files in `.vmdk` format and suggested VM configuration in .ovf format.  `qemu-img` can be used to convert `.vmdk` to `.qcow2` which supports snapshots and then use in KVM.

### Saving State

After creating a new virtual machine or an OVA import, a new `.vdi` (or `.vmdk`) file is created in the `~/Virtualbox\ VMs` folder with the same name of the virtual machine. <ins>All future changes will be saved to that file, not to the file you imported.</ins>

**BUT! Be aware**, if you imported a VDI file (meaning if you created a new VM and selected an existing VDI file as the hard disk), all your changes will be saved on that file.

So your VM won't work anymore if you move the original `.vdi` file because Virtualbox keeps a reference to that file in its registry and saves all the changes you made to that file.

If you right click on a VM and remove it (without removing all files), then the registry will also be removed.

But if you first move the `.vdi` file and then remove it, the registry won't be updated. In that case you have to manually delete the registry entries:

* File -> Virtual Media Manager -> Removed existing images.

**Removing all files of a VM:** Be careful when removing imported VDI files, as explained above, if you right click on the VM and select remove + delete all files, your external/imported VDI file will also be deleted!

 * That's why prefer an OVA import over a VDI import!

### Snapshots

Snapshots can be created in order to save a specific state of a VM.

### Migrate OVA to Different Operating Systems

TODO, possible?

### Some Virtualbox errors

**VirtualBox Cannot register the hard disk already exists**

* File -> Virtual Media Manager -> Removed existing images (note, I removed them only from the registry).