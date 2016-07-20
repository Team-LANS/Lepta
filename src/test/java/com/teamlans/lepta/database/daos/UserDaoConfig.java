package com.teamlans.lepta.database.daos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoConfig {

    // this bean will be injected into the OrderServiceTest class
    @Bean
    public UserDao userDao() {
      UserDao userDao = new UserDaoImpl();
      // set properties, etc.
      return userDao;
    }

}
