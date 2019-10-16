#!/bin/bash
set -eu
# Copyright FUJITSU LIMITED 2019
# Creation Date: Oct 16, 2019

# Gets local machine IP address and inserts it into the Swagger's index.html file to set the default OpenApi JSON file location.
# @author Crystalzord

FILE="../webapp/index.html"

if [[ -f ${FILE} ]]; then
    TMP_OSCM_IP=$(dirname $(ip addr | grep "ens160" | grep "inet" | awk '{ print $2 }'))
    echo "Fetched IP address. Value: $TMP_OSCM_IP"
    sed -i "s/oscm-ip/$TMP_OSCM_IP/g" "../webapp/index.html"
    echo "Updated the index.html file."
    exit 0;
else
    echo "index.html file not found!"
    exit 1;
fi