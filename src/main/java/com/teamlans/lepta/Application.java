package com.teamlans.lepta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hans-Joerg Schroedl
 */
@SpringBootApplication
@ComponentScan
public class Application {


  public static void main(String[] args) {
    Main main = new Main();
    main.start(args);
  }

  private static class Main {

    private ConfigurableApplicationContext context;

    void start(String[] args) {
      context = SpringApplication.run(Application.class, args);
    }

    public void stop() {
      context.close();
    }

  }


}
