package com.teamlans.lepta.database;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.Connection;

@Component public class DatabaseInitialization {

  @Autowired SessionFactory sessionFactory;

  public void populate(String dataset) throws Exception {
    DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet(dataset));
  }

  private IDataSet getDataSet(String dataset) throws Exception {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(dataset);
    return new FlatXmlDataSetBuilder().build(inputStream);
  }

  private IDatabaseConnection getConnection() throws Exception {
    Connection jdbcConnection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
    IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
    return connection;
  }
}
