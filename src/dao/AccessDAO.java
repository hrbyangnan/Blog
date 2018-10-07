package dao;

import entity.ArticlePOJO;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessDAO implements AutoCloseable{

    private final Connection conn;

    //Use Hikari connection pool to access database
    public AccessDAO() throws SQLException {
        System.out.println("trying to setup Hikari connection");
        this.conn = HikariConnectionPool.getConnection();
        System.out.println("Hikari connection has been setup");
    }


    //This method takes a username and "password" and adds it to the database
    public void setNewUser(User thisUser) throws SQLException {
        //add user add password to user table
        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO user(UserName, UserPasswd, profilePath) VALUES (?,?,?);")) {
            stmt.setString(1, thisUser.getName());
            stmt.setString(2, thisUser.getPassword());
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

    //This method gets a password for the login
    public String getLoginFromDataBase(String name) throws SQLException {

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

    //This method gets user information from the database and returns it as a User POJO

    public User getUserInfoFromDataBase(int UserID) throws SQLException {
        User thisUser;
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM userinformation WHERE UserId =? ")){
            stmt.setInt(1,UserID);

            try (ResultSet rs = stmt.executeQuery()){
                rs.next();
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


    //This method gets a single article from the database based on the articleID and returns it as a POJO
public ArticlePOJO getOneArticleFromDataBase(int articleID) throws SQLException {
        ArticlePOJO thisArticle;
        try(PreparedStatement stmt=this.conn.prepareStatement("SELECT * FROM aricle WHERE ArticleId = ?")){
            stmt.setInt(1,articleID);

            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                thisArticle = new ArticlePOJO();
                thisArticle.setArticleID(rs.getInt(1));
                thisArticle.setAuthorID(rs.getInt(2));
                thisArticle.setTitle(rs.getString(3));
                thisArticle.setArticleContent(rs.getString(4));
                thisArticle.setPublicationTime(rs.getTimestamp(5));
                thisArticle.setPicPath(rs.getString(6));
            }
        }
        return thisArticle;


}

//This method gets all articles from the database and returns them as an Array List of Article POJOs
public List<ArticlePOJO> getAllArticlesFromDataBase() throws SQLException{
        List<ArticlePOJO> allArticles = new ArrayList<>();
        try(PreparedStatement stmt=this.conn.prepareStatement("SELECT * FROM article;")){

            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    ArticlePOJO thisArticle = new ArticlePOJO();
                    thisArticle.setArticleID(rs.getInt(1));
                    thisArticle.setAuthorID(rs.getInt(2));
                    thisArticle.setTitle(rs.getString(3));
                    thisArticle.setArticleContent(rs.getString(4));
                    thisArticle.setPublicationTime(rs.getTimestamp(5));
                    thisArticle.setPicPath(rs.getString(6));
                    allArticles.add(thisArticle);
                }
            }
        }return  allArticles;

}

//This method should take an article in the form of a POJO and post it to the database
public void postArticleToDatabase(ArticlePOJO thisArticle) throws SQLException{
        try(PreparedStatement stmt=this.conn.prepareStatement("INSERT INTO article(UserId,ArticleTitle,ArticleContent,PubTime,picPath ) VALUES(?,?,?,?,?);")){
            stmt.setInt(1,thisArticle.getAuthorID());
            stmt.setString(2,thisArticle.getTitle());
            stmt.setString(3,thisArticle.getArticleContent());
            stmt.setTimestamp(4,thisArticle.getPublicationTime());
            stmt.setString(5,thisArticle.getPicPath());
        }
}


@Override
    public void close() throws Exception {

    }






}
