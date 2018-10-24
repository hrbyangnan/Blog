package CommentServlet;

import dao.ComOnComDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowComInComServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            try(ComOnComDao dao = new ComOnComDao()){
                String id=request.getParameter("comOnComId");

                int ComOnComId = Integer.parseInt(id);

                dao.ShowComOnCom(ComOnComId);

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


