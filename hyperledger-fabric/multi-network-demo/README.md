## Multi Network Demo
See https://dilumbandara.medium.com/custom-hyperledger-fabric-network-with-minifabric-fd362ee34343

### org1


"minifab netup -e 7100 -o org1.example.com -i 2.3.2 -l node -s couchdb"

1. `cd multi-network-demo/org1 && minifab netup -e 7100 -o org1.example.com -i 2.2 -l node -s couchdb`
2. `minifab create,join -c channel1`
3. `minifab install,approve,commit -n simple -l node -v 1.0 -p '"init","a","200","b","300"'`

### org3

```bash
cd ../org3 && \
minifab netup -e 7300 -o org3.example.com -i 2.3.2 -l node -s couchdb && \
minifab create,join -c channel2 && \
minifab install,approve,commit -n simple -l node -v 1.0 -p '"init","a","200","b","300"'
```

### org2
`cd ../org2 && minifab netup -e 7200 -o org2.example.com -i 2.3.2 -l node -s couchdb`
Now that network2 is running, we can add it to the 2 application channels. We need to do this one channel at a time using the details in the `JoinRequest_org2-example-com.json` file produced by Minifabric. We use the recently introduced `orgjoin` command to add new nodes. We’ll also generate the profile files as we need them to import orderers to org2 and join its peer to the 2 channels.

```bash
cd ../org1 && \
cp ../org2/vars/JoinRequest_org2-example-com.json ./vars/NewOrgJoinRequest.json && \
minifab orgjoin,profilegen && \
cd ../org3 && \
cp ../org2/vars/JoinRequest_org2-example-com.json ./vars/NewOrgJoinRequest.json && \
minifab orgjoin,profilegen
```

```
cd ../org2 && \
cp ../org1/vars/profiles/endpoints.yaml vars && \
minifab nodeimport,join -c channel1 && \
cp ../org3/vars/profiles/endpoints.yaml vars && \
minifab nodeimport,join -c channel2
```
Install the chaincode on Org2:
```bash
minifab install,approve -n simple -v 1.0 -p '"init","a","200","b","300"' -c channel1
```
    # Running operation: ******************************************
    cc approve
    .....
    # Run the channel signing off script on cli container *********
    non-zero return code
    Error: failed to send transaction: got unexpected status: NOT_FOUND -- channel does not exist

    # STATS *******************************************************
    minifab: ok=45  failed=1


```bash
cd ../org1 && \
minifab approve,discover,commit
```

    # Running operation: ******************************************
    cc commit
    ..........
    # Select a random endorser group ******************************
    The task includes an option with an undefined variable. The error was: 'dict object' has no attribute 'Layouts'
    
    The error appears to be in '/home/playbooks/common/peerselection.yaml': line 25, column 5, but may
    be elsewhere in the file depending on the exact syntax problem.
    
    The offending line appears to be:
    
    
        - name: Select a random endorser group
        ^ here
    

    # STATS *******************************************************
    minifab: ok=67  failed=1


```
cd ../org3 && \
minifab approve,discover,commit
```
