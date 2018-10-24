package CommentServlet;


import dao.ArticleDao;
import dao.CommentDao;
import pojo.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCommentServlet extends HttpServlet {


    ArticleDao dao = new ArticleDao();
    CommentDao cDao = new CommentDao();

    public DeleteCommentServlet() throws SQLException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int articleId = Integer.parseInt(request.getParameter("superID"));
        try (CommentDao dao = new CommentDao()) {

            int commentId = Integer.parseInt(request.getParameter("cID"));

            dao.deleteComment(commentId);

        } catch (SQLException e) {
            System.out.println("delete in "+e);
        } catch (Exception e) {
            System.out.println("delete in "+e);
        }
        try (ArticleDao adao = new ArticleDao()){
            Article art = adao.findOneArticle(articleId);
            System.out.println(art.getArticleName() + "this is in the delete comment servlet - ");
            request.setAttribute("SingleArticle", art);


        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("inside delete catch");
        } catch(Exception e)  {
        }

        //This line seems necessary at moment but not 100% sure why
        //request.setAttribute("param2",40);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayArticle.jsp");
        dispatcher.forward(request,response);}



        public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

     }

}

//        int commentId = (int) request.getAttribute("cID");
//        int articleId = (int) request.getAttribute("superID");
//
//        List<Comment> comments = cDao.selectComByArt(articleId);
//        cDao.deleteComment(commentId);
//
//        System.out.println(" you are in delete comments");
//        request.setAttribute("comments", comments);
//        System.out.println(" you are in no attri comments");
//
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/displayArticle.jsp");
//        dispatcher.forward(request,response);

