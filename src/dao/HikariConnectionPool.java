package dao;


import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariConnectionPool {
    private static HikariDataSource hds;

    static {

       /* Properties dbProps = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("class loader is: " + classLoader.toString());
        // Loads a resource from the classpath. Can find files on:
        // /WEB-INF/lib, or
        // /WEB-INF/classes
        try (InputStream input = classLoader.getResourceAsStream("c.properties")){
            //testing hikari is loading the connection properties file

            System.out.println("Hikari has found " + input );
            System.out.println(input);
            dbProps.load(input);

        } catch (IOException e) {
            System.out.println("There is an error somewhere");
            e.printStackTrace();

        }*/

        hds = new HikariDataSource();
        hds.setJdbcUrl("jdbc:mysql://db.sporadic.nz:3306/group2");
        hds.setDriverClassName("com.mysql.jdbc.Driver");
        hds.setUsername("group2");
        hds.setPassword("DivideAndConquer");
        hds.setMaximumPoolSize(50);
    }

    public static Connection getConnection() throws SQLException {
        return hds.getConnection();
    }
}