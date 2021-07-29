package com.noblemarkets.testportal.controller;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class IndexPageController {
    private static final Logger log = Logger.getLogger(IndexPageController.class);
    private Connection conn;

    @GetMapping(value = "/")
    public ModelAndView getIndexPageModelAndView() throws Exception {
        ModelAndView modelAndView = new ModelAndView("index");

        String hostname = "rabbitmq"; // "rabbitmq" - service name in docker-compose file
        int port = 8080; // Default: 5672
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(hostname);
        factory.setPort(port);
        factory.setUsername("usr");
        factory.setPassword("pwd");

        try {
            this.conn = factory.newConnection();
            modelAndView.addObject("connectionStatus", "It's opened successfully.");
            log.info("Connected to RabbitMQ at " + hostname + ":" + port);
        } catch (IOException | TimeoutException exception) {
            log.error("Could not connect at " + hostname + ":" + port, exception);
            throw new Exception(exception);
        } finally {
            if(conn != null) {
                try {
                    if (conn.isOpen()){
                        log.info("Connection is closed.");
                        this.conn.close();
                    }
                } catch (IOException exception) {
                    log.error("Could not close connecting at " + hostname + ":" + port, exception);
                }
            }
        }

        return modelAndView;
    }

}
