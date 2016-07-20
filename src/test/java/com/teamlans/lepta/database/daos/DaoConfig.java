package com.teamlans.lepta.database.daos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class DaoConfig {

  @Bean public UserDao userDao() {
    return new UserDaoImpl();
  }

  @Bean public BillDao billDao() {
    return new BillDaoImpl();
  }

  @Bean public ItemDao itemDao() {
    return new ItemDaoImpl();
  }

}
