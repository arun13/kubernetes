package com.kubernetes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class AccountController {

    @Value("server.port")
    String server_port;

    @RequestMapping("/account")
    public String hello() {
        StringBuilder message = new StringBuilder("Hello This is account service!");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            message.append(" From host: " + ip);
            System.out.println("Account Service working on port "+server_port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return message.toString();
    }

}