package com.algalopez.reackus.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
@Slf4j
public class DbUtils {

  private Connection connection;

  public Connection connection() {
    return connection;
  }

  @SneakyThrows
  public void executeUpdate(String sql) {
    connection.prepareStatement(sql).executeUpdate();
  }

  @SneakyThrows
  public <T> T executeQuery(String sql, String field, Class<T> type) {
    ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
    resultSet.next();
    T dbId = resultSet.getObject(field, type);
    resultSet.close();
    return dbId;
  }


  @PostConstruct
  public void onStart() {
    log.debug("The database is starting...");

    try {
      Class.forName("org.mariadb.jdbc.Driver");
      connection =
          DriverManager.getConnection("jdbc:mariadb://localhost:13306/reackus", "user", "pass");
    } catch (SQLException | ClassNotFoundException e) {
      log.error("Error creating test connection for database", e);
      connection = null;
      throw new IllegalStateException(e);
    }
  }

  @PreDestroy
  public void onStop() {
    log.debug("The database is stopping...");

    try {
      connection.close();
    } catch (SQLException e) {
      log.error("Error stopping test connection for database", e);
      throw new IllegalStateException(e);
    } finally {
      connection = null;
    }
  }
}
