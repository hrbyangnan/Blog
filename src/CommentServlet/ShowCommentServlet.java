package CommentServlet;

import dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowCommentServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try(CommentDao dao = new CommentDao()){

            int CommentId = Integer.parseInt(request.getParameter("commentId"));

            dao.ShowComment(CommentId);

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


