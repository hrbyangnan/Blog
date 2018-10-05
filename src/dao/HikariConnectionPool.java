package dao;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariConnectionPool {
    private static HikariDataSource hds;

    static {

        Properties dbProps = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("class loader is: " + classLoader.toString());
        // Loads a resource from the classpath. Can find files on:
        // /WEB-INF/lib, or
        // /WEB-INF/classes
        try (InputStream input = classLoader.getResourceAsStream("connection.properties")) {
            //testing hikari is loading the connection properties file
            System.out.println("Hikari has found " + input );
            dbProps.load(input);

        } catch (IOException e) {
            System.out.println("There is an error somewhere");
            e.printStackTrace();

        }

        hds = new HikariDataSource();
        hds.setJdbcUrl(dbProps.getProperty("url"));
        hds.setDriverClassName("com.mysql.jdbc.Driver");
        hds.setUsername(dbProps.getProperty("user"));
        hds.setPassword(dbProps.getProperty("password"));
        hds.setMaximumPoolSize(4);
    }

    public static Connection getConnection() throws SQLException {
        return hds.getConnection();
    }
}