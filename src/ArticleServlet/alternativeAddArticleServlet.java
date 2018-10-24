/*
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

public class alternativeAddArticleServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        System.out.println("try to get author attributes from session inside add article");
        User author = (User) userSession.getAttribute("userInfo");
        System.out.println(author.getRealName());
        System.out.println("inside addarticle servlet");


        System.out.println("inside addarticle servlet");




        //SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        java.util.Date d = null;
        System.out.println("DDD");
        String picPath ="/Uploaded_Photos";
        */
/*String picPath = "/Uploaded_Photos/" + fileName;*//*


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
//        int loginUserId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        int loginUserId = author.getId();
        //temporary until login is set up
        System.out.println("FFF");
        //
        //Article newArticle = new Article(loginUserId, articleName, articleContent, realName);
        try (ArticleDao dao = new ArticleDao()) {
            dao.addArticle(newArticle);
            List<Article> articles = dao.selectArtByUser(loginUserId);
            System.out.println("GGG");
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
*/
