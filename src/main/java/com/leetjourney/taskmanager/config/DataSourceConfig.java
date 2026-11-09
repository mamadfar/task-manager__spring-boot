package com.leetjourney.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration 
public class DataSourceConfig {

    public void showMsg(String msg) {
        System.out.println(msg);
    }
    public void showMsg(String msg, String description) {
        System.out.println(msg);
        if (description != null) {
            System.out.println(description);
        }
    }

    @Bean
    @Profile("dev")
    public boolean printDevMsg() {
        showMsg("--------------------------------");
        showMsg("DEVELOPMENT ENVIRONMENT");
        showMsg("--------------------------------");
        showMsg("H2 In-Memory Database", "Fast, for development");
        return true;
    }

    @Bean
    @Profile("stg")
    public boolean printStgMsg() {
        showMsg("--------------------------------");
        showMsg("STAGING ENVIRONMENT");
        showMsg("--------------------------------");
        showMsg("We don't have a staging environment yet");
        return false;
    }

    @Bean
    @Profile("prod")
    public boolean printProdMsg() {
        showMsg("--------------------------------");
        showMsg("PRODUCTION ENVIRONMENT");
        showMsg("--------------------------------");
        showMsg("PostgreSQL Database", "Reliable, for production"); 
        return true;
    }
}
