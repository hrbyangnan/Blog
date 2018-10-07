package dao;

import java.sql.SQLException;

//To test if AccessDAO works at all
public class PRACTICE_TestDAO {

    public void start(){
        try{
            AccessDAO dao = new AccessDAO();
            System.out.println("Try to access database");
            dao.setNewUser("Harry456","secretPassword");
            System.out.println("Database was accessed");
        }catch(SQLException e){e.getMessage();}

    }

    public static void main(String[] args) {
        PRACTICE_TestDAO p = new PRACTICE_TestDAO();
        p.start();
    }
}