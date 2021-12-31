#!/bin/bash

CLI="swagger-cli/swagger-codegen-cli-3.0.30.jar"

SWAGGER="../../api-spec/m-site.yaml"
PACKAGE="org.ubf.model.m-site"
DEST="m-site/1.0.0"

java -jar ${CLI} generate -i "${SWAGGER}" -l java -o "${DEST}" --model-package "${PACKAGE}" -D models

# Make model notes:
