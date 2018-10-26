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

        Properties dbProps = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream input = classLoader.getResourceAsStream("c.properties")){

            dbProps.load(input);

        } catch (IOException e) {

            e.printStackTrace();

        }

        hds = new HikariDataSource();

        hds.setJdbcUrl(dbProps.getProperty("url"));
        hds.setDriverClassName("com.mysql.jdbc.Driver");
        hds.setUsername(dbProps.getProperty("user"));
        hds.setPassword(dbProps.getProperty("password"));
        hds.setMaximumPoolSize(10);
    }


    public static Connection getConnection() throws SQLException {
        return hds.getConnection();
    }
}