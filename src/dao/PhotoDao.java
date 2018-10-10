package dao;

import pojo.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PhotoDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //add photo
    public void addPhoto(Photo p) {
        if (p == null) {
            return;
        }
        Photo photo = (Photo) p;
        String sql = "insert into Photo(UserId,Url) values(?,?)";
        try {
            this.conn = HikariConnectionPool.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, photo.getUserId());
            ps.setObject(2, photo.getUrl());
            ps.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    //delete photo
    public Photo deletePhoto(Photo p) {
        if (p == null || p.getPhotoId() == 0) {
            return null;
        }


        String sql = "delete from Photo where PhotoId=?";
        try {
            this.conn = HikariConnectionPool.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, p.getPhotoId());

            ps.executeUpdate();
            return p;
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    //select photo
    public List<Photo> selectAll() {
        String sql = "select *from Photo";
        try {
            this.conn = HikariConnectionPool.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return translate(rs);
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return null;
    }

    //select user's photo
    public List<Photo> selectByUser(int userId) {
        String sql = "select *from Photo where userId=?";
        try {
            this.conn = HikariConnectionPool.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, userId);
            rs = ps.executeQuery();
            return translate(rs);
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return null;
    }

    // translate result
    private List<Photo> translate(ResultSet rs) {

        List<Photo> l = new ArrayList<Photo>();
        try {
            while (rs.next()) {
                Photo photo = new Photo(rs.getInt("PhotoId"), rs.getInt("UserId"), rs.getString("Url"));
                l.add(photo);


            }
        } catch (SQLException e) {

            e.printStackTrace();
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