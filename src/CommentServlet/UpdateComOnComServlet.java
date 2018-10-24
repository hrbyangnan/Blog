package CommentServlet;

import dao.ComOnComDao;
import pojo.CommentOnComment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateComOnComServlet extends HttpServlet {
    public UpdateComOnComServlet() throws SQLException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (ComOnComDao dao = new ComOnComDao()) {
            System.out.println("Trying to get info about CommentOnComment servlet ");

            int commentId = Integer.parseInt(request.getParameter("ComOnComID"));
            System.out.println("I'm trying to use ComOnComID " + commentId);
            System.out.println("About to call dao method");
            CommentOnComment com = dao.findOneComment(commentId);
            System.out.println("Update comment servlet");
            request.setAttribute("ComOnComID", com);
            request.setAttribute("commentId", com.getCommentId());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editComOnCom.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.getMessage();
        } catch (Exception e) {
            e.getMessage();
        }

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }
}