//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/4
//		Time: 16:38
//		To change this template use File | Settings | File Templates.
//
package UserServlet;


import dao.UserDaoImp;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;


/*
* Check if password is correct, if so add user info to the session and forward to personal page.
* */
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
         String name = request.getParameter("login");
        String password = request.getParameter("password");

        HttpSession userSession = request.getSession(true);


        try (UserDaoImp ud = new UserDaoImp()){
            assert ud != null;
            if (ud.login(name).equals(password)) {
                //check this syntax does set login to true
                response.getWriter().write("success");
                userSession.setAttribute("login", true);

                User current = ud.getUserInfo(name);

                userSession.setAttribute("userInfo", current);

                request.getRequestDispatcher("personalpage.jsp").forward(request, response);
            } else  {
                JOptionPane.showMessageDialog(null, "Incorrect username or password!");
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (IllegalAccessException ce) {
                e.printStackTrace();
            } catch (InstantiationException ce) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException ce) {
                e.printStackTrace();
            } catch (ClassNotFoundException ce) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Incorrect username or password!");
            response.sendRedirect("index.jsp");
        }catch(Exception e){e.getMessage(); e.getStackTrace();}
    }
}