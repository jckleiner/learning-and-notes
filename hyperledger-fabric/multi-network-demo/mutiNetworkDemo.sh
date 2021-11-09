#!/bin/bash

cd org1 && \
minifab netup -e 7100 -o org1.example.com -i 2.3.2 -l node -s couchdb && \
cd multi-network-demo/org1 && minifab netup -e 7100 -o org1.example.com -i 2.2 -l node -s couchdb && \
minifab create,join -c channel1 && \
minifab install,approve,commit -n simple -l node -v 1.0 -p '"init","a","200","b","300"' && \


cd ../org3 && \
minifab netup -e 7300 -o org3.example.com -i 2.3.2 -l node -s couchdb && \
minifab create,join -c channel2 && \
minifab install,approve,commit -n simple -l node -v 1.0 -p '"init","a","200","b","300"' && \


cd ../org2 && minifab netup -e 7200 -o org2.example.com -i 2.3.2 -l node -s couchdb && \
cd ../org1 && \
cp ../org2/vars/JoinRequest_org2-example-com.json ./vars/NewOrgJoinRequest.json && \
minifab orgjoin,profilegen && \
cd ../org3 && \
cp ../org2/vars/JoinRequest_org2-example-com.json ./vars/NewOrgJoinRequest.json && \
minifab orgjoin,profilegen && \
cd ../org2 && \
cp ../org1/vars/profiles/endpoints.yaml vars && \
minifab nodeimport,join -c channel1 && \
cp ../org3/vars/profiles/endpoints.yaml vars && \
minifab nodeimport,join -c channel2 && \

### This throws an error
minifab install,approve -n simple -v 1.0 -p '"init","a","200","b","300"' -c channel1

