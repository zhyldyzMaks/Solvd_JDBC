package com.solvd.db.utils;

import com.solvd.db.mysql.dao.classes.AssignmentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static List<Connection> connectionPool;
    private static final int maxConnections = 5;

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
            logger.error("Error while initializing connection pool.", e);
        }
    }

    private Connection createConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            logger.error("Error while loading db.properties file", e);
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
                logger.error("Error while waiting to a connection", e);
            }
        }
        return connectionPool.remove(connectionPool.size() - 1);
    }

    public synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
        notify();
    }
}
