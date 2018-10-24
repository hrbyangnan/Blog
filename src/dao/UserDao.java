package dao;

import pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    String login(String name) throws SQLException;

    boolean register(User user) throws SQLException;

    void delete(int id) throws SQLException;
    boolean updateProfile(User user) throws SQLException;

    void deleteUser(int userId)throws SQLException;
    User getUserInfo(String name) throws SQLException;

    User findUserByName(String userName)throws SQLException ;

    boolean changePassword(User user) throws SQLException;


    List<User> getAllUser()throws SQLException ;
}