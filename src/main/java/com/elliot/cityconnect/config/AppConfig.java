package com.elliot.cityconnect.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.elliot.cityconnect")
@PropertySource("classpath:application.properties")
public class AppConfig {

}
