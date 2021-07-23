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
    public ModelAndView getIndexPageModelAndView() {
        ModelAndView modelAndView = new ModelAndView("index");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmqSrv"); // Default: localhost
        factory.setPort(8080); // Default: 5672
        factory.setUsername("usr");
        factory.setPassword("pwd");

        try {
            this.conn = factory.newConnection();
            modelAndView.addObject("connectionStatus", "It's opened successfully.");
            log.info("RabbitMQ connection is open");
        } catch (IOException | TimeoutException exception) {
            String errorMessage = exception.getMessage();
            if (exception.getCause() != null) {
                errorMessage += " Cause: " + exception.getCause().getMessage();
            }
            modelAndView.addObject("connectionStatus", errorMessage);
            log.error("RabbitMQ connection failed", exception);
        } finally {
            if(conn != null) {
                try {
                    if (conn.isOpen()){
                        this.conn.close();
                    }
                } catch (IOException exception) {
                    //do nothing
                }
            }
        }

        return modelAndView;
    }

}
