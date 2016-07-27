package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.HibernateTestConfiguration;
import com.teamlans.lepta.entities.Item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfiguration.class,
    DaoConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ItemDaoImplTest {

  @Autowired
  private ItemDao itemDao;

  @Test
  public void addItem_validItem_ItemAdded() throws Exception {
    int oldItemCount = itemDao.listItems().size();

    itemDao.addItem(new Item("Description", 1.0));

    int newItemCount = itemDao.listItems().size();
    assertEquals(oldItemCount + 1, newItemCount);
  }

  @Test(expected = DataAccessException.class)
  public void deleteItem_invalidItem_exceptionThrown() throws Exception {
    itemDao.deleteItem(-1);
  }

  @Test
  public void listItem_validItemsListed_returnsItems() throws Exception {
    List<Item> items = itemDao.listItems();
    assertFalse(items.isEmpty());
  }

  @Test
  public void updateItem_validItem_itemUpdated() {
    Item item = itemDao.listItems().get(0);
    item.setName("NewDescription");

    itemDao.updateItem(item);

    Item updatedItem = itemDao.listItems().get(0);
    assertTrue(updatedItem.getName().equals("NewDescription"));
  }
}
