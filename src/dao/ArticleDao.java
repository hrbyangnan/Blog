package dao;


import pojo.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArticleDao {
    Connection conn;

    {
        try {
            conn = HikariConnectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    PreparedStatement ps = null;
    ResultSet rs = null;


    //add article
    public void addArticle(Article artc) {
        String sql = "insert into aricle(UserId,ArticleName,ArticleContent) values(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, artc.getUserId());
            ps.setObject(2, artc.getArticleName());
            ps.setObject(3, artc.getArticleContent());

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

    //delete article
    public void deleteArticle(int articleId) {
        Integer i = articleId;
        if (i == null) {
            return;
        }
        String sql = "delete from aricle where ArticleId=?";
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

        String sql = "update aricle set ArticleName=?,ArticleContent=? where ArticleId=?";
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
        String sql = "select * from aricle where UserId=?";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, userId);
            rs = ps.executeQuery();
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
        String sql = "select * from aricle where ArticleId=?";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, articleId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Article artc = new Article(rs.getInt("ArticleId"), rs.getString("ArticleName"), rs.getString("ArticleContent"));
                return artc;
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
}
