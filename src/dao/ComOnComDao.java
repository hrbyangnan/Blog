package dao;

import pojo.Comment;
import pojo.CommentOnComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComOnComDao implements AutoCloseable {
    private final Connection conn;

    //Use Hikari connection pool to access database
    public ComOnComDao() throws SQLException {
        System.out.println("before connection inside ComOnComDao");
        this.conn = HikariConnectionPool.getConnection();
        System.out.println("after connection inside ComOnComDao");
    }

    PreparedStatement ps = null;
    ResultSet rs = null;


    public void deleteCommentOnComment(int commentId) {
        Integer i = commentId;
        if (i == null) {
            return;
        }
        String sql = "delete from commentInComment where CommentId=?";
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


    public void addCommentOnComment(CommentOnComment artc) {

        String sql = "INSERT INTO commentInComment(FatherCommentId,CommentContent,UserId,UserName) values(?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, artc.getFatherCommentId());

            ps.setString(2, artc.getCommentContent());

            ps.setInt(3, artc.getUserId());

            ps.setString(4, artc.getUserName());

           // ps.setInt(5,1);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());

        }
    }

    // select all commend of one article
    public List<CommentOnComment> selectComByCom(int commentId) {
        Integer i = commentId;
        List<CommentOnComment>commentinCommentList = new ArrayList<>();

        if (i == null) {
            return null;
        }
        String sql = "SELECT * FROM commentInComment WHERE FatherCommentId=?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, commentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CommentOnComment currentComment = new CommentOnComment();
                currentComment.setCommentId(rs.getInt(1));
                currentComment.setFatherCommentId(rs.getInt(2));
                currentComment.setCommentContent(rs.getString(3));
                currentComment.setUserId(rs.getInt(4));
                currentComment.setUserName(rs.getString(5));
                currentComment.setVisible(rs.getInt(6));
                commentinCommentList.add(currentComment);
            } if (commentinCommentList != null) {
                return commentinCommentList;
            } else {
                return null;
            }
        } catch (Exception e) {

        }
        return commentinCommentList;
    }
    public CommentOnComment findOneComment(int commentId) {
        Integer i = commentId;

        if (i == null) {
            return null;
        }

        CommentOnComment com = new CommentOnComment();

        try (PreparedStatement ps=conn.prepareStatement("SELECT CommentContent FROM commentInComment WHERE CommentId=?")){

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
    public void updateComment(CommentOnComment comm) throws SQLException {

    }
    public List<CommentOnComment> getAllComoncom() {
        List<CommentOnComment> commentOnCommentList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM commentInComment;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                CommentOnComment cc = new CommentOnComment();
                cc.setCommentId(rs.getInt(1));
                cc.setFatherCommentId(rs.getInt(2));
                cc.setCommentContent(rs.getString(3));
                cc.setUserId(rs.getInt(4));
                cc.setUserName(rs.getString(5));
                cc.setVisible(rs.getInt(6));

                commentOnCommentList.add(cc);
            }
              return commentOnCommentList;
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
         return commentOnCommentList;
    }
    public void HideComOnCom(int comOnComId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE commentInComment SET visible=? WHERE CommentId=?;")){


            ps.setInt(1, 0);
            ps.setInt(2, comOnComId);

            ps.executeUpdate();

        }
    }

    public void ShowComOnCom(int comOnComId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE commentInComment SET visible=? WHERE CommentId=?;")){


            ps.setInt(1, 1);
            ps.setInt(2, comOnComId);

            ps.executeUpdate();

        }
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
        conn.close();
    }
}
