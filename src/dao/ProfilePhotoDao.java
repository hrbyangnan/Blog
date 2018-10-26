package dao;

import pojo.ProfilePhoto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProfilePhotoDao {
    private final Connection conn;

    private PreparedStatement ps;

    public ProfilePhotoDao() throws SQLException {
        System.out.println("before connection");
        this.conn = HikariConnectionPool.getConnection();
        System.out.println("after connection");
    }

    //add photo
    public void addPhoto(ProfilePhoto p) {
        if (p == null) {
            return;
        }
        String sql = "insert into photo(UserId,photoId) values(?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getUserId());
            ps.setString(2, p.getUrl());
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


        String sql = "delete from Photo where photoId=?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
          ps.setInt(1, p.getPhotoId());

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




}