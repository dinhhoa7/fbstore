package com.hoavd.fbstore.admin;

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
public class FbStoreAdminApplication {
  public static void main(String[] args) {
    SpringApplication.run(FbStoreAdminApplication.class, args);
  }
}