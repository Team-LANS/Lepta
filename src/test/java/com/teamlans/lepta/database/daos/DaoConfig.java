package com.teamlans.lepta.database.daos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class DaoConfig {

  @Bean public UserDao userDao() {
    return new UserDaoImpl();
  }

  @Bean @Autowired public BillDao billDao(SessionFactory sessionFactory) {
    return new BillDaoImpl();
  }

  @Bean public ItemDao itemDao() {
    return new ItemDaoImpl();
  }

}
