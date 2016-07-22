package com.teamlans.lepta.database.daos;

import com.teamlans.lepta.database.entities.Bill;
import com.teamlans.lepta.database.exceptions.LeptaDatabaseException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public class BillDaoImpl extends HibernateDaoSupport implements BillDao {

  @Autowired public BillDaoImpl(SessionFactory sessionFactory) {
    setSessionFactory(sessionFactory);
  }

  public Bill getBillBy(int nr) {
    return getHibernateTemplate().get(Bill.class, nr);
  }

  public Integer addBill(Bill newBill) throws LeptaDatabaseException {
    try {
      return (Integer) getHibernateTemplate().save(newBill);
    } catch (DataIntegrityViolationException e) {
      throw new LeptaDatabaseException("Could not save bill " + newBill.toString());
    }
  }

  public void deleteBill(Integer nr) throws LeptaDatabaseException {
    Bill billToDelete = getHibernateTemplate().get(Bill.class, nr);
    if (billToDelete == null) {
      throw new LeptaDatabaseException("Could not delete. Bill with id " + nr + " does not exist.");
    }
    getHibernateTemplate().delete(billToDelete);
  }

  public void updateBill(Bill newBill) throws LeptaDatabaseException {
    getHibernateTemplate().update(newBill);
  }

  @Override @SuppressWarnings("unchecked") public List<Bill> listBills()
      throws LeptaDatabaseException {
    return (List<Bill>) getHibernateTemplate()
        .findByCriteria(DetachedCriteria.forClass(Bill.class));
  }

}
