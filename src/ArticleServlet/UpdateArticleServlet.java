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
    System.out.println("Trying to get info about article ID - Inside update article servlet - James");
    int articleId = Integer.parseInt(request.getParameter("scooby"));
    System.out.println("I'm trying to use article Id" +articleId);
    System.out.println("Trying to get this as srticle ID");
    System.out.println("About to call dao method");
    Article article = dao.findOneArticle(articleId);
    System.out.println("Update Article servlet");
    System.out.println(article.getArticleName());
        request.setAttribute("scooby", article);
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
