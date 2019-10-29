#!/bin/bash
set -eu
# Copyright FUJITSU LIMITED 2019
# Creation Date: Oct 16, 2019

# Gets local machine IP address and inserts it into the Swagger's index.html file to set the default OpenApi JSON file location.
# This should be executed from the script's location directory after cloning the repository (before compiling).
# @author Crystalzord

INDEX_FILE="../webapp/index.html"
DEFINITION_FILE="../java/org/oscm/rest/swagger/SwaggerBootstrap.java"

if [[ -f ${INDEX_FILE} && -f ${DEFINITION_FILE} ]]; then
    TMP_OSCM_IP=$(dirname $(ip addr | grep "ens160" | grep "inet" | awk '{ print $2 }'))
    echo "Fetched IP address. Value: $TMP_OSCM_IP"

    sed -i "s/oscm-ip/$TMP_OSCM_IP/g" ${INDEX_FILE}
    echo "Updated the index.html file."

    sed -i "s/oscm-ip/$TMP_OSCM_IP/g" ${DEFINITION_FILE}
    echo "Updated the SwaggerBootstrap.java file."

    unset INDEX_FILE
    unset DEFINITION_FILE
    exit 0;
else
    echo "index.html or SwaggerBootstrap.java file not found!"
    unset INDEX_FILE
    unset DEFINITION_FILE
    exit 1;
fi