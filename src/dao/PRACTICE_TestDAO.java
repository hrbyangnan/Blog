package dao;

import java.sql.SQLException;

//To test if AccessDAO works at all
public class PRACTICE_TestDAO {

    public void start(){
        try{
            AccessDAO dao = new AccessDAO();
            System.out.println(dao.getLoginFromDataBase("Bob"));

        }catch(SQLException e){e.getMessage();}

    }

    public static void main(String[] args) {
        PRACTICE_TestDAO p = new PRACTICE_TestDAO();
        p.start();
    }
}