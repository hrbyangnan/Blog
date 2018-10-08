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

public class RegisterUserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Check that servlet doPost is loading");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String realName = request.getParameter("realName");
        String country = request.getParameter("country");
        String birthday = request.getParameter("birthday");
        String information = request.getParameter("information");
        String avatar = request.getParameter("avatar");

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRealName(realName);
        user.setCountry(country);
        user.setBirthday(birthday);
        user.setInfomation(information);
        user.setAvatar(avatar);

try {
    UserDao ud = new UserDaoImp();

    if (ud.register(user)) {
        request.setAttribute("username", name);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    } else {

        response.sendRedirect("regSuccess.jsp");
    }

}catch(SQLException e){e.getMessage();}
    }
}
