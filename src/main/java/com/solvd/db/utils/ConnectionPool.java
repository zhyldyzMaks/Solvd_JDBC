package com.solvd.db.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static List<Connection> connectionPool;
    private static final int maxConnections = 10;


    public ConnectionPool() {
        connectionPool = new ArrayList<>();
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        try {
            for (int i = 0; i < maxConnections; i++) {
                Connection connection = createConnection();
                connectionPool.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }

    public synchronized Connection getConnection() {
        while (connectionPool.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connectionPool.remove(connectionPool.size() - 1);
    }

    public synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
        notify();
    }
}
