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
import java.util.List;

public class GetAllArticlesServlet extends HttpServlet {
    ArticleDao dao;

    {
        try {
            dao = new ArticleDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //gets list of article pojos, adds to session, opens JSP page (allArticles.jsp)

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        System.out.println("inside get all articles");

        List<Article> articleList = dao.getAllArticles();

        System.out.println("after article list method... " + articleList.size());

        request.getSession().setAttribute("AllArticlesPojo",articleList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/indextesting.jsp");
        dispatcher.forward(request,response);

    }

}
