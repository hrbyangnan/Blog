package ArticleServlet;


import dao.ArticleDao;
import pojo.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateArticleServlet extends HttpServlet {

    public UpdateArticleServlet() throws SQLException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(ArticleDao dao= new ArticleDao()){

    int articleId = Integer.parseInt(request.getParameter("editArticleID"));

    Article article = dao.findOneArticle(articleId);
    System.out.println("Update Article servlet");
    System.out.println(article.getArticleName());
        request.setAttribute("editArticle", article);
        request.setAttribute("articleId", article.getArticleId());
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editArticle.jsp");
    dispatcher.forward(request,response);}
        catch(SQLException e){e.getMessage();}catch(Exception e){e.getMessage();}

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}
