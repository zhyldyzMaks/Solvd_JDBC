package com.solvd.db.mysql.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO <T>{
    boolean create(T t) throws SQLException;
    T getById(long id) throws SQLException;
    List<T> getAll() throws SQLException;
    boolean update(T t);
    boolean delete(long id) throws SQLException;

}
