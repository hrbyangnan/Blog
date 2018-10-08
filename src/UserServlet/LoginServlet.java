//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/4
//		Time: 16:38
//		To change this template use File | Settings | File Templates.
//
package UserServlet;


import dao.UserDao;
import dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        String plaintext=null;

        UserDao ud = null;
        try {
            ud = new UserDaoImp();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(ud.login(name)==plaintext){
            request.setAttribute("mes", "weilcome"+name);
            request.getRequestDispatcher("/success.jsp").forward(request, response);}
            else{
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}