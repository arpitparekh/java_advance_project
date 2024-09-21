package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product_list")
public class ProductListServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String name = (String) req.getParameter("name");
    String price = (String) req.getParameter("price");
    String quantity = (String) req.getParameter("quantity");

    req.setAttribute("name", name);
    req.setAttribute("price", price);
    req.setAttribute("quantity", quantity);

    RequestDispatcher dispatcher = req.getRequestDispatcher("product_list.jsp");
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }

}
