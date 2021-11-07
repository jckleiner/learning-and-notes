# Hyperledger Fabric and Minifabric
These examples use `minifabric` to manage fabric networks.

## Setup
1. Make sure you will invoke minifab commands from this directory and the config file is named `spec.yaml` and **NOT** `spec.yml` or else it will take the default spec file.
2. Download the `minifab` executable: `curl -o minifab -sL https://tinyurl.com/yxa2q6yr && chmod +x minifab`
3. Start a fabric network using the `spec.yml` in the this directory: `minifab up`. Minifabric by default:
    - Creates a channel called `mychannel` and adds all(?) the organizations to it?
    - Deploys the chaincode `simple` to all peers

After the setup is done, `docker ps` should look more or lesse like this:
```bash
    TODO
```

## Deploy your own chaincode (`chaincode-java`)
See also https://www.youtube.com/watch?v=r108lkV7auk&list=PL0MZ85B_96CExhq0YdHLPS5cmSBvSmwyO&index=3

0. Make sure your fabric network is running. Execute `minifab explorerup` to be able to see the blocks and channels in the GUI.
1. `mkdir -p ./vars/chaincode/chaincode-java/java && cp -R chaincode-java/* ./vars/chaincode/chaincode-java/java`
2. `minifab install -n chaincode-java -l java` 
3. `minifab approve`: After this command the chaincode is not installed on the peers (?), no containers up for this chaincode (those containers with really long names)
4. `minifab commit`: This probably installed the chaincode on peers. Now the chaincode containers are up.
5. `minifab initialize -p '"InitLedger"'` Calls the `InitLedger` method. This method creates 6 assets on the ledger with AssetID's: `asset1, ..., asset6`
6. `minifab invoke -p '"ReadAsset","asset3"'`: Should be result 200 with the data of `asset3` printed.
7. `minifab discover` ???
8. `minifab blockquery`: displays the last block in the ledger. The explorer does not show the block info like this.

## Multi Network Demo
See https://dilumbandara.medium.com/custom-hyperledger-fabric-network-with-minifabric-fd362ee34343

### org1
1. `cd multi-network-demo/org1 && minifab netup -e 7100 -o org1.example.com -i 2.2 -l node -s couchdb`
2. `minifab create,join -c channel1`
3. `minifab install,approve,commit -n simple -l node -v 1.0 -p '"init","a","200","b","300"'`

### org3
and install the chaincode using the following commands:

```bash
cd ../org3 && \
minifab netup -e 7300 -o org3.example.com -i 2.2 -l node -s couchdb && \
minifab create,join -c channel2 && \
minifab install,approve,commit -n simple -l node -v 1.0 -p '"init","a","200","b","300"'
```

### org2
`cd ../org2 && minifab netup -e 7200 -o org2.example.com -i 2.2 -l node -s couchdb`
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

`TODO` throws error: `The task includes an option with an undefined variable`

```bash
cd ../org1 && \
minifab approve,discover,commit && \
cd ../org3 && \
minifab approve,discover,commit
```

## TODOs
* How to create a new org in an existing network
* How to bind 2 docker networks with different organizations in it?
* Learn more about what `minifab install/approve/discover` etc. do exactly
* `minifab up` vs `minifab netup` and what are the other steps?
* Are organizations added to channels or peers? If an org is added, are all peers added automatically?
* Setup `application-java`, an example of how to develop a client for interacting with the fabric network
* Difference between `ChaincodeBase` and `ContractInterface`