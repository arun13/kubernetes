package com.kubernetes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@RestController
public class AccountDepositController {


    @Value("server.port")
    String server_port;

    @RequestMapping("/account-deposit")
    public String hello() {
        StringBuilder message = new StringBuilder("Hello This is account deposit service!");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            message.append(" From host: " + ip);
            log.info("Account Deposit Service is working on port "+server_port);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return message.toString();
    }

}
