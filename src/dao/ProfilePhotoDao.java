package dao;

import pojo.ProfilePhoto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProfilePhotoDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //add photo
    public void addPhoto(ProfilePhoto p) {
        if (p == null) {
            return;
        }
        ProfilePhoto photo = (ProfilePhoto) p;
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
    public ProfilePhoto deletePhoto(ProfilePhoto p) {
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
    public List<ProfilePhoto> selectAll() {
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
    public List<ProfilePhoto> selectByUser(int userId) {
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
    private List<ProfilePhoto> translate(ResultSet rs) {

        List<ProfilePhoto> l = new ArrayList<ProfilePhoto>();
        try {
            while (rs.next()) {
                ProfilePhoto photo = new ProfilePhoto(rs.getInt("PhotoId"), rs.getInt("UserId"), rs.getString("Url"));
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