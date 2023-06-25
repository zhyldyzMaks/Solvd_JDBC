package com.solvd.db.mysql.dao;

import com.solvd.db.utils.ConnectionPool;
import com.solvd.db.utils.GenericDAO;
import java.sql.Connection;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    private ConnectionPool connectionPool;

    public AbstractDAO() {
        connectionPool = new ConnectionPool();
    }

    protected Connection getConnection() {
        return connectionPool.getConnection();
    }
}
