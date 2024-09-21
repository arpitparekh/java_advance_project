package servlets;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import database.ProductDatabase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add_product")
public class AddProductServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddProductServlet.class.getName());

    static {
        try {
            String logFilePath = System.getProperty("user.home") + "/myapp_logs/add_product_servlet.log";
            FileHandler fileHandler = new FileHandler(logFilePath, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
            System.out.println("AddProductServlet log file location: " + logFilePath);
        } catch (IOException e) {
            System.err.println("Failed to create log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add_product.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost method called");
        System.out.println("Log file location: " + System.getProperty("user.home") + "/myapp_logs/add_product_servlet.log");
        request.setCharacterEncoding("UTF-8");
        try {
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");

            if (name == null || name.trim().isEmpty() ||
                priceStr == null || priceStr.trim().isEmpty() ||
                quantityStr == null || quantityStr.trim().isEmpty()) {
                throw new IllegalArgumentException("All fields (name, price, quantity) must be filled.");
            }

            float price;
            int quantity;
            try {
                price = Float.parseFloat(priceStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Price must be a valid number.");
            }
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Quantity must be a valid integer.");
            }

            System.out.println("About to log product addition");
            LOGGER.log(Level.INFO, "Adding product - Name: {0}, Price: {1}, Quantity: {2}", new Object[]{name, price, quantity});
            System.out.println("Logged product addition");

            ProductDatabase productDatabase = new ProductDatabase("yuvraj_batch");
            productDatabase.createTable("product");
            productDatabase.insertData(name, price, quantity);
            productDatabase.closeConnection();

            RequestDispatcher dispatcher = request.getRequestDispatcher("product_list.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException | IllegalArgumentException e) {
            System.out.println("About to log error");
            LOGGER.log(Level.SEVERE, "An error occurred while adding the product.", e);
            System.out.println("Logged error");
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
