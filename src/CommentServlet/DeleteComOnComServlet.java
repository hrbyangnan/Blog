package CommentServlet;

import dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteComOnComServlet extends HttpServlet {

    public DeleteComOnComServlet() throws SQLException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (CommentDao dao = new CommentDao()) {
            int commentId = Integer.parseInt(request.getParameter("ComOnComID"));
            dao.deleteComment(commentId);

        } catch (SQLException e) {
            System.out.println("delete in DeleteComOnComServlet " + e);
        } catch (Exception e) {
            System.out.println("delete in DeleteComOnComServlet  " + e);
        }
        response.sendRedirect("/displayArticle.jsp");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
