package dao;

import entity.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
public class TestsForAccessDAO {

    private AccessDAO ourDAO;

    @Before
    public void setUp() {try {
        ourDAO = new AccessDAO();
    }catch (SQLException e){
        System.out.println("Creating the DAO threw an exception");

    }
    }

    /*@Test
    public void setLogin() throws SQLException{
        ourDAO.setNewUser("Jimbo","trees123");
    }*/

    @Test
    public void testGetLogin() throws SQLException{
        assertEquals("Unbreakable", ourDAO.getLoginFromDataBase("Bob"));
    }

    @Test
    public void testGetUserInfo() throws SQLException{
        User ourPOJO = ourDAO.getUserInfoFromDataBase(42);
        assertEquals("Betty", ourPOJO.getRealName());
        assertEquals("China", ourPOJO.getCountry());
    }

    @Test
    public void testGetAllArticles
}
