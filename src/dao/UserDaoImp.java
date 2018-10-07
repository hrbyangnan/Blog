package dao;

import pojo.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImp implements UserDao {
    @Override
    public boolean login(String name, String UserPasswd) {
        return false;
    }

    @Override
    public boolean register(User user) throws SQLException {
        //add user add password to user table
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO user(UserName, UserPasswd, profilePath) VALUES (?,?,?);")) {
            stmt.setString(1, User.getName());
            stmt.setString(2, User.getPassword());
//TODO check about profile path...
            stmt.setString(3,"?");
            stmt.executeUpdate();
        }
        // add user and info to information table
        try(PreparedStatement stmt=this.conn.prepareStatement("INSERT INTO userinformation(NickName, RealName, Birthday, Country) VALUES (?,?,?,?);")){
            stmt.setString(1,thisUser.getName());
            stmt.setString(2,thisUser.getRealName());
            stmt.setInt(3, thisUser.getBirthday());
            stmt.setString(4,thisUser.getCountry());
            stmt.executeUpdate();
        }

    }
    @Override
    public boolean delete(int id) {
        return false;
    }
}
