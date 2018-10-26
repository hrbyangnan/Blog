package ArticleServlet;

import dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
* Used by admin to hide articles
* */

public class HideArticleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (ArticleDao dao = new ArticleDao()) {
            String id = request.getParameter("articleId");

            int articleId = Integer.parseInt(id);

            dao.HideArticle(articleId);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
        }

        response.sendRedirect("adminPage.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);

    }

}


