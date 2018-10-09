//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/5
//		Time: 13:14
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
import java.time.LocalDateTime;
import java.util.List;

public class AddArticleServlet extends HttpServlet {
    ArticleDao dao;

    {
        try {
            dao = new ArticleDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("inside addarticle servlet");

        String articleName = request.getParameter("articleName");
        String articleContent = request.getParameter("articleContent");
//        int loginUserId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        int loginUserId = 123;
        //temporary until login is set up
        LocalDateTime pubTime = LocalDateTime.now();
        Article newArticle = new Article(articleName, loginUserId, articleContent, pubTime);
        dao.addArticle(newArticle);
        List<Article> articles = dao.selectArtByUser(loginUserId);
        request.getSession().setAttribute("userArticles", articles);
        response.sendRedirect("article/articleList.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);


    }

}
