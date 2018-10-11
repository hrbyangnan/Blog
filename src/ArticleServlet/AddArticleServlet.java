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
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AddArticleServlet extends HttpServlet {







    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        System.out.println("try to get author attributes from session inside add article");
        User author = (User) userSession.getAttribute("userInfo");
        System.out.println(author.getRealName());
        System.out.println("inside addarticle servlet");
        Article newArticle= new Article();
        newArticle.setArticleName(request.getParameter("articleName"));
        newArticle.setArticleContent(request.getParameter("articleContent"));

        System.out.println(author.getRealName());
        newArticle.setRealName(author.getRealName());
        newArticle.setUserId(author.getId());
//        int loginUserId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        int loginUserId = author.getId();
        //temporary until login is set up
        LocalDateTime pubTime = LocalDateTime.now();
        //
        //Article newArticle = new Article(loginUserId, articleName, articleContent, realName);
        try(ArticleDao dao =new ArticleDao()) {
            dao.addArticle(newArticle);
            List<Article> articles = dao.selectArtByUser(loginUserId);

            request.getSession().setAttribute("userArticles", articles);
            response.sendRedirect("personalpage.jsp");
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("inside articleservlet catch");
        } catch(Exception e)  {
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);


    }

}
