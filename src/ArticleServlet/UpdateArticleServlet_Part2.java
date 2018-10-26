

package ArticleServlet;

import Multimedia.ThumbnailGenerator;
import dao.ArticleDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/*
* Takes data from edit.jsp page puts into article POJO and replaces old article in the database. Similar to add new article
* */

public class UpdateArticleServlet_Part2 extends HttpServlet {

    private File uploadsFolder;
    private File tempFolder;
    private File thumbnailsFolder;
    private File mediaFolder;

    private ArrayList<String> pathnames;
    private ArrayList<String> mediaPaths;

    String articleName;
    String articleContent;
    String pubTime;
    String fileName;
    int originalID;
    java.util.Date d=null;

    @Override
    public void init() throws ServletException {
        super.init();
        initUploadFolder();
    }

    public void initUploadFolder(){
        this.uploadsFolder = new File(getServletContext().getRealPath("/Uploaded_Photos"));
        if (!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }

        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        this.thumbnailsFolder = new File(getServletContext().getRealPath("/Thumbnails"));
        if (!thumbnailsFolder.exists()) {
            thumbnailsFolder.mkdirs();
        }
        this.mediaFolder = new File(getServletContext().getRealPath("/Uploaded_Multimedia"));
        if (!mediaFolder.exists()) {
            mediaFolder.mkdirs();
        }
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response)  {

        //create file objects to handle enctype multipart
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4*1024);
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);


        //set random number so files less likely to overwrite each other
        String random = String.valueOf((int)(Math.random()*(9999-1000+1))+1000);
        pathnames = new ArrayList<>();
        mediaPaths = new ArrayList<>();

        try {
            List<FileItem> fileItems = upload.parseRequest(request);

            for(FileItem fi: fileItems){
                if(!fi.isFormField()){
                    if(fi.getSize()>1){
                        fileName = fi.getName();

                        if(fi.getContentType().contains("image")){
                            File fullsizeImagefile = new File(uploadsFolder, random+"_"+fileName);
                            fi.write(fullsizeImagefile);
                            pathnames.add(random+"_"+fileName);
                            ThumbnailGenerator.generateThumb(fullsizeImagefile, random+"_"+fileName, thumbnailsFolder);
                        }
                        else if(fi.getContentType().contains("audio")||fi.getContentType().contains("video")){
                            File fullsizeMediaFile = new File (mediaFolder, random+"_"+fileName);
                            fi.write(fullsizeMediaFile);
                            mediaPaths.add(random+"_"+fileName);
                        }
                    }
                }else if(fi.getFieldName() !=null){
                    if(fi.getFieldName().equals("articleName")){
                        articleName = fi.getString();
                    }
                    if(fi.getFieldName().equals("articleContent")){
                        articleContent = fi.getString();
                    }
                    if (fi.getFieldName().equals("pubTime")){
                        pubTime = fi.getString();
                    }
                    if (fi.getFieldName().equals("oldArticleID")){
                        originalID=Integer.parseInt(fi.getString());
                    }
                }
            }

        }catch (FileUploadException e){e.getStackTrace();}catch(Exception e){e.getStackTrace();}

//Put the date into an SQL compatible format
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");

        try{  d = format.parse(pubTime);}catch(ParseException e){e.getStackTrace();}
        java.sql.Time date = new java.sql.Time(d.getTime());

        //Get the original Article then change stuff
        try(ArticleDao dao = new ArticleDao()){
            Article original = dao.findOneArticle(originalID);
            original.setArticleName(articleName);
            original.setArticleContent(articleContent);
            //original.setPubTime(date);
            original.setAllPicPaths(pathnames);
            original.setMedia(mediaPaths);
            original.setVisible(1);
            dao.updateArticle(original);
            dao.deleteOldPhotos(originalID);
            dao.deleteOldMedia(originalID);
            for(String path: pathnames){
                dao.insertPhotoinf(originalID,"/Uploaded_Photos/"+path);
            }
            for(String path: mediaPaths){
                dao.insertMediaInf(originalID,"/Uploaded_Multimedia/"+path);
            }



            response.sendRedirect("personalpage.jsp");


        }catch (SQLException e){e.getStackTrace();}catch (Exception e){e.getStackTrace();}


    }

}










