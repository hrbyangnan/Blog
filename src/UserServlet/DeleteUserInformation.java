package UserServlet;

import dao.UserDaoImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserInformation extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("inside DeleteUserInformation chckk");
        try (UserDaoImp dao = new UserDaoImp()) {
            int userId = Integer.parseInt(request.getParameter("userId"));

            System.out.println("DeleteUserInformation1: "+ userId);
            dao.deleteUser(userId);

        } catch (SQLException e) {
            System.out.println("inside DeleteUserInformation servlet catch1");
        } catch (Exception e) {
            System.out.println(e+" inside DeleteUserInformation servlet catch2");
        }
        System.out.println("DeleteUserInformation2");

        request.getSession().setAttribute("userInfo", null);
        System.out.println("DeleteUserInformation3");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
     }
        public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            this.doGet(request, response);
    }

}