package dao;

import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessDAO {

    private final Connection conn;

    //Use Hikari connection pool to access database
    public AccessDAO() throws SQLException {
        this.conn = HikariConnectionPool.getConnection();
    }


    //Takes a username and "password" and adds it to the database
    public void setNewUser(String name, String password) throws SQLException {
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO user(UserName, UserPasswd) VALUES (?,?)")) {
            stmt.setString(1, name);
            stmt.setString(2, password);

            stmt.executeUpdate();
        }
    }

    //get password for matching login
    public String getLoginFromDataBase(String name) throws SQLException {
        String plaintext;
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT UserPasswd FROM user WHERE UserName = ?")) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                //For this method should be only one item, it will be in a table
                plaintext = rs.getString(1);
            }

        }
        return plaintext;
    }

    //Gets user information from the database and returns a User POJO

    public User getUserInfoFromDataBase(int UserID) throws SQLException {
        User thisUser;
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM userinformation WHERE UserId =? ")){
            stmt.setInt(1,UserID);

            try (ResultSet rs = stmt.executeQuery()){
                thisUser = new User();
                //TODO using column indexes, but maybe better to change to column names?
                thisUser.setId(rs.getInt(2));
                thisUser.setName(rs.getString(3));
                thisUser.setRealName(rs.getString(4));
                thisUser.setBirthday(rs.getInt(5));
                thisUser.setCountry(rs.getString(6));
            }

        }
        return thisUser;
    }

    public void postUserInfoToDatabase() throws SQLException {
    }

    /*public Article getOneArticleFromDataBase() throws SQLException {
    }

    public List<Article> getAllArticlesFromDataBase() throws SQLException{}
    */

}
