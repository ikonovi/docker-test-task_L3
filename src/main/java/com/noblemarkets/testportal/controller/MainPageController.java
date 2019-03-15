package com.noblemarkets.testportal.controller;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class MainPageController {
    private  Connection conn = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getMainPage() throws IOException {
        ModelAndView result = new ModelAndView("index");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(8080);
        factory.setUsername("usr");
        factory.setPassword("pwd");

        this.conn = factory.newConnection();
        this.conn.close();

        return result;
    }

}
