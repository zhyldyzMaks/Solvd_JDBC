package com.solvd.db.mysql.dao;

public interface IDAO<T>{
    boolean create(T t);
    T getById(long id);
    boolean update(T t);
    boolean delete(long id);
}
