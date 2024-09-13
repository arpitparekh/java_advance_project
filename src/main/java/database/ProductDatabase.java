package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDatabase {

  Connection connection;
  Statement statement;
  public static String DATABASE_NAME = "";
  public static String TABLE_NAME = "";

  public ProductDatabase(String databaseName) {

    DATABASE_NAME = databaseName;

    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME, "root", "Walden0042$$");

      statement = connection.createStatement();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void createTable(String tableName) {

    TABLE_NAME = tableName;

    try {
      statement.executeUpdate("create table " + TABLE_NAME
          + "(id int primary key auto_increment, name varchar(255), price float, quantity int)");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

  }

  public void insertData(String name, float price, int quantity) {

    try {
      statement.executeUpdate("insert into " + TABLE_NAME
          + "(name, price, quantity) values ('" + name + "', " + price + ", " + quantity + ")");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

  }

  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }




}
