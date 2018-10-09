//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/4
//		Time: 16:38
//		To change this template use File | Settings | File Templates.
package UserServlet;


import dao.UserDao;
import dao.UserDaoImp;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterUserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Check that servlet doPost is loading");

        String userName = request.getParameter("username");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        String realName = firstName + " " + lastName;
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        String b = request.getParameter("birthday");

       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       Date bb=null;
        try {
            bb=sdf.parse(b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date dayDateSql = new java.sql.Date(bb);

        String information = request.getParameter("publicinfo");

        User user = new User();
        user.setName(userName);
        user.setRealName(realName);
        user.setPassword(password);
        user.setCountry(country);
        user.setBirthday(birthday);
        user.setInfomation(information);

        try {
            UserDao ud = new UserDaoImp();

            if (ud.register(user)) {
                request.setAttribute("username", realName);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {

                response.sendRedirect("regSuccess.jsp");
            }

        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
