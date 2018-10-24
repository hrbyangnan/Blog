package Multimedia;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class Gallery extends HttpServlet {

     public void doPost(HttpServletRequest request, HttpServletResponse response){
            try{

                System.out.println("Gallery servlet");
                File folder = new File(getServletContext().getRealPath("/Thumbnails"));
                File mediaFolder = new File (getServletContext().getRealPath("/Uploaded_Multimedia"));
                String[] thumbNames = folder.list();
                String[] mediaNames = mediaFolder.list();
                System.out.println(thumbNames.length+" is the size of the names list");
                request.setAttribute("thumbNames", thumbNames);
                request.setAttribute("mediaNames", mediaNames);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gallery.jsp");
                dispatcher.forward(request, response);}catch(ServletException e){e.getMessage();}catch(IOException e){e.getMessage();}



        }
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request,response);
    }
}
