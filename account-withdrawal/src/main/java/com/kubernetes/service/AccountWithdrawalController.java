package com.kubernetes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@RestController
public class AccountWithdrawalController {

    @Value("${server.port}")
    String serverPort;

    @RequestMapping("/account-withdrawal")
    public String hello() {
        StringBuilder message = new StringBuilder("Hello this is account withdrawal service!");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            message.append(" From host: " + ip);
            log.info("Hello this is account withdrawal service!");
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }
        return message.toString();
    }

}
