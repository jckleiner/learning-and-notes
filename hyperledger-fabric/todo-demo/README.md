# Run 1, 2
 * failed after 4
 * Each spec.yaml has one org with 2 peers

```
fabric:
  peers:
  - "peer1.org0.example.com"
  - "peer2.org0.example.com"
  orderers:
  - "orderer1.example.com"
  netname: "n1"

  fabric:
  peers:
  - "peer1.orgx.example.com"
  - "peer2.orgx.example.com"
  netname: "n2"
```


        # Discover orderers results ***********************************
        Channel orderers file: ./vars/discover/mychannel/ordererendpoints.json
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

# Run 3 
 * WORKS
 

```
fabric:
  peers:
  - "peer1.org0.example.com"
  - "peer2.org0.example.com"
  - "peer1.org1.example.com"
  - "peer2.org1.example.com"
  orderers:
  - "orderer1.example.com"
  netname: "n1"

  fabric:
  peers:
  - "peer1.orgx.example.com"
  - "peer2.orgx.example.com"
  netname: "n2"
```