#!/bin/bash

cd mysite0 && minifab down && \
cd ../mysite1 && minifab down -o orgx.example.com && \

cd ../mysite0 && minifab cleanup && \
cd ../mysite1 && minifab cleanup -o orgx.example.com