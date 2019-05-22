package com.springx.bootdubbo.common.util;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {

    private JdbcUtil() {
    }

    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            File file = new File(Thread.currentThread().getContextClassLoader().getResource("dbConfig.properties").getPath());
            Properties properties = new Properties();
            properties.load(new FileReader(file));

            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("数据库连接失败！");
            se.printStackTrace();
        }
        return null;
    }


    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }


    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }


    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public static void closeQuietly(Connection conn) {
        try {
            close(conn);
        } catch (SQLException e) { // NOPMD
            // quiet
        }
    }

    public static void closeQuietly(Connection conn, Statement stmt,
                                    ResultSet rs) {

        try {
            closeQuietly(rs);
        } finally {
            try {
                closeQuietly(stmt);
            } finally {
                closeQuietly(conn);
            }
        }

    }


    public static void closeQuietly(ResultSet rs) {
        try {
            close(rs);
        } catch (SQLException e) {
            // quiet
        }
    }

    public static void closeQuietly(Statement stmt) {
        try {
            close(stmt);
        } catch (SQLException e) {
            // quiet
        }
    }

    public static void commitAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            try {
                conn.commit();
            } finally {
                conn.close();
            }
        }
    }

    public static void commitAndCloseQuietly(Connection conn) {
        try {
            commitAndClose(conn);
        } catch (SQLException e) {
            // quiet
        }
    }

}