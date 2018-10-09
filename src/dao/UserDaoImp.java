package dao;

import pojo.User;

import java.sql.*;

public class UserDaoImp implements UserDao {

    private final Connection conn;

    //Use Hikari connection pool to access database
    public UserDaoImp() throws SQLException {
        System.out.println("trying to setup Hikari connection");
        this.conn = HikariConnectionPool.getConnection();
        System.out.println("Hikari connection has been setup");
    }

    @Override
    public String login(String name) throws SQLException {

        String plaintext;
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT UserPasswd FROM user WHERE UserName = ?;")) {
            stmt.setString(1, name);
            System.out.println(stmt);
            try (ResultSet rs = stmt.executeQuery()) {
                //For this method should be only one item, it will be in a table
                rs.next();
                plaintext = rs.getString(1);
            }

        }

        return plaintext;
    }

    @Override
    public boolean register(User user) throws SQLException {
        //add user add password to user table
        System.out.println("Trying to register user 1");
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO user(UserName, UserPasswd, profilePath) VALUES (?,?,?);")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, "?");
            stmt.executeUpdate();
            System.out.println("Trying to register user 2");
        }
        // add user and info to information table
        System.out.println("Trying to register user info 1");
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO userinformation(UserId, NickName, RealName, Country, PublicInfo) VALUES (?,?,?,?,?);")) {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getRealName());
            stmt.setDate(4, (java.sql.Date)user.getBirthday());
            stmt.setString(4, user.getCountry());
            stmt.setString(5, user.getInfomation());


            stmt.executeUpdate();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User getUserInfo(int userID) throws SQLException {
        User thisUser;
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM userinformation WHERE UserId =? ")) {
            stmt.setInt(1, userID);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                thisUser = new User();
                //TODO using column indexes, but maybe better to change to column names?
                thisUser.setId(rs.getInt(2));
                thisUser.setName(rs.getString(3));
                thisUser.setRealName(rs.getString(4));
                thisUser.setBirthday(rs.getDate(5));
                thisUser.setCountry(rs.getString(6));
            }

        }
        return thisUser;
    }
}

