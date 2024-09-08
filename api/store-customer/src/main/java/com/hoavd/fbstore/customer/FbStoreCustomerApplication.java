package com.hoavd.fbstore.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.hoavd.fbstore.*" })
@EnableJpaRepositories(basePackages = { "com.hoavd.fbstore.*.repository" })
@ComponentScan(basePackages = { "com.hoavd.fbstore.*" })
@EntityScan("com.hoavd.fbstore.*")
@EnableAspectJAutoProxy
public class FbStoreCustomerApplication {
  public static void main(String[] args) {
    SpringApplication.run(FbStoreCustomerApplication.class, args);
  }
}