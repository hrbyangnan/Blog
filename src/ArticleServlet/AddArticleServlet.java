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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddArticleServlet extends HttpServlet {
    private File uploadsFolder;
    private File tempFolder;


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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String articleName = null;
        String articleContent = null;
        String pubTime = null;
        String fileName = null;

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            File fullsizeImagefile = null;


            for (FileItem fi : fileItems) {
                if (!fi.isFormField()) {
                    fileName = fi.getName();
                    fullsizeImagefile = new File(uploadsFolder, fileName);
                    fi.write(fullsizeImagefile);
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

            out.println("<img src=/Uploaded_Photos/" + fullsizeImagefile.getName() + " width=\"200\">");
            out.println("Photo is already uploaded.");

            System.out.println(fullsizeImagefile.getName());
        } catch (Exception e) {
            throw new ServletException(e);
        }


        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
        java.util.Date d = null;

        String picPath = "/Uploaded_Photos/" + fileName;

        try {
            d = format.parse(pubTime);

        } catch (Exception e) {
            e.printStackTrace();
        }

        java.sql.Time date = new java.sql.Time(d.getTime());

//        int loginUserId = Integer.parseInt(request.getSession().getAttribute("userId").toString());


        Article newArticle = new Article();
        newArticle.setArticleName(articleName);
        newArticle.setArticleContent(articleContent);
        newArticle.setPubTime(date);

        System.out.println(author.getRealName());
        newArticle.setRealName(author.getRealName());
        newArticle.setUserId(author.getId());
        newArticle.setPicPath(picPath);
//        int loginUserId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        int loginUserId = author.getId();
        //temporary until login is set up

        //
        //Article newArticle = new Article(loginUserId, articleName, articleContent, realName);
        try (ArticleDao dao = new ArticleDao()) {
            dao.addArticle(newArticle);
            List<Article> articles = dao.selectArtByUser(loginUserId);

            request.getSession().setAttribute("userArticles", articles);
            response.sendRedirect("personalpage.jsp");
        } catch (SQLException e) {
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
