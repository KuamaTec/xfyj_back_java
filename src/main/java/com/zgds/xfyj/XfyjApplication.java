package com.zgds.xfyj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class XfyjApplication {

    public static void main(String[] args) {
        SpringApplication.run(XfyjApplication.class, args);
    }

}
