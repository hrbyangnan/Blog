package UserServlet;

import dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
/*
* Admin can delete user, using dao method with userID.
* */
public class RemoveUserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try(UserDaoImp dao = new UserDaoImp()){

            int UserId = Integer.parseInt(request.getParameter("UserId"));

            dao.delete(UserId);

        }
        catch(SQLException e) {
            e.printStackTrace();
        }catch (Exception e){e.getMessage();}

        response.sendRedirect("adminPage.jsp");}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);


    }
}
