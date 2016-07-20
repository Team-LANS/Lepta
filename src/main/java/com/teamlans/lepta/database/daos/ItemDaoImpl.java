package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemDaoImpl implements ItemDao {

  private static SessionFactory factory;

  public ItemDaoImpl() throws LeptaDatabaseException {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Exception e) {
      throw new LeptaDatabaseException("Factory configuration failed.\n", e);
    }
  }

  public Integer addItem(Item newItem) throws LeptaDatabaseException {
    Transaction tx = null;
    Integer id;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      id = (Integer) session.save(newItem);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in addItem.\n", e);
    }
    return id;
  }

  public void deleteItem(Integer id) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      Item item = session.get(Item.class, id);
      session.delete(item);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in deleteItem.\n", e);
    }
  }

  public List listItems() throws LeptaDatabaseException {
    Transaction tx = null;
    List items;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      items = session.createQuery("FROM Item").list();
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in listItems.\n", e);
    }
    return items;
  }

  public void updateItem(Item newItem) throws LeptaDatabaseException {
    Transaction tx = null;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();

      Integer id = newItem.getId();
      Item item = session.get(Item.class, id);

      String newDescription = newItem.getDescription();
      item.setDescription(newDescription);

      double newPrice = newItem.getPrice();
      item.setPrice(newPrice);

      Set<User> newUsers = newItem.getUsers();
      Set<User> users = item.getUsers();
      Set<User> deletedUsers = new HashSet<>();
      for (User user : users) {
        if (!newUsers.contains(user)) {
          deletedUsers.add(user);
        }
      }
      item.removeUsers(deletedUsers);
      for (User newUser : newUsers) {
        if (!users.contains(newUser)) {
          item.addUser(newUser);
        }
      }

      session.update(item);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException("Transaction failed in updateItem.\n", e);
    }
  }
}