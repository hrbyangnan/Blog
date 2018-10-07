package servlet;

import dao.AccessDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class PRACTICEServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("Test Servlet testing");
        System.out.println("The textfield contained " + request.getParameter("testtext"));
        try {
            System.out.println("Trying to create a DAO");
            AccessDAO dao = new AccessDAO();
            System.out.println("Trying to use the DAO");
        System.out.println(dao.getLoginFromDataBase("Bob"));
            System.out.println("Did that work??");

    }catch(SQLException e){e.getMessage();}

    }


}
