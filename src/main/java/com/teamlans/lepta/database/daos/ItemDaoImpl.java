package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.entities.User;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Set;

public class ItemDaoImpl implements ItemDao {
  private static SessionFactory factory;

  public ItemDaoImpl() throws LeptaDatabaseException {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Exception e) {
      throw new LeptaDatabaseException();
    }
  }

  public Integer addItem(String description, double price, Bill bill) throws LeptaDatabaseException {
    Transaction tx = null;
    Integer id;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      Item item = new Item(description, price, bill);
      id = (Integer) session.save(item);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException();
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
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException();
    }
  }

  public List listItems() throws LeptaDatabaseException {
    Transaction tx = null;
    List items;
    try (Session session = factory.openSession()) {
      tx = session.beginTransaction();
      items = session.createQuery("FROM Item").list();
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException();
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
      for (User user : users) {
        if (!newUsers.contains(user)) {
          item.removeUser(user);
        }
      }
      for (User newUser : newUsers) {
        if (!users.contains(newUser)) {
          item.addUser(newUser);
        }
      }

      session.update(item);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      throw new LeptaDatabaseException();
    }
  }
}