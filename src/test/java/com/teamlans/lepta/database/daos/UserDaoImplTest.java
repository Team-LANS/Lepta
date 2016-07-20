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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfiguration.class,
    DaoConfig.class}, loader = AnnotationConfigContextLoader.class) @Transactional
public class UserDaoImplTest {

  @Autowired private UserDao userDao;

  @Autowired private BillDao billDao;

  @Autowired private ItemDao itemDao;

  @Test public void addUser_addValidUser_existsInDatabase() throws Exception {
    int oldUserCount = userDao.listUsers().size();

    userDao.addUser(new User(3, "name", "password", Color.YELLOW));

    int newUserCount = userDao.listUsers().size();
    assertEquals(oldUserCount + 1, newUserCount);
  }

//  @Test(expected = LeptaDatabaseException.class)
//  public void addUser_addUserWithExistingColor_ExceptionThrown() throws Exception {
//    List<User> users = userDao.listUsers();
//    userDao.addUser(new User(3, "name", "password", Color.BLUE));
//  }
//
//  @Test public void listUser_validUsersListed_returnsUsers() throws Exception {
//    List<User> users = userDao.listUsers();
//
//    assertFalse(users.isEmpty());
//  }
//
//  @Test public void deleteUser_validUser_userRemoved() throws Exception {
//    int oldUserCount = userDao.listUsers().size();
//
//    userDao.deleteUser(1);
//
//    int newUserCount = userDao.listUsers().size();
//    assertEquals(oldUserCount - 1, newUserCount);
//  }
//
//  @Test public void deleteUser_validUser_BillsAndItemsRemoved() throws Exception {
//    int oldItemCount = itemDao.listItems().size();
//    int oldBillCount = billDao.listBills().size();
//
//    userDao.deleteUser(1);
//
//    int newItemCount = itemDao.listItems().size();
//    int newBillCount = billDao.listBills().size();
//    assertTrue(oldBillCount > newBillCount);
//    assertTrue(oldItemCount > newItemCount);
//  }
//
//  @Test(expected = LeptaDatabaseException.class)
//  public void deleteUser_invalidUser_ExceptionThrown() throws Exception {
//    userDao.deleteUser(-1);
//  }



}
