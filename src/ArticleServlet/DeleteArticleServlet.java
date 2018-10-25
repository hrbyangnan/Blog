//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/4
//		Time: 16:38
//		To change this template use File | Settings | File Templates.
//
package ArticleServlet;

import dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteArticleServlet extends HttpServlet {





   /* For deleting articles from the database, deletes  from articlePhoto and multimedia table first  to prevent foreign key conflicts then deletes from article table
    }*/

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try(ArticleDao dao = new ArticleDao()){

        int articleId = Integer.parseInt(request.getParameter("articleID"));
    dao.deleteOldPhotos(articleId);
    dao.deleteOldMedia(articleId);
        dao.deleteArticle(articleId);

    }
        catch(SQLException e) {
        e.printStackTrace();
    }catch (Exception e){e.getMessage();}

        response.sendRedirect("personalpage.jsp");}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
