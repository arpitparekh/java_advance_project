package servlets;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/intro")
public class Introduction extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet Initialization");
        super.init();
    }

    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

        String message = "Hello from Yuvraj!";
        request.setAttribute("message", message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

        System.out.println("Servlet Invocation");

        // response.setContentType("text/html");

        // PrintWriter out=response.getWriter();
        // out.print("<html><body>");
        // out.print("<b>hello simple servlet</b>");
        // out.print("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Servlet Destroy");
        super.destroy();
    }

}
