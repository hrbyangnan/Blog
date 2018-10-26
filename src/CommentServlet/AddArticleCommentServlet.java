package CommentServlet;


import dao.ArticleDao;
import dao.CommentDao;
import pojo.Article;
import pojo.Comment;
import pojo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*
* Allowss a user to add a comment to an article. Gets user information  from session and gets comment content and relevant article id from httprequest object. Then calls dao method to add comment to the database, then refreshes the page
*
* */

public class AddArticleCommentServlet extends HttpServlet {

    public AddArticleCommentServlet() throws SQLException {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        System.out.println("try to get author attributes from session inside add comment");
        User author = (User) userSession.getAttribute("userInfo");
       // Article article = (Article) userSession.getAttribute("SingleArticle");

        System.out.println("inside addComments servlet " + author.getRealName());
        Comment newComment = new Comment();

        newComment.setCommentContent(request.getParameter("commentContent"));//1
        newComment.setUserName(author.getName());//2
        newComment.setUserId(author.getId());//3
        int  articleId   =   Integer.parseInt(request.getParameter("artID")) ;
         System.out.println("I am trying to pass " + articleId + " as ID inside comments Servlet");
        newComment.setArticleId(articleId);
        userSession.setAttribute("newComment",newComment);



        try(CommentDao dao =new CommentDao()) {
            dao.addComment(newComment);
            List<Comment> comments = dao.selectComByArt(articleId);
            String path = dao.getPicPath(author.getId());
            System.out.println("This is the string path inside Article comments: "+ path);

            System.out.println("usercomments: " + comments.size());
            request.getSession().setAttribute("userComments", comments);
            request.setAttribute("path", path);

        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("inside addComments catch");
        } catch(Exception e)  {
        }
        try (ArticleDao adao = new ArticleDao()){
            Article art = adao.findOneArticle(articleId);
            System.out.println(art.getArticleName() + "this is in the servlet");
            request.setAttribute("SingleArticle", art);
            //The jsp page we are redirecting to requires param2 and it should not be null; 9999 is a dummy value that is overwritten elsewhere
            request.setAttribute("param2",9999);

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("inside addComments catch");
        } catch(Exception e)  {
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayArticle.jsp");
        dispatcher.forward(request,response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}



