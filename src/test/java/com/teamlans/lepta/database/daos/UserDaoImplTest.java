package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.HibernateTestConfiguration;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.enums.Color;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfiguration.class,
    DaoConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserDaoImplTest {

  @Autowired
  private UserDao userDao;

  @Test
  public void addUser_addValidUser_existsInDatabase() throws Exception {
    int oldUserCount = userDao.listUsers().size();

    userDao.addUser(new User(3, "name", "password", Color.YELLOW));

    int newUserCount = userDao.listUsers().size();
    assertEquals(oldUserCount + 1, newUserCount);
  }

  @Test
  public void listUser_validUsersListed_returnsUsers() throws Exception {
    List<User> users = userDao.listUsers();
    assertFalse(users.isEmpty());
  }

  @Test
  public void updateUser_validUser_userUpdated() throws Exception {
    User user = userDao.listUsers().get(0);
    user.setName("NewName");

    userDao.updateUser(user);

    User newUser = userDao.listUsers().get(0);
    assertEquals(newUser.getName(), "NewName");
  }
}
