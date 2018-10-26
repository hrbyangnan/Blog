package UserServlet;

import dao.UserDaoImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
* User can delete their own information by calling this servlet. The ID of the user is used to delete user information from database, then user is forwarded to index page
* */

public class DeleteUserInformation extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try (UserDaoImp dao = new UserDaoImp()) {
            int userId = Integer.parseInt(request.getParameter("userId"));

            System.out.println("DeleteUserInformation1: "+ userId);
            dao.deleteUser(userId);

        } catch (SQLException e) {
             System.out.println("SQLException " + e.getMessage());
        } catch (Exception e) {
             System.out.println("Exception " + e.getMessage());
        }

        request.getSession().setAttribute("userInfo", null);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
     }
        public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            this.doGet(request, response);
    }

}