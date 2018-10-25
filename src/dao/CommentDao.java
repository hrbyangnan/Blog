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


    public void deleteComment(int commentId) {
        System.out.println("1:vb");
        Integer i = commentId;
        if (i == null) {
            return;
        }

        System.out.println("2:vb");
        String sql1 = "DELETE FROM commentInComment WHERE FatherCommentId=?";
        String sql2 = "DELETE FROM comment WHERE CommentId=?";

        try (PreparedStatement ps = conn.prepareStatement(sql1)) {
            System.out.println("3:vb " + ps);
            ps.setObject(1, commentId);
            System.out.println("4:vb " + ps);
            ps.executeUpdate();
        } catch (Exception e) {

        }
        try (PreparedStatement ps = conn.prepareStatement(sql2)) {
            System.out.println("5:vb " + ps);
            ps.setObject(1, commentId);
            System.out.println("6:vb " + ps);
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
        System.out.println("before insert CommentDAO");

        String sql = "INSERT INTO comment(ArticleId,CommentContent,UserId,UserName) values(?,?,?,?)";
        System.out.println("after insert CommentDAO");

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, artc.getCommentId());
            ps.setInt(1, artc.getArticleId());
            System.out.println("1: " + artc.getArticleId());

            ps.setString(2, artc.getCommentContent());
            System.out.println("2: " + artc.getCommentContent());

            ps.setInt(3, artc.getUserId());
            System.out.println("3: " + artc.getUserId());

            ps.setString(4, artc.getUserName());
            System.out.println("4: " + artc.getUserName());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("inside catch CommentDAO");

        }
    }

    // select all commend of one article

    public List<Comment> selectComByArt(int articleId) {
        Integer i = articleId;
        List<Comment> comments = new ArrayList<>();
        System.out.println("This is the id we passed in:" + i);

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

    public void updateComment(Comment comm) throws SQLException {

        System.out.println("About to try prepared statement");
        try (PreparedStatement ps = conn.prepareStatement("UPDATE comment SET CommentContent=? WHERE CommentId=?;")) {

            ps.setObject(1, comm.getCommentContent());
            System.out.println("content: " + comm.getCommentContent());
            System.out.println("id: " + comm.getCommentId());

            System.out.println("SQL is about to be executed for update comment..");
            ps.executeUpdate();
            System.out.println("SQL has been executed inside dao still");
        } catch (SQLException e) {
            System.out.println("We are throwing e ");

        }
    }

    public Comment findOneComment(int commentId) {
        Integer i = commentId;

        if (i == null) {
            return null;
        }

        Comment com = new Comment();
        System.out.println("Trying to get one com dao");

        try (PreparedStatement ps = conn.prepareStatement("SELECT CommentContent FROM comment WHERE CommentId=?")) {

            System.out.println("Statement has been prepared");
            System.out.println("value of commentId: " + commentId);
            ps.setInt(1, i);
            System.out.println("value of commentId: " + i);
            ResultSet rs = ps.executeQuery();
            System.out.println("find one comment has been executed");
            while (rs.next()) {
                com.setCommentContent(rs.getString(1));
            }
            return com;

        } catch (SQLException e) {
            System.out.println("We are throwing an SQL exception, I don;t know why");

        }
        return com;
    }

    public void HideComment(int commentId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE comment SET visible=? WHERE CommentId=?;")) {

            ps.setInt(1, 0);
            ps.setInt(2, commentId);

            ps.executeUpdate();

        }
    }

    public void ShowComment(int commentId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE comment SET visible=? WHERE CommentId=?;")) {

            ps.setInt(1, 1);
            ps.setInt(2, commentId);
            ps.executeUpdate();

        }
    }
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
//            articleList = translate(rs);
            System.out.println("inside try after query");
            return commentList;
//        }
// catch (SQLException e) {
//            e.getMessage();
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//
//                e.printStackTrace();
//            }
//        }
//        System.out.println("This is in getAllArticles method in dao but it shouldn't be reached");
//        return commentList;
        }
    }

    public String getPicPath(int userId) throws SQLException {
        String path=null;
        System.out.println("get Picpath");
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
