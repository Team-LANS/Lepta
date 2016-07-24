package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Item;
import com.teamlans.lepta.database.entities.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ItemDaoImpl implements ItemDao {

  private static final Logger logger = LoggerFactory.getLogger(ItemDao.class);

  @Autowired
  private SessionFactory factory;

  @Override
  public Integer addItem(Item newItem)  {
    logger.debug("Adding item with {}", newItem);
    return (Integer) factory.getCurrentSession().save(newItem);
  }

  @Override
  public void deleteItem(Integer id) {
    logger.debug("Deleting item with id {}", id);
    Session session = factory.getCurrentSession();
    Item item = session.get(Item.class, id);
    factory.getCurrentSession().delete(item);
  }

  @Override
  public void updateItem(Item newItem)  {
    logger.debug("Updating item with {}", newItem);
    Session session = factory.getCurrentSession();

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
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Item> listItems() {
    logger.debug("Listing items...");
    return factory.getCurrentSession().createQuery("FROM Item").list();
  }
}
