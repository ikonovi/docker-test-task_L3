package com.noblemarkets.testportal.controller;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqConnectionCheck {
    private static final Logger log = Logger.getLogger(RabbitmqConnectionCheck.class);

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(8080); // Default: 5672
        factory.setUsername("usr");
        factory.setPassword("pwd");
        Connection conn = null;
        try {
            conn = factory.newConnection();
            log.info("Connection was opened successfully.");
        } catch (IOException | TimeoutException exception) {
            log.info("Connection failed");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (IOException exception) {
                    // do nothing
                }
            }
        }
    }
}
