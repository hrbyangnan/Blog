package dao;

import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccessDAO {

    private final Connection conn;

    //Use Hikari connection pool to access database
    public AccessDAO() throws SQLException {
        this.conn = HikariConnectionPool.getConnection();
    }


    //Takes a username and "password" and adds it to the database
    public void setNewUser(String name, String passwd) throws SQLException {
        try(PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO user(UserName, UserPasswd) VALUES (?,?)")){
            stmt.setString(1,name);
            stmt.setString(2,passwd);
        }
    }

    public void getLoginFromDataBase() throws SQLException {
    }

    public User getUserInfoFromDataBase() throws SQLException {
    return null; }

    public void postUserInfoToDatabase() throws SQLException {
    }

    public Article getOneArticleFromDataBase() throws SQLException {
    }

    public List<Article> getAllArticlesFromDataBase() throws SQLException{}

}
