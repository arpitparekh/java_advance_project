package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JobsDatabaseConnect {

  Connection connection;
  Statement statement;

  public JobsDatabaseConnect(String databaseName) {

    try {
      connection = DriverManager.getConnection(

          "jdbc:mysql://localhost:3306/" + databaseName,
          "root",
          "Walden0042$$");

      statement = connection.createStatement();

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
  }

  public void createTable(String table) {

    try {
      statement.executeUpdate(
          "create table if not exists " + table + "(id int primary key, name varchar(255),price float,quantity int);");

      System.out.println("Table created successfully");

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void insertData(String table, String name, float price, int quantity) {

    try {
      statement.executeUpdate("insert into " + table + " values(1,'" + name + "'," + price + "," + quantity + ");");

      System.out.println("Data successfully");

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

}
