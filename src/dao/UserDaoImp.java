package dao;

import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao, AutoCloseable {

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
        int idPlaceholder;
        System.out.println("Trying to register user 1");
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO user(UserName, UserPasswd, Email, ProfilePath) VALUES (?,?,?,?);")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getProfilePhoto());
            stmt.executeUpdate();
            System.out.println("Trying to register user 2");
        }
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT LAST_INSERT_ID()")) {
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                idPlaceholder = rs.getInt(1);
                System.out.println(idPlaceholder);
            }
        }
        // add user and info to information table
        System.out.println("Trying to register user info 1");
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO userinformation(UserId, NickName, RealName,Birthday, Country, PublicInfo) VALUES (?,?,?,?,?,?);")) {
            stmt.setInt(1, idPlaceholder);
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getRealName());
            stmt.setDate(4, (java.sql.Date) user.getBirthday());
            stmt.setString(5, user.getCountry());
            stmt.setString(6, user.getInfomation());


            stmt.executeUpdate();
        }
        return false;
    }

    @Override
    public void delete(int id) throws SQLException{
        try (PreparedStatement ps = this.conn.prepareStatement("delete from article where UserId=?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        try (PreparedStatement ps = this.conn.prepareStatement("delete from userinformation where UserId=?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        try (PreparedStatement ps = this.conn.prepareStatement("delete from user where UserId=?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    public User getUserInfo(String userName) throws SQLException {
        User thisUser;
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM user JOIN userinformation WHERE UserName=? AND NickName =?;")) {
            stmt.setString(1, userName);
            stmt.setString(2, userName);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                thisUser = new User();
                //TODO using column indexes, but maybe better to change to column names?
                thisUser.setId(rs.getInt(1));
                thisUser.setName(rs.getString(2));
                thisUser.setProfilePhoto(rs.getString(5));
                thisUser.setRealName(rs.getString(9));
                thisUser.setBirthday(rs.getDate(10));
                thisUser.setCountry(rs.getString(11));
                thisUser.setInfomation(rs.getString(12));
            }

        }

        return thisUser;
    }

    public List<User> getAllUserInfo() throws SQLException {
        List<User> listOfAllUsers;
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM user JOIN userinformation WHERE user.UserId=userinformation.UserId ;")) {

            try (ResultSet rs = stmt.executeQuery()) {
                listOfAllUsers = new ArrayList<>();
                while (rs.next()) {
                    User thisUser = new User();
                    //TODO using column indexes, but maybe better to change to column names?
                    thisUser.setId(rs.getInt(1));
                    thisUser.setName(rs.getString(2));
                    thisUser.setProfilePhoto(rs.getString(5));
                    thisUser.setRealName(rs.getString(9));
                    thisUser.setBirthday(rs.getDate(10));
                    thisUser.setCountry(rs.getString(11));
                    thisUser.setInfomation(rs.getString(12));
                    listOfAllUsers.add(thisUser);
                }

            }
        }
        return listOfAllUsers;
    }

    public boolean nameNotTaken(String userName) throws SQLException {
        try (PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM user WHERE UserName = ?")) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            //Ugly but use a counter, if there is something in the result set the counter should go up and return false i.e. name already taken
            int i = 0;
            while (rs.next()) {
                i = i + 1;
            }
            if (i > 0) {
                return false;
            }
            return true;
        }
    }

    @Override
    public User findUserByName(String userName) throws SQLException {
        User thisUser;
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM user  WHERE UserName=?;")) {

            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                thisUser = new User();
                thisUser.setName(rs.getString(2));
                thisUser.setEmail(rs.getString(4));
            }

        }
        return thisUser;
    }

    @Override
    public boolean changePassword(User user) throws SQLException {
        System.out.println("changePassword 1");
        String sql = "UPDATE user set UserPasswd=?  WHERE UserName=?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {

            System.out.println("changePassword 2");
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            System.out.println("changePassword 3");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }



    @Override
    public List<User> getAllUser() throws SQLException {
        List<User> userList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user;")) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setProfilePhoto(rs.getString(5));

                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return userList;
    }

    public void deleteUser(int userId) throws SQLException{
        System.out.println("this is the id passed: "+ userId);
        Integer i = userId;
        if (i == null) {
            return;
        }
        System.out.println("DeleteUserInformation dao method  "+ userId);

        String sql1 = "DELETE FROM article WHERE UserId=?";
        String sql2 = "DELETE FROM comment WHERE UserId=?";
        String sql3 = "DELETE FROM commentInComment WHERE UserId=?";
        String sql4 = "DELETE FROM userinformation WHERE UserId=?";
        String sql5 = "DELETE FROM user WHERE UserId=?";

        try (PreparedStatement ps = conn.prepareStatement(sql1)) {
            ps.setObject(1, userId);
            ps.executeUpdate();
            System.out.println("sql1");

        } catch (Exception e) {
        }
        try (PreparedStatement ps = conn.prepareStatement(sql2)) {
            ps.setObject(1, userId);
            ps.executeUpdate();
            System.out.println("sql2");
        } catch (Exception e) {

        }
        try (PreparedStatement ps = conn.prepareStatement(sql3)) {
            ps.setObject(1, userId);
            ps.executeUpdate();
            System.out.println("sql3");
        } catch (Exception e) {

        }
        try (PreparedStatement ps = conn.prepareStatement(sql4)) {
            ps.setObject(1, userId);
            ps.executeUpdate();
            System.out.println("sql4");
        } catch (Exception e) {

        }
        try (PreparedStatement ps = conn.prepareStatement(sql5)) {
            ps.setObject(1, userId);
            ps.executeUpdate();
            System.out.println("sql5");
        } catch (Exception e) {

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                System.out.println("inside DeleteUserInformation dao catch");
            }
        }
    }

    //    public void updateUser(User user) throws SQLException {
//
//        System.out.println("About to try prepared statement.");
//        try (PreparedStatement ps = conn.prepareStatement("UPDATE user SET UserName=?,UserPasswd=?,Email=?,ProfilePath=? WHERE UserId=?;")) {
//            ps.setString(1, user.getName());
//            ps.setString(2, user.getPassword());
//            ps.setString(3, user.getEmail());
//            ps.setString(4, user.getProfilePhoto());
//            ps.executeUpdate();
//            System.out.println("Trying to edit user ");
//            ps.executeUpdate();
//        } catch (Exception e) {
//
//        }
//    }
    //
    public boolean updateProfile(User user) throws SQLException {
        //add user add password to user table
//        int idPlaceholder;
        System.out.println("Trying to update user 1");
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE user SET UserName=?,ProfilePath=? WHERE UserId=?;")) {
//            try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO user(UserName, UserPasswd, Email, ProfilePath) VALUES (?,?,?,?);")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getProfilePhoto());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
            System.out.println("Trying to update user 2");
        }
//        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT LAST_INSERT_ID()")) {
//            try (ResultSet rs = stmt.executeQuery()) {
//                rs.next();
//                idPlaceholder = rs.getInt(1);
//                System.out.println(idPlaceholder);
//            }
//        }
        // add user and info to information table
        try(PreparedStatement stmt=conn.prepareStatement("UPDATE article SET RealName=? WHERE UserId=?;")){
            stmt.setString(1,user.getName());
            stmt.setInt(2,user.getId());
            stmt.executeUpdate();
            System.out.println("updated articles which user wrote");
        }

        System.out.println("Trying to update user 3");
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE userinformation SET NickName=?, RealName=?,Birthday=?, Country=?, PublicInfo=? WHERE UserId=?;")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getRealName());
            stmt.setDate(3, (java.sql.Date) user.getBirthday());
            stmt.setString(4, user.getCountry());
            stmt.setString(5, user.getInfomation());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
            System.out.println("Trying to update user 4");
        }
        return true;
    }


    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}

