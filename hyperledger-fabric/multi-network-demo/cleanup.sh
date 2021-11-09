#!/bin/bash

cd org1 && minifab down -o org1.example.com && \
cd ../org2 && minifab down -o org2.example.com && \
cd ../org3 && minifab down -o org3.example.com && \

cd ../org1 && minifab cleanup -o org1.example.com && \
cd ../org2 && minifab cleanup -o org2.example.com && \
cd ../org3 && minifab cleanup -o org3.example.com