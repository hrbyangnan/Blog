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

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("inside login servlet");
        String name = request.getParameter("login");
        String password = request.getParameter("password");

        HttpSession userSession = request.getSession(true);
//        userSession.setAttribute("login", false);


        try (UserDaoImp ud = new UserDaoImp()){
            assert ud != null;
            if (ud.login(name).equals(password)) {
                //check this syntax does set login to true
                response.getWriter().write("success");
                userSession.setAttribute("login", true);
                System.out.println("login set to true");

                User current = ud.getUserInfo(name);

                userSession.setAttribute("userInfo", current);
                System.out.println("before page");
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Login success!");

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