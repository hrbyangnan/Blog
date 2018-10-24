package Multimedia;

import dao.ArticleDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class GalleryForOneUser extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            int userID = Integer.parseInt(request.getParameter("paramUserID"));
            System.out.println("Gallery for single User servlet");
                /*File folder = new File(getServletContext().getRealPath("/Thumbnails"));
                File mediaFolder = new File (getServletContext().getRealPath("/Uploaded_Multimedia"));*/
            try(ArticleDao aDao = new ArticleDao();) {
                Set<String> thumbNames = aDao.getPicPathsByUser(userID);
                Set<String> mediaNames = aDao.getMediaPathsByUser(userID);
                System.out.println(thumbNames.size() + " is the size of the names list");
                request.setAttribute("thumbNames", thumbNames);
                request.setAttribute("mediaNames", mediaNames);
            }catch (SQLException e){e.getMessage();}
            catch(Exception e){e.getMessage();}

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/galleryForOne.jsp");
            dispatcher.forward(request, response);}catch(ServletException e){e.getMessage();}catch(IOException e){e.getMessage();}



    }
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}

