package dao;


import pojo.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class ArticleDao implements AutoCloseable {
    private final Connection conn;

    //private PreparedStatement ps;
    //private ResultSet rs;

    public ArticleDao() throws SQLException {
        System.out.println("before connection");
        this.conn = HikariConnectionPool.getConnection();
        System.out.println("after connection");
    }

//    {
//        try {
//            conn = HikariConnectionPool.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    //add article
    public void addArticle(Article artc) {
        String sql = "INSERT INTO article(UserId,ArticleTitle,ArticleContent,PubTime,PicPath,RealName,visible)  VALUES(?,?,?,?,?,?,?);";
        System.out.println("before prepared statement");
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, artc.getUserId());
            ps.setString(2, artc.getArticleName());
            ps.setString(3, artc.getArticleContent());
            ps.setTimestamp(4, new Timestamp(artc.getPubTime().getTime()));
            ps.setString(5, artc.getPicPath());
            ps.setString(6, artc.getRealName());
            ps.setInt(7,artc.getVisible());
//            ps.setTime(4, artc.getPubTime());
            System.out.println("before execute update");
            ps.executeUpdate();
            System.out.println("after execute update");
        } catch (Exception e) {

        }
    }

    //delete article
    public void deleteArticle(int articleId) {
        Integer i = articleId;
        if (i == null) {
            return;
        }
        String sql = "DELETE FROM article WHERE ArticleId=?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, articleId);


            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    //undate article
    public void updateArticle(Article artc) throws SQLException {

        String sql = "UPDATE article SET ArticleTitle=?,ArticleContent=?,PubTime=?,PicPath=? WHERE ArticleId=?";
        System.out.println("About to try prepared statement");
        try (PreparedStatement ps = conn.prepareStatement("UPDATE article SET ArticleTitle=?,ArticleContent=?,PubTime=?,PicPath=? WHERE ArticleId=?;")){


            ps.setObject(1, artc.getArticleName());
            System.out.println("Inside the DAO trying to use "+ artc.getArticleName());
            ps.setObject(2, artc.getArticleContent());
            ps.setObject(3, artc.getPubTime());
            ps.setObject(4, artc.getPicPath());
            ps.setInt(5,artc.getArticleId());
            //Note to James also need to make changes for multiple images
            System.out.println("SQL is about to be executed");
            ps.executeUpdate();
            System.out.println("SQL has been executed inside dao still");
        }
    }






    private List<Article> translate(ResultSet rs) {
        List<Article> l = new ArrayList<Article>();
        try {
            while (rs.next()) {
                Article artc = new Article(rs.getInt("ArticleId"), rs.getString("ArticleName"), rs.getString("ArticleContent"));
                l.add(artc);
            }
        } catch (Exception e) {

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return l;

    }

    public List<Article> selectArtByUser(int userId) {
        Integer i = userId;
        List<Article> articleList = new ArrayList<>();
        System.out.println("This is the id we passed in:" + i);
        if (i == null) {
            return null;
        }
        String sql = "SELECT * FROM article WHERE UserId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setObject(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Article currentArticle = new Article();
                currentArticle.setArticleId(rs.getInt(1));
                currentArticle.setUserId(rs.getInt(2));
                currentArticle.setRealName(rs.getString(3));
                currentArticle.setArticleName(rs.getString(4));
                currentArticle.setArticleContent(rs.getString(5));
                currentArticle.setPubTime(rs.getDate(6));
                currentArticle.setPicPath(rs.getString(7));
                currentArticle.setVisible(rs.getInt(8));
                currentArticle.setAllPicPaths(getPicPaths(currentArticle.getArticleId()));
                currentArticle.setMedia(getMediaPaths(currentArticle.getArticleId()));
                articleList.add(currentArticle);
            }
            if (articleList != null) {
                return articleList;
            } else {
                return null;
            }
        } catch (Exception e) {

        }
        return articleList;
    }

    public Article findOneArticle(int articleId) {
        Integer i = articleId;

        if (i == null) {
            return null;
        }
        String sql = "SELECT * FROM article WHERE ArticleId=?";

        Article artc = new Article();
        artc.setArticleName("Placeholder");
        System.out.println("Trying to get one article - James");

        try (PreparedStatement ps=conn.prepareStatement("SELECT * FROM article WHERE ArticleId=?")){


            System.out.println("Statement has been prepared");
            ps.setInt(1, i);
            System.out.println(ps + " James mucking around");
            ResultSet rs = ps.executeQuery();
            System.out.println("find one article has been executed");

            while (rs.next()) {

                artc.setArticleId(rs.getInt(1));
                artc.setArticleName(rs.getString(4));
                artc.setArticleContent(rs.getString(5));
                artc.setPubTime(rs.getDate(6));
                artc.setPicPath(rs.getString(7));
                artc.setRealName(rs.getString(3));
                artc.setAllPicPaths(getPicPaths(artc.getArticleId()));
                artc.setMedia(getMediaPaths(artc.getArticleId()));
                artc.setUserId(rs.getInt(2));

            }
            return artc;

        } catch (SQLException e) {
            System.out.println("We are throwing an SQL exception, I don;t know why");

        }
        return artc;
    }

// This method gets article id, article name and article content from the database and returns a list of POJOs

    public List<Article> getAllArticles() {
        List<Article> articleList = new ArrayList<>();
        System.out.println("before try");
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM article;")) {


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Article currentArticle = new Article();
                currentArticle.setArticleId(rs.getInt(1));
                currentArticle.setUserId(rs.getInt(2));
                currentArticle.setRealName(rs.getString(3));
                currentArticle.setArticleName(rs.getString(4));
                currentArticle.setArticleContent(rs.getString(5));
                currentArticle.setPubTime(rs.getDate(6));
                currentArticle.setPicPath(rs.getString(7));
                currentArticle.setVisible(rs.getInt(8));
                //System.out.println(getPicPaths(140));
                currentArticle.setAllPicPaths(getPicPaths(currentArticle.getArticleId()));
currentArticle.setMedia(getMediaPaths(currentArticle.getArticleId()));
                articleList.add(currentArticle);
            }
//            articleList = translate(rs);

            return articleList;
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        System.out.println("This is in getAllArticles method in dao but it shouldn't be reached");
        return articleList;
    }



    public int getLastID() throws SQLException{
        int idPlaceholder;
        System.out.println("Inside dao trying to get lastID");
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT MAX(ArticleId) FROM article")) {
            System.out.println("Prepared statement");
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Statement executed");
                rs.next();
                idPlaceholder = rs.getInt(1);
                System.out.println(idPlaceholder);
            }
        }return idPlaceholder;
    }

    public void insertPhotoinf(int articleID, String picpath) throws SQLException{
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO articlePhoto (ArticleId,photoUrl) VALUES (?,?);")){
            stmt.setInt(1,articleID);
            stmt.setString(2,picpath);
            stmt.executeUpdate();
        }
    }

    public void insertMediaInf(int articleID, String mediaPath) throws SQLException{
        try(PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO multimedia(ArticleId, mediaURL) VALUES (?,?);")){
            stmt.setInt(1,articleID);
            stmt.setString(2,mediaPath);
            stmt.executeUpdate();
        }
    }

    public List<String> getPicPaths(int articleID){
        List<String> allPaths = new ArrayList<>();
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT photoUrl from articlePhoto WHERE ArticleId = ?")){
            stmt.setInt(1,articleID);
            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    allPaths.add(rs.getString(1));
                }
                return allPaths; }
        }catch(SQLException e ){e.getMessage();}catch (Exception e){e.getMessage();}

        return allPaths;
    }

    public List<String> getMediaPaths(int articleID){
        List<String> allPaths = new ArrayList<>();
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT mediaURL from multimedia WHERE ArticleId = ?")){
            stmt.setInt(1,articleID);
            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    allPaths.add(rs.getString(1));
                }
                return allPaths; }
        }catch(SQLException e ){e.getMessage();}catch (Exception e){e.getMessage();}

        return allPaths;
    }

    public void HideArticle(int ArticleId) throws SQLException {
        System.out.println("hide 1");
        try (PreparedStatement ps = conn.prepareStatement("UPDATE article SET visible=? WHERE ArticleId=?;")){
            System.out.println("hide 2");

            ps.setInt(1, 0);
            System.out.println("hide 3");
            ps.setInt(2, ArticleId);
            System.out.println("hide 4");

            ps.executeUpdate();

        }
    }
    public void ShowArticle(int ArticleId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE article SET visible=? WHERE ArticleId=?;")){


            ps.setInt(1, 1);
            ps.setInt(2, ArticleId);

            ps.executeUpdate();

        }
    }
    public List<Article> SearchArticle(String info) {

        List<Article> articleList = new ArrayList<>();

        String sql = "SELECT * FROM article WHERE RealName like ? or ArticleTitle like ? or PubTime like ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String temp="%"+info+"%";

            ps.setObject(1, temp);
            ps.setObject(2, temp);
            ps.setObject(3, temp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Article currentArticle = new Article();
                currentArticle.setArticleId(rs.getInt(1));
                currentArticle.setUserId(rs.getInt(2));
                currentArticle.setRealName(rs.getString(3));
                currentArticle.setArticleName(rs.getString(4));
                currentArticle.setArticleContent(rs.getString(5));
                currentArticle.setPubTime(rs.getDate(6));
                currentArticle.setPicPath(rs.getString(7));
                currentArticle.setVisible(rs.getInt(8));
                currentArticle.setAllPicPaths(getPicPaths(currentArticle.getArticleId()));
                articleList.add(currentArticle);
            }
            if (articleList != null) {
                return articleList;
            } else {
                return null;
            }
        } catch (Exception e) {

        }
        return articleList;
    }

    //Gets picture paths for for a particular user
    public Set<String> getPicPathsByUser(int userID)throws SQLException{
        Set<String> allDistinctPicPaths = new TreeSet<>();
        try(PreparedStatement stmt = this.conn.prepareStatement("SELECT DISTINCT photoURL FROM articlePhoto, article WHERE articlePhoto.ArticleId=article.ArticleId AND article.UserId = ?;")){
            stmt.setInt(1,userID);
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    allDistinctPicPaths.add(rs.getString(1));
                }
                return allDistinctPicPaths;
            }
        }

    }
    //Get all media filenames for one user
    public Set<String> getMediaPathsByUser(int userID)throws SQLException{
        Set<String> allDistinctMediaPaths = new TreeSet<>();
        try(PreparedStatement stmt = this.conn.prepareStatement("SELECT DISTINCT mediaURL FROM multimedia, article WHERE multimedia.ArticleId=article.ArticleId AND article.UserId = ?;")){
            stmt.setInt(1,userID);
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    allDistinctMediaPaths.add(rs.getString(1));
                }
                return allDistinctMediaPaths;
            }
        }

    }

    public void deleteOldPhotos(int id) {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM articlePhoto WHERE ArticleId=?")){
            System.out.println("Could be a problem delete old files");
            ps.setInt(1,id);

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Tried to delete old photos");}
    }
    public void deleteOldMedia(int id) {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM multimedia WHERE ArticleId=?")){
            System.out.println("Could be a problem delete old media");
            ps.setInt(1,id);

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Tried to delete old media");}
    }
    public String getPicPath(int userId) throws SQLException {
        String path=null;
        try (PreparedStatement ps = conn.prepareStatement("select ProfilePath from user WHERE UserId=?;")) {
            ps.setInt(1, userId);
            try(ResultSet rs=ps.executeQuery()){
                rs.next();
                path=rs.getString(1);
            }
        }
        return path;
    }
    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}
