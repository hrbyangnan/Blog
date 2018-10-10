//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/4
//		Time: 16:38
//		To change this template use File | Settings | File Templates.
//
package ArticleServlet;

import dao.ArticleDao;
import pojo.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeleteArticleServlet extends HttpServlet {
    ArticleDao dao;

    {
        try {
            dao = new ArticleDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DeleteArticleServlet() throws SQLException {
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int articleId = Integer.parseInt(request.getParameter("articleId"));
        dao.deleteArticle(articleId);
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        List<Article> articles = dao.selectArtByUser(userId);
        request.getSession().setAttribute("userArticles", articles);
        response.sendRedirect("article/articleList.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        int userId = Integer.parseInt(id);


    }

}
