package CommentServlet;

import dao.ArticleDao;
import dao.ComOnComDao;
import pojo.Article;
import pojo.CommentOnComment;
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

public class AddCommentCommentServlet extends HttpServlet {
     public AddCommentCommentServlet() throws SQLException {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        System.out.println("try to get author attributes from session inside add comment on comment");
        User author = (User) userSession.getAttribute("userInfo");
//      Article article = (Article) userSession.getAttribute("SingleArticle");
        int articleId = Integer.parseInt(request.getParameter("superID"));
        int fatherCommentId  = Integer.parseInt(request.getParameter("cID"));


        System.out.println("inside addCommentsOnComments servlet " + author.getRealName());
        CommentOnComment newCommentOnComment = new CommentOnComment();

        System.out.println("I am trying to pass  comment Id as " + fatherCommentId + " as ID inside comments on comment Servlet");
        newCommentOnComment.setFatherCommentId(fatherCommentId);//1
        newCommentOnComment.setCommentContent(request.getParameter("ComOnCom"));//1

        newCommentOnComment.setUserId(author.getId());//3
        newCommentOnComment.setUserName(author.getName());//2

        try(ComOnComDao dao = new ComOnComDao()) {
            dao.addCommentOnComment(newCommentOnComment);
            List<CommentOnComment>CommentOnComment = dao.selectComByCom(fatherCommentId);
            String path1 = dao.getPicPath(author.getId());
            System.out.println("This is the string path Comments on comments: "+ path1);

            System.out.println("user comments this comments on comment: "+ CommentOnComment.size());
            request.getSession().setAttribute("userCommentsOnComments", CommentOnComment);
            request.setAttribute("path1", path1);

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("inside addCommentComment catch");
        } catch(Exception e)  {
        }
        try (ArticleDao adao = new ArticleDao()){
            Article art = adao.findOneArticle(articleId);
            System.out.println(art.getArticleName() + "this is in the servlet - James");
            request.setAttribute("SingleArticle", art);


    }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("inside addCommentComment catch");
        } catch(Exception e)  {
        }

        //This line seems necessary at moment but not 100% sure why
        //request.setAttribute("param2",40);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayArticle.jsp");
        dispatcher.forward(request,response);}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
