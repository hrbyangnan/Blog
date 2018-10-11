package dao;


import pojo.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArticleDao implements AutoCloseable {
    private final Connection conn;

    private PreparedStatement ps;
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
        String sql = "INSERT INTO article(UserId,ArticleTitle,ArticleContent)  VALUES(?,?,?);";
        System.out.println("before prepared statement");
        try (PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, artc.getUserId());
            ps.setString(2, artc.getArticleName());
            ps.setString(3, artc.getArticleContent());
//            ps.setTime(4, artc.getPubTime());
            System.out.println("before execute update");
            ps.executeUpdate();
            System.out.println("after execute update");
        } catch (Exception e) {

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //delete article
    public void deleteArticle(int articleId) {
        Integer i = articleId;
        if (i == null) {
            return;
        }
        String sql = "DELETE FROM article WHERE ArticleId=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
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
    public void updateArticle(Article artc) {

        String sql = "UPDATE article SET ArticleName=?,ArticleContent=? WHERE ArticleId=?";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, artc.getArticleName());
            ps.setObject(2, artc.getArticleContent());
            ps.setObject(3, artc.getArticleId());
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
        if (i == null) {
            return null;
        }
        String sql = "SELECT * FROM article WHERE UserId=?";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Article> articleList = translate(rs);
            if (articleList != null) {
                return articleList;
            } else {
                return null;
            }
        } catch (Exception e) {

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return null;
    }

    public Article findOneArticle(int articleId) {
        Integer i = articleId;
        if (i == null) {
            return null;
        }
        String sql = "SELECT * FROM article WHERE ArticleId=?";
        Article artc = new Article();
        artc.setArticleName("Placeholder");
        try {
            System.out.println("Trying to get one article - James");
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, articleId);
            System.out.println(ps + " James mucking around");
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                artc.setArticleId(rs.getInt(1));
                artc.setArticleName(rs.getString(3));
             artc.setArticleContent(rs.getString(4));
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
            }
            return artc;

        } catch (SQLException e) {
            System.out.println("We are throwing an SQL exception, I don;t know why");

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return artc;
    }

// This method gets article id, article name and article content from the database and returns a list of POJOs

    public List<Article> getAllArticles() {
        List<Article> articleList = new ArrayList<>();
        System.out.println("before try");
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM article;")) {
            System.out.println("inside try before query");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Article currentArticle = new Article(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(7));
                articleList.add(currentArticle);
            }
//            articleList = translate(rs);
            System.out.println("inside try after query");
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
        return null;
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}
