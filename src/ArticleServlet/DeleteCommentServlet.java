package ArticleServlet;


import dao.ArticleDao;
import dao.CommentDao;
import pojo.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeleteCommentServlet extends HttpServlet {


    ArticleDao dao = new ArticleDao();
    CommentDao cDao = new CommentDao();

    public DeleteCommentServlet() throws SQLException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int commentId = Integer.parseInt(request.getParameter("commentId"));
        int articleId = Integer.parseInt(request.getSession()
                .getAttribute("articleId").toString());
        cDao.deleteComment(commentId);

        List<Comment> comments = cDao.selectComByArt(articleId);
        request.getSession().setAttribute("comments", comments);

        response.sendRedirect("article/articleDemo.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}
