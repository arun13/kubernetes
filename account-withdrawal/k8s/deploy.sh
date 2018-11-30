#!/usr/bin/env bash
 kubectl apply -f ./account-withdrawal/k8s/account-withdrawal-deployment.tpl.yml
 kubectl apply -f ./account-withdrawal/k8s/account-withdrawal-service.tpl.yml