package ArticleServlet;


import dao.ArticleDao;
import dao.CommentDao;
import pojo.Comment;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddArticleComment extends HttpServlet {
    ArticleDao adao = new ArticleDao();
    CommentDao cdao = new CommentDao();

    public AddArticleComment() throws SQLException {
        super();
    }


    public void destroy() {
        super.destroy();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        request.getSession().setAttribute("articleId", articleId);

        User u = (User) request.getSession().getAttribute("user");
        String userName = u.getName();
        String CommentContent = request.getParameter("message");
        int commentID = Integer.parseInt(request.getParameter("commentID"));
        cdao.addComment(new Comment(CommentContent, commentID, articleId, userId, userName));

        //
        response.sendRedirect("/BokeProject/ShowFriendArticle.do");
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}
