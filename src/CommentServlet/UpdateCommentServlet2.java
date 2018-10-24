package CommentServlet;


import dao.CommentDao;
import pojo.Comment;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateCommentServlet2 extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

         String newContent = request.getParameter("oldContent");
         int id = Integer.parseInt(request.getParameter("oldCommentID"));


        try (CommentDao dao = new CommentDao()) {
            Comment placeholderComment = dao.findOneComment(id);
            System.out.println("Trying to update comment #" + placeholderComment.getCommentId());
            placeholderComment.setCommentContent(newContent);
            System.out.println("Going to try to update.");
            dao.updateComment(placeholderComment);
            System.out.println("Comment should be updated");
            response.sendRedirect("displayArticle.jsp");
        } catch (SQLException e) {
            System.out.println("Threw an SQL exception");
            e.getMessage();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}

