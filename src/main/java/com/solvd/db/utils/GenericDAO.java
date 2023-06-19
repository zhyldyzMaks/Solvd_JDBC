package com.solvd.db.utils;

import com.solvd.db.mysql.model.ClassTable;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T>{
    boolean create(T t);
    T getById(long id);
    boolean update(T t);
    boolean delete(long id);
}
