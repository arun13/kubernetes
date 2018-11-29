package com.kubernetes.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class AccountWithdrawalController {

    @RequestMapping("/account-withdrawal")
    public String hello() {
        StringBuilder message = new StringBuilder("Hello this is account withdrawal service!");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            message.append(" From host: " + ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return message.toString();
    }

}
