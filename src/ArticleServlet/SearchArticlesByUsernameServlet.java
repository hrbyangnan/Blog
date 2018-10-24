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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchArticlesByUsernameServlet extends HttpServlet {
    ArticleDao dao;
    List<Article> articleList;


    //gets list of article pojos, adds to session, opens JSP page (allArticles.jsp)

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

            try(
                ArticleDao dao = new ArticleDao()){
                String info=null;
                info=request.getParameter("info");


            articleList = dao.SearchArticle(info);
                Collections.sort(articleList, new Comparator<Article>() {
                    @Override
                    public int compare(Article o1, Article o2) {
                        return o1.getRealName().compareTo(o2.getRealName());

                    }
                });

            request.setAttribute("AllArticlesPojo", articleList);
        }catch (SQLException e){e.getMessage();}catch (Exception e){

        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/searchResult.jsp");
        dispatcher.forward(request,response);

    }

}
