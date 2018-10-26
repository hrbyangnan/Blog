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
    List<Article> articleList;


    //gets list of article pojos, adds to session, opens JSP page (allArticles.jsp)

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        System.out.println("inside get all articles");

//It's important that the ArticleDao instance is created in try with resources, otherwise there will be an SQLException thrown the second time we run it, though I'm not sure why
            try(
                ArticleDao dao = new ArticleDao()){


         articleList = dao.getAllArticles();
            System.out.println("When the servlet is called the article list size is:" + articleList.size());

            request.setAttribute("AllArticlesPojo", articleList);
        }
        catch (SQLException e){
                System.out.println(e.getMessage());
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allArticles.jsp");
        dispatcher.forward(request,response);

    }

}
