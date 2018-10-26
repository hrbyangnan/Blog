package Administrative;

import dao.UserDao;
import dao.UserDaoImp;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
/*
* For resetting the password. Get username and doublechecks the new password is correct.
* */

public class ResetPasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String newPassword = request.getParameter("password");
        String newPassword2 = request.getParameter("password2");
        Map<String, String> errors = new HashMap<String, String>();
        if (newPassword == null || "".equals(newPassword)) {
            errors.put("password", "new passwold can not be blank");
        }

        if (newPassword2 == null || "".equals(newPassword2)) {
            errors.put("password2", "new password can not be blank");
        }

        if (!newPassword.equals(newPassword2)) {
            errors.put("passwordError", "two passwords are different");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/resetPasswordUI?userName=" + userName).forward(request, response);
            return;
        }

        UserDaoImp userDao = null;
        try {
            userDao = new UserDaoImp();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User user = null;
        try {
            user = userDao.findUserByName(userName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setPassword(newPassword);

        UserDao ud = null;
        try {
            ud = new UserDaoImp();


            if (ud.changePassword(user)) {
                request.setAttribute("username", userName);
                request.getRequestDispatcher("resetPasswordSuccess.html").forward(request, response);
            } else {

                response.sendRedirect("resetpass.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
