package com.redd4ford.insteadit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan({"com.redd4ford.insteadit.*"})
@EnableJpaRepositories({"com.redd4ford.insteadit.repository"})
@EntityScan(
    basePackageClasses = {Application.class, Jsr310JpaConverters.class}
)
@EnableAsync
public class Application {

  public static void main(String[] args) throws Throwable {
    SpringApplication.run(Application.class, args);
  }

}
