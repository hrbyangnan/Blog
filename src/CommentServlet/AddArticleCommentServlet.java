package CommentServlet;


import dao.ArticleDao;
import dao.CommentDao;
import dao.ProfilePhotoDao;
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

public class AddArticleCommentServlet extends HttpServlet {
    ArticleDao adao = new ArticleDao();
    CommentDao cdao = new CommentDao();
    ProfilePhotoDao pdao = new ProfilePhotoDao();
Comment comment;
    public AddArticleCommentServlet() throws SQLException {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        System.out.println("try to get author attributes from session inside add comment");
        User author = (User) userSession.getAttribute("userInfo");
        Article article = (Article) userSession.getAttribute("SingleArticle");

        System.out.println("inside addComments servlet " + author.getRealName());
        Comment newComment = new Comment();

        newComment.setCommentContent(request.getParameter("commentContent"));//1
        newComment.setUserName(author.getName());//2
        newComment.setUserId(author.getId());//3
        int  articleId   =   Integer.parseInt(request.getParameter("artID")) ;
        System.out.println("article id we are using in add comment to article servlet is : "+ articleId);
        System.out.println("I am trying to pass " + articleId + " as ID inside comments Servlet");
 //        int articleId = 67;//4
        newComment.setArticleId(articleId);
//        int commentID = request.getParameter();
        userSession.setAttribute("newComment",newComment);
//        comment = cdao.findOneComment(articleId);
//        System.out.println(comment.getCommentId() + "this is in the servlet - James");
//        request.setAttribute("SingleComment", comment);



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
            System.out.println(art.getArticleName() + "this is in the servlet - James");
            request.setAttribute("SingleArticle", art);
            request.setAttribute("param2",9999);

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("inside addComments catch");
        } catch(Exception e)  {
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayArticle.jsp");
        dispatcher.forward(request,response);
    }
//    CommentContent, commentID, articleId, userId, userName

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


}



//    int articleId = Integer.parseInt(request.getParameter("articleId"));
//    int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
//        request.getSession().setAttribute("articleId", articleId);
//
//                User u = (User) request.getSession().getAttribute("user");
//                String userName = u.getName();
//                String CommentContent = request.getParameter("message");
//                int commentID = Integer.parseInt(request.getParameter("commentID"));
//                cdao.addComment(new Comment( CommentContent, commentID, articleId, userId, userName));
//                request.setAttribute("cdao", cdao);
//                System.out.println("before display articles inside AddArticleCommentServlet");
//                response.sendRedirect("displayArticle.jsp");