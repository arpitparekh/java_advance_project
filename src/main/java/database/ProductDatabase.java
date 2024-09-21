package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ProductDatabase {
    private Connection connection;
    private Statement statement;
    private String databaseName;
    private String tableName;
    private static final Logger LOGGER = Logger.getLogger(ProductDatabase.class.getName());

    static {
        try {
            String logFilePath = System.getProperty("user.home") + "/myapp_logs/product_database.log";
            FileHandler fileHandler = new FileHandler(logFilePath, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
            System.out.println("ProductDatabase log file location: " + logFilePath);
        } catch (IOException e) {
            System.err.println("Failed to create log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ProductDatabase(String databaseName) {
        System.out.println("ProductDatabase constructor called");
        this.databaseName = databaseName;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.databaseName, "root", "Walden0042$$");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            System.out.println("About to log database connection success");
            LOGGER.info("Database connected successfully.");
            System.out.println("Logged database connection success");
        } catch (SQLException e) {
            System.out.println("About to log database connection error");
            LOGGER.log(Level.SEVERE, "Database Connection Error: " + e.getMessage(), e);
            System.out.println("Logged database connection error");
        }
    }

    public void createTable(String tableName) {
        this.tableName = tableName;
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + this.tableName + " "
                + "(id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(255), "
                + "price FLOAT, "
                + "quantity INT)";
            statement.executeUpdate(createTableSQL);
            LOGGER.log(Level.INFO, "Table ''{0}'' ensured to exist.", this.tableName);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Table Creation Error: " + e.getMessage(), e);
        }
    }

    public void insertData(String name, float price, int quantity) {
        try {
            String sql = "INSERT INTO " + this.tableName + " (name, price, quantity) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, price);
            preparedStatement.setInt(3, quantity);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Product inserted successfully.");
            } else {
                LOGGER.warning("Failed to insert product.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Data Insertion Error: " + e.getMessage(), e);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Database connection closed.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection Close Error: " + e.getMessage(), e);
        }
    }
}
