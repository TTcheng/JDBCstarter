package com.wcc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工厂
 * Singleton
 */
public class ConnectionFactory {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    private Connection connection;

    static {
        InputStream is = null;// = ConnectionFactory.class.getClassLoader().getResourceAsStream("src/main/db.properties");
        Properties properties = new Properties();
        try {
            is = new FileInputStream(new File("src/main/db.properties"));
            properties.load(is);
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection makeConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static ConnectionFactory ourInstance = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return ourInstance;
    }

    private ConnectionFactory() {
    }
}
