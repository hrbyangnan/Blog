package dao;


import pojo.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class ArticleDao implements AutoCloseable {
    private final Connection conn;



    public ArticleDao() throws SQLException {
        System.out.println("before connection");
        this.conn = HikariConnectionPool.getConnection();
        System.out.println("after connection");
    }


    // inserts article
    public void addArticle(Article artc) {
        String sql = "INSERT INTO article(UserId,ArticleTitle,ArticleContent,PubTime,PicPath,RealName,visible)  VALUES(?,?,?,?,?,?,?);";
         try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, artc.getUserId());
            ps.setString(2, artc.getArticleName());
            ps.setString(3, artc.getArticleContent());
            ps.setTimestamp(4, new Timestamp(artc.getPubTime().getTime()));
            ps.setString(5, artc.getPicPath());
            ps.setString(6, artc.getRealName());
            ps.setInt(7,artc.getVisible());
             ps.executeUpdate();
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

    //update article
    public void updateArticle(Article artc) throws SQLException {

         try (PreparedStatement ps = conn.prepareStatement("UPDATE article SET ArticleTitle=?,ArticleContent=?,PubTime=?,PicPath=? WHERE ArticleId=?;")){


            ps.setObject(1, artc.getArticleName());
             ps.setObject(2, artc.getArticleContent());
            ps.setObject(3, artc.getPubTime());
            ps.setObject(4, artc.getPicPath());
            ps.setInt(5,artc.getArticleId());
             ps.executeUpdate();
         }
    }



    // This returns a list of articles by using user id
    public List<Article> selectArtByUser(int userId) {
        Integer i = userId;
        List<Article> articleList = new ArrayList<>();
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

    // This method gets one article by using article id

    public Article findOneArticle(int articleId) {
        Integer i = articleId;

        if (i == null) {
            return null;
        }

        Article artc = new Article();
        artc.setArticleName("Placeholder");
        try (PreparedStatement ps=conn.prepareStatement("SELECT * FROM article WHERE ArticleId=?")){
             ps.setInt(1, i);
             ResultSet rs = ps.executeQuery();

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
            System.out.println("exception "+ e);

        }
        return artc;
    }

// This method gets a list of articles

    public List<Article> getAllArticles() {
        List<Article> articleList = new ArrayList<>();
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
                 currentArticle.setAllPicPaths(getPicPaths(currentArticle.getArticleId()));
currentArticle.setMedia(getMediaPaths(currentArticle.getArticleId()));
                articleList.add(currentArticle);
            }

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
         return articleList;
    }

//Because the article id is generated by the database as autoincrement it is not present in this article POJO.
// We need to get this article ID to use to insert into the photo and media tables
    public int getLastID() throws SQLException{
        int idPlaceholder;
         try (PreparedStatement stmt = this.conn.prepareStatement("SELECT MAX(ArticleId) FROM article")) {
             try (ResultSet rs = stmt.executeQuery()) {
                 rs.next();
                idPlaceholder = rs.getInt(1);
                System.out.println(idPlaceholder);
            }
        }return idPlaceholder;
    }


    //This adds a filename for a photo and the corresponding articleID into the photo database
    public void insertPhotoinf(int articleID, String picpath) throws SQLException{
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO articlePhoto (ArticleId,photoUrl) VALUES (?,?);")){
            stmt.setInt(1,articleID);
            stmt.setString(2,picpath);
            stmt.executeUpdate();
        }
    }
    //This adds a filename for media and the corresponding articleID into the media database
    public void insertMediaInf(int articleID, String mediaPath) throws SQLException{
        try(PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO multimedia(ArticleId, mediaURL) VALUES (?,?);")){
            stmt.setInt(1,articleID);
            stmt.setString(2,mediaPath);
            stmt.executeUpdate();
        }
    }
//This returns a list of photo filenames from the database corresponding to the desired articleID
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
//This returns a list of media filenames from the database corresponding to the desired articleID

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
//This sets the visibility parameter to 0 of a particular article (Visibility parameters: 1 is visble, 0 is hidden)
    public void HideArticle(int ArticleId) throws SQLException {
         try (PreparedStatement ps = conn.prepareStatement("UPDATE article SET visible=? WHERE ArticleId=?;")){

            ps.setInt(1, 0);
            ps.setInt(2, ArticleId);


            ps.executeUpdate();

        }
    }

    //This sets the visibility parameter to 1 of a particular article (Visibility parameters: 1 is visble, 0 is hidden)
    public void ShowArticle(int ArticleId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE article SET visible=? WHERE ArticleId=?;")){


            ps.setInt(1, 1);
            ps.setInt(2, ArticleId);

            ps.executeUpdate();

        }
    }

    /*
    * Returns a list of articles based on whether the user search string is found in the articles title, date or author fields
    * */
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
//removes rows from photo database based on article id
    public void deleteOldPhotos(int id) {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM articlePhoto WHERE ArticleId=?")){
             ps.setInt(1,id);

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException " + e.getMessage());
        }
    }
    //removes rows from media database based on article id
    public void deleteOldMedia(int id) {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM multimedia WHERE ArticleId=?")){
             ps.setInt(1,id);

            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
    }

    //Gets the filename of the users profile image from the database based on the userID
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
