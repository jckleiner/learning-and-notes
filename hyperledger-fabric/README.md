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

## Useful
* `minifab channelquery`, `minifab channelquery -c systemchannel`
* `minifab discover`
* `system channel VS application channel`: "If a new organization goes into application channel, then the new organization wont be able to create new channels. If a new organization goes into system channel, then the new organization will be able to create new channels."

## TODOs
* How to see which peers are endorsers? With `minifab discover`?
* How to list all channels? How to list all channels that an existing org belongs to?
* How to create a new org in an existing network
* How to bind 2 docker networks with different organizations in it?
* Learn more about what `minifab install/approve/discover` etc. do exactly
* `minifab up` vs `minifab netup` and what are the other steps?
* Are organizations added to channels or peers? If an org is added, are all peers added automatically?
* Setup `application-java`, an example of how to develop a client for interacting with the fabric network
* Difference between `ChaincodeBase` and `ContractInterface`