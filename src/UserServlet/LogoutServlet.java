package UserServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
* Gets the session and sets User information session attribute  to null, redirect user to index page
* */
public class LogoutServlet extends HttpServlet {

    public LogoutServlet() {
        super();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = null;
        request.getSession().setAttribute("userInfo", null);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}
