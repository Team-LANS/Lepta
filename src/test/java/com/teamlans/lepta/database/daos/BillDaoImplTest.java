package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.HibernateTestConfiguration;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Color;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfiguration.class,
    UserDaoConfig.class}, loader = AnnotationConfigContextLoader.class) @Transactional
public class BillDaoImplTest {

  @Autowired private UserDao userDao;

  @Test public void addUser_addValidUser_existsInDatabase() throws Exception {
    int oldUserCount = userDao.listUsers().size();
    userDao.addUser(new User(3, "name", "password", Color.YELLOW));
    int newUserCount = userDao.listUsers().size();
    assertEquals(oldUserCount + 1, newUserCount);
  }




}
