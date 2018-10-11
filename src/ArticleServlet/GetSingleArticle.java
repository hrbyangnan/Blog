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

public class GetSingleArticle extends HttpServlet {



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
            throws ServletException, IOException {

        System.out.println("inside get single article");

        int id = 11;
        Article art = dao.findOneArticle(id);
        System.out.println(art.getArticleName()+"this is in the servlet - James");
        request.getSession().setAttribute("SingleArticle",art);

        System.out.println("after article method... " );



        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayArticle.jsp");
        dispatcher.forward(request,response);




    }

}

