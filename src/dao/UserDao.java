package dao;

import pojo.User;

import java.sql.SQLException;

public interface UserDao {
    public String login(String name) throws SQLException;

    public boolean register(User user) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public User getUserInfo(int userID) throws SQLException;
}