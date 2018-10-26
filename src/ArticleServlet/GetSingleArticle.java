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
    Article art;


    ArticleDao dao;



    //gets list of article pojos, adds to session, opens JSP page (allArticles.jsp)

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//It's important that the ArticleDao instance is created in try with resources, otherwise there will be an SQLException thrown the second time we run it, though I'm not sure why

        try(ArticleDao dao = new ArticleDao()){


        System.out.println("inside get single article");
//Get param1 from href to determine what id to pass into SQL query
        int id = Integer.parseInt(request.getParameter("param1"));
        System.out.println("I am trying to pass " + id + " as ID");

        art = dao.findOneArticle(id);
        System.out.println(art.getArticleName() + "this is in the servlet - James");
        request.setAttribute("SingleArticle", art);

        System.out.println("after article method... ");
//This line is necessary for comments but delete if we find a better way
           // request.setAttribute("param2",9999);
    }catch (SQLException e){
            e.getMessage();}
            catch (Exception f){
                System.out.println(f.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayArticle.jsp");
        dispatcher.forward(request,response);


    }

}

