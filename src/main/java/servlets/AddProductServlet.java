package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add_product")
public class AddProductServlet extends HttpServlet {


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      RequestDispatcher dispatcher = request.getRequestDispatcher("add_product.jsp");
      dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String name = request.getParameter("name");
    String price = request.getParameter("price");
    String quantity = request.getParameter("quantity");

    System.out.println(name + " " + price + " " + quantity);

    request.setAttribute("name", name);
    request.setAttribute("price", price);
    request.setAttribute("quantity", quantity);

    RequestDispatcher dispatcher = request.getRequestDispatcher("product_list.jsp");
    dispatcher.forward(request, response);

    // this is just for redirection
    // response.sendRedirect(request.getContextPath() + "/product_list.jsp");

  }

}
