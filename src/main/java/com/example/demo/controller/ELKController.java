package com.example.demo.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
class ELKController {
    private static final Logger LOG = LoggerFactory.getLogger(ELKController.class);

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/elk")
    public String helloWorld() {
        String response = "hi namaste....." + new Date();
        LOG.info(response);
        return response;
    }

    @GetMapping("/exception")
    public String exception() {
        String response = "";
        try {
            throw new Exception("Exception has occurred....");
        } catch (Exception e) {
            LOG.error("Exception occurred", e);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            LOG.error("Exception - {}", stackTrace);
            response = stackTrace;
        }
        return response;
    }
}
