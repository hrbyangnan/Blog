package CommentServlet;

import dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
 * Administrator can hide comments . Gets comment id from request and passes it into dao method.
 * */

public class HideCommentServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            try(CommentDao dao = new CommentDao()){

                int CommentId = Integer.parseInt(request.getParameter("commentId"));

                dao.HideComment(CommentId);

            }
            catch(SQLException e) {
                e.printStackTrace();
            }catch (Exception e){e.getMessage();}

            response.sendRedirect("adminPage.jsp");}
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            this.doGet(request, response);


        }

    }


