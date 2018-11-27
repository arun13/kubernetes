#!/usr/bin/env bash
 kubectl apply -f ./account/k8s/account-deployment.tpl.yml
 kubectl apply -f ./account/k8s/account-service.tpl.yml