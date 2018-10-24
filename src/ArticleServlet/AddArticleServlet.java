//
//		Created by IntelliJ IDEA.
//		User: Neco Yang
//		Date: 2018/10/5
//		Time: 13:14
//		To change this template use File | Settings | File Templates.
//
package ArticleServlet;

import dao.ArticleDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Article;
import pojo.User;
import Multimedia.ThumbnailGenerator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AddArticleServlet extends HttpServlet {
    private File uploadsFolder;
    private File tempFolder;
    private File thumbnailsFolder;
private File mediaFolder;

private ArrayList<String> pathnames;
private ArrayList<String> mediaPaths;

    @Override
    public void init() throws ServletException {
        super.init();
        initUploadFolder();
    }

    public void initUploadFolder() {
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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        System.out.println("try to get author attributes from session inside add article");
        User author = (User) userSession.getAttribute("userInfo");
        System.out.println(author.getRealName());
        System.out.println("inside addarticle servlet");


        System.out.println("inside addarticle servlet");




        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024);
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);
        System.out.println("AAA");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String articleName = null;
        String articleContent = null;
        String pubTime = null;
        String fileName = null;
        System.out.println("BBB");
        String random = String.valueOf((int)(Math.random()*(9999-1000+1))+1000);
        pathnames = new ArrayList<>();
        mediaPaths = new ArrayList<>();

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            File fullsizeImagefile = null;

            System.out.println("CCC");
            for (FileItem fi : fileItems) {
                if (!fi.isFormField()) {
                    //if filename null don't do this
                    if(fi.getSize()>1){
                    fileName = fi.getName();

                    //Check if file type is image
                        if(fi.getContentType().contains("image")){
                            fullsizeImagefile = new File(uploadsFolder, random+"_"+fileName);
                    fi.write(fullsizeImagefile);
                    //don't
                    pathnames.add(fileName);
                    //INSERT THUMBNAIL GENERATOR HERE
                    ThumbnailGenerator.generateThumb(fullsizeImagefile,random+"_"+fileName,thumbnailsFolder);
                    }
                    else if(fi.getContentType().contains("audio")||fi.getContentType().contains("video")){
                        File fullsizeAudioFile = new File(mediaFolder,random+"_"+fileName);
                        fi.write(fullsizeAudioFile);
                        mediaPaths.add(random+"_"+fileName);

                    }}
                    System.out.println("got to here..");
                } else if (fi.getFieldName() != null) {
                    if (fi.getFieldName().equals("articleName")) {
                        articleName = fi.getString();
                    }
                    if (fi.getFieldName().equals("articleContent")) {
                        articleContent = fi.getString();
                    }
                    if (fi.getFieldName().equals("pubTime")) {
                        pubTime = fi.getString();
                    }
                }
            }

           /* out.println("<img src=/Uploaded_Photos/" + fullsizeImagefile.getName() + " width=\"200\">");
            out.println("Photo is already uploaded.");

            System.out.println(fullsizeImagefile.getName());*/
        } catch (Exception e) {
            throw new ServletException(e);
        }


        //SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        System.out.println(pubTime);
        java.util.Date d = null;
        System.out.println("DDD");
        String picPath = "";
        if(fileName!=null){
         picPath = "/Uploaded_Photos/" + random+"_"+fileName;}

        try {
            d = format.parse(pubTime);

        } catch (Exception e) {
            e.printStackTrace();
        }

        java.sql.Time date = new java.sql.Time(d.getTime());

//        int loginUserId = Integer.parseInt(request.getSession().getAttribute("userId").toString());

        System.out.println("EEE");
        Article newArticle = new Article();
        newArticle.setArticleName(articleName);
        System.out.println(newArticle.getArticleName());
        newArticle.setArticleContent(articleContent);
        System.out.println(articleContent);
        newArticle.setPubTime(date);
        System.out.println(date);
        System.out.println(author.getRealName());
        newArticle.setRealName(author.getRealName());
        newArticle.setUserId(author.getId());
        newArticle.setPicPath(picPath);
        newArticle.setVisible(1);
//        int loginUserId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        int loginUserId = author.getId();
        //temporary until login is set up
        System.out.println("FFF");
        //
        //Article newArticle = new Article(loginUserId, articleName, articleContent, realName);
        try (ArticleDao dao = new ArticleDao()) {
            dao.addArticle(newArticle);
            System.out.println("GGG");
            int articleNumber = dao.getLastID();
            System.out.println("HHH articlenumber is "+articleNumber);
            //need to check for null
            for(String path: pathnames){
                dao.insertPhotoinf(articleNumber,"/Uploaded_Photos/"+random+"_"+path);
            }
            for(String path: mediaPaths){
                dao.insertMediaInf(articleNumber,"/Uploaded_Multimedia/"+random+"_"+path);
            }
            System.out.println("If this prints the loop wasn;t the problem");
            //List<Article> articles = dao.selectArtByUser(loginUserId);

            System.out.println("IIIi");
            //request.getSession().setAttribute("userArticles", articles);
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Submitted!");
            response.sendRedirect("personalpage.jsp");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("inside articleservlet catch");
        } catch (Exception e) {
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);


    }

}
