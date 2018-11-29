#!/usr/bin/env bash
 kubectl apply -f ./account/k8s/account-withdrawal-deposit-deployment.tpl.yml
 kubectl apply -f ./account/k8s/account-withdrawal-deposit-service.tpl.yml