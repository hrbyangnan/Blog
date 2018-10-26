package CommentServlet;

import dao.ComOnComDao;
import pojo.CommentOnComment;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
/*
 * We created this method to edit comments on comments but realised it was not necessary for the brief.
 * Left in incase of future upgrade.
 * */
public class UpdateComOnComServlet2 extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String newContent = request.getParameter("oldComOnCom");
        int id = Integer.parseInt(request.getParameter("oldComOnComID"));


        try (ComOnComDao dao = new ComOnComDao()) {
            CommentOnComment placeholderComment = dao.findOneComment(id);
            System.out.println("Trying to update commentId #" + placeholderComment.getCommentId());
            placeholderComment.setCommentContent(newContent);
            System.out.println("Going to try to update");
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
