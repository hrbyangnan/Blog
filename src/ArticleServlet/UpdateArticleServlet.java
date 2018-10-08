package ArticleServlet;


import dao.ArticleDao;
import pojo.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateArticleServlet extends HttpServlet {


    ArticleDao dao = new ArticleDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int articleId = Integer.parseInt(request.getParameter("articleId"));
        Article article = dao.findOneArticle(articleId);
        request.getSession().setAttribute("article", article);
        request.getSession().setAttribute("articleId", article.getArticleId());
        response.sendRedirect("article/aditArticle.jsp");
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}
