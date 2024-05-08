package com.redhat.demo.openshift.web.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

@SpringBootApplication(exclude = {JmsAutoConfiguration.class, DataSourceAutoConfiguration.class, })
//@EntityScan(basePackages = {"com.redhat.demo.openshift.web.rest.entities"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
