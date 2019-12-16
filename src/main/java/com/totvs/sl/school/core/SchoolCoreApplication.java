package com.totvs.sl.school.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ SchoolProperties.class, FlyWayProperties.class })
public class SchoolCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolCoreApplication.class, args);
    }
}