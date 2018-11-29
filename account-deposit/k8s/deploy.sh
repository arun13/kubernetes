#!/usr/bin/env bash
 kubectl apply -f ./account/k8s/account-deposit-deployment.tpl.yml
 kubectl apply -f ./account/k8s/account-deposit-service.tpl.yml