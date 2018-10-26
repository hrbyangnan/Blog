package dao;

import pojo.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements AutoCloseable {
    private final Connection conn;

    //Use Hikari connection pool to access database
    public CommentDao() throws SQLException {
        System.out.println("before connection inside CommentDAO");
        this.conn = HikariConnectionPool.getConnection();
        System.out.println("after connection inside CommentDAO");

    }

    PreparedStatement ps = null;
    ResultSet rs = null;

// Servlet calls this dao method to delete comment from database
    public void deleteComment(int commentId) {
         Integer i = commentId;
        if (i == null) {
            return;
        }

         String sql1 = "DELETE FROM commentInComment WHERE FatherCommentId=?";
        String sql2 = "DELETE FROM comment WHERE CommentId=?";

        try (PreparedStatement ps = conn.prepareStatement(sql1)) {
             ps.setObject(1, commentId);
             ps.executeUpdate();
        } catch (Exception e) {

        }
        try (PreparedStatement ps = conn.prepareStatement(sql2)) {
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


// This method gets comment from the user and adds it to the database
    public void addComment(Comment artc) {

        String sql = "INSERT INTO comment(ArticleId,CommentContent,UserId,UserName) values(?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, artc.getArticleId());

            ps.setString(2, artc.getCommentContent());

            ps.setInt(3, artc.getUserId());

            ps.setString(4, artc.getUserName());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());

        }
    }

    // to get all comment of one article by using article id
    public List<Comment> selectComByArt(int articleId) {
        Integer i = articleId;
        List<Comment> comments = new ArrayList<>();

        if (i == null) {
            return null;
        }
        String sql = "SELECT * FROM comment WHERE ArticleId=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, articleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment currentComment = new Comment();
                currentComment.setCommentId(rs.getInt(1));
                currentComment.setArticleId(rs.getInt(2));
                currentComment.setCommentContent(rs.getString(3));
                currentComment.setUserId(rs.getInt(4));
                currentComment.setUserName(rs.getString(5));
                currentComment.setVisible(rs.getInt(6));
                comments.add(currentComment);
            }
            if (comments != null) {
                return comments;
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
        return comments;
    }



    /*
     * We created this method to edit comments on comments but realised it was not necessary for the brief.
     * Left in incase of future upgrade.
     * */
    public void updateComment(Comment comm) throws SQLException {

         try (PreparedStatement ps = conn.prepareStatement("UPDATE comment SET CommentContent=? WHERE CommentId=?;")) {

            ps.setObject(1, comm.getCommentContent());
            System.out.println("content: " + comm.getCommentContent());
            System.out.println("id: " + comm.getCommentId());

             ps.executeUpdate();
         } catch (SQLException e) {
             System.out.println("SQLException " + e.getMessage());

        }
    }
    /*
     * We created this method to edit comments on comments but realised it was not necessary for the brief.
     * Left in incase of future upgrade.
     * */
    public Comment findOneComment(int commentId) {
        Integer i = commentId;

        if (i == null) {
            return null;
        }
        Comment com = new Comment();

        try (PreparedStatement ps = conn.prepareStatement("SELECT CommentContent FROM comment WHERE CommentId=?")) {

             ps.setInt(1, i);
             ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                com.setCommentContent(rs.getString(1));
            }
            return com;

        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
        return com;
    }

    /*
     * Allows an admin to make visible a hidden comment. Gets comment id and updates the database
     * */
    public void HideComment(int commentId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE comment SET visible=? WHERE CommentId=?;")) {

            ps.setInt(1, 0);
            ps.setInt(2, commentId);

            ps.executeUpdate();

        }
    }

    /*
     * Allows an admin to make hidden comment visible. Gets comment id and updates the database
     * */
    public void ShowComment(int commentId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE comment SET visible=? WHERE CommentId=?;")) {

            ps.setInt(1, 1);
            ps.setInt(2, commentId);
            ps.executeUpdate();

        }
    }

    //Gets comment id and gets article title for the table of admin interface
    public String getArticleTitle(int commentId) throws SQLException {
        String title=null;
        try (PreparedStatement ps = conn.prepareStatement("select ArticleTitle from article,comment WHERE article.ArticleId=comment.ArticleId and CommentId=?;")) {
            ps.setInt(1, commentId);
            try(ResultSet rs=ps.executeQuery()){
                rs.next();
                title=rs.getString(1);
            }
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        return title;
    }

    //gets all the comments
    public List<Comment> getAllComments() throws SQLException {
        List<Comment> commentList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM comment;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setCommentId(rs.getInt(1));
                c.setArticleId(rs.getInt(2));
                c.setCommentContent(rs.getString(3));
                c.setUserId(rs.getInt(4));
                c.setUserName(rs.getString(5));
                c.setVisible(rs.getInt(6));
                commentList.add(c);
            }
              return commentList;

        }
    }

    //gets the avatar of user by using user id for article, comment, and comments on comments
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
        conn.close();
    }
}
