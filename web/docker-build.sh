#!/bin/bash

REGISTRY=http://192.168.0.13:5000
PROJECT=infra
MODULE=op-demo-kafka-web
VERSION=${1:-1.0.0}
TAG=${2:-latest}

#docker build -t $PROJECT/$MODULE:$VERSION .
podman build -t $PROJECT/$MODULE:$TAG .
#docker tag $PROJECT/$MODULE:$VERSION $REGISTRY/$PROJECT/$MODULE:$VERSION
podman tag $PROJECT/$MODULE:$TAG $REGISTRY/$PROJECT/$MODULE:$TAG
#docker push $REGISTRY/$PROJECT/$MODULE:$VERSION
podman push -f v2s2 --tls-verify false $REGISTRY/$PROJECT/$MODULE:$TAG