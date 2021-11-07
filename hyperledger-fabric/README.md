# Hyperledger Fabric and Minifabric
These examples use `minifabric` to manage fabric networks.

## Setup
1. Make sure you will invoke minifab commands from this directory and the config file is named `spec.yaml` and NOT `spec.yml` or else it will take the default spec file.
2. Download the `minifab` executable: `curl -o minifab -sL https://tinyurl.com/yxa2q6yr && chmod +x minifab`
3. Start a fabric network using the `spec.yml` in the this directory: `minifab up`. Minifabric by default:
    - Creates a channel called `mychannel` and adds all(?) the organizations to it?
    - asd

## Deploy your own chaincode
TODO

### TODOs
* How to create a new org in an existing network
* `minifab up` vs `minifab netup` and what are the other steps?
* How to bind 2 docker networks with different organizations in it?
* Are organizations added to channels or peers? If an org is added, are all peers added automatically?