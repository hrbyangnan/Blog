package CommentServlet;

import dao.CommentDao;
import pojo.Comment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
/*
 * We created this method to edit comments on comments but realised it was not necessary for the brief.
 * Left in incase of future upgrade.
 * */


public class UpdateCommentServlet extends HttpServlet {
    public UpdateCommentServlet() throws SQLException {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(CommentDao dao= new CommentDao()){
            System.out.println("Trying to get info about Comment servlet ");

            int commentId = Integer.parseInt(request.getParameter("id"));
            System.out.println("Trying to use commentId Id " + commentId);
            System.out.println("About to call dao method");
            Comment com = dao.findOneComment(commentId);
            System.out.println("Update comment servlet");
            request.setAttribute("id", com);
            request.setAttribute("commentId", com.getCommentId());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editComment.jsp");
            dispatcher.forward(request,response);}
        catch(SQLException e){e.getMessage();}catch(Exception e){e.getMessage();}

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}
