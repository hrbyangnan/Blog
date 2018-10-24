package CommentServlet;

import dao.ComOnComDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HideComInComServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            try(ComOnComDao dao = new ComOnComDao()){

                int ComOnComId = Integer.parseInt(request.getParameter("comOnComId"));

                dao.HideComOnCom(ComOnComId);

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


