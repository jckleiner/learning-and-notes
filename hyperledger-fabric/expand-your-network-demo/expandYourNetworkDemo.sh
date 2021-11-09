#!/bin/bash

cd ./mysite0 && \
minifab up -e 7000 -n samplecc -p '' && \

cd ../mysite1 && \
minifab netup -e 7200 -o orgx.example.com && \

echo " ----- 1 -----" && \

# Join orgx.example.com to the application channel with the following step
cd ../mysite0 && \
cp ../mysite1/vars/JoinRequest_orgx-example-com.json vars/NewOrgJoinRequest.json && \
minifab orgjoin && \

echo " ----- 2 -----" && \

# Import orderer nodes to orgx.example.com and join peers of orgx.example.com to the mychannel
cd ../mysite1 && \
cp ../mysite0/vars/profiles/endpoints.yaml vars && \
minifab nodeimport,join && \

echo " ----- 3 -----" && \

# Install and approve chaincode samplecc for orgx peers
cd ../mysite1 && \
minifab install,approve -n samplecc -p '' && \

echo " ----- 4 -----" && \

# Approve the chaincode on org0 and org1
# Since new orgs joined, the chaincode will need to be approved again so that new org can also commit

cd ../mysite0 && \
minifab approve,discover,commit && \

echo " ----- 5 -----" && \

# Discover and verify the chaincode on orgx
cd ../mysite1 && \
minifab discover && \
minifab stats
