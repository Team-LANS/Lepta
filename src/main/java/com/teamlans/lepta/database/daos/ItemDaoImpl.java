package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Item;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {

  private static final Logger logger = LoggerFactory.getLogger(ItemDao.class);

  @Autowired
  private SessionFactory factory;

  @Override
  public Integer addItem(Item newItem) {
    logger.debug("Adding item with {}", newItem);
    return (Integer) factory.getCurrentSession().save(newItem);
  }

  @Override
  public void deleteItem(Integer id) {
    logger.debug("Deleting item with id {}", id);
    Session session = factory.getCurrentSession();
    Item item = session.get(Item.class, id);
    if (item == null) {
      throw new DataRetrievalFailureException("Could not delete item with id +" + id);
    }
    factory.getCurrentSession().delete(item);
  }

  @Override
  public void updateItem(Item newItem) {
    logger.debug("Updating item with {}", newItem);
    Session session = factory.getCurrentSession();
    session.update(newItem);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Item> listItems() {
    logger.debug("Listing items...");
    return factory.getCurrentSession().createQuery("FROM Item").list();
  }
}
