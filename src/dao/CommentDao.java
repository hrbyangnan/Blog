package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import pojo.Comment;

public class CommentDao {
    private final Connection conn;

    //Use Hikari connection pool to access database
    public CommentDao() throws SQLException {

        this.conn = HikariConnectionPool.getConnection();

    }

    PreparedStatement ps = null;
    ResultSet rs = null;


    public void deleteComment(int commentId) {
        Integer i = commentId;
        if (i == null) {
            return;
        }
        String sql = "delete from comment where CommentId=?";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, commentId);
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


    public void addComment(Comment artc) {

        String sql = "insert into Comment(CommentContent,commentID,ArticleId,UserId,UserName) values(?,?,?,?,?)";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, artc.getCommentContent());
            ps.setObject(2, artc.getCommentId());
            ps.setObject(3, artc.getArticleId());
            ps.setObject(4, artc.getUserId());
            ps.setObject(5, artc.getUserName());
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

    // select all commend of one article

    public List<Comment> selectComByArt(int articleId) {
        Integer i = articleId;

        if (i == null) {
            return null;
        }
        String sql = "select * from comment where articleId=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, articleId);
            rs = ps.executeQuery();
            return translate(rs);
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


    private List<Comment> translate(ResultSet rs) {
        List<Comment> l = new ArrayList<Comment>();
        try {
            while (rs.next()) {
                Comment artc = new Comment(rs.getString("commentContent"), rs.getInt("commendID"),
                        rs.getInt("articleId"),
                        rs.getInt("userId"), rs.getString("userName"));
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

}
