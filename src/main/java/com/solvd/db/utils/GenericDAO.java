package com.solvd.db.utils;

public interface GenericDAO<T>{

    boolean create(T t);
    T getById(long id);
    boolean update(T t);
    boolean delete(long id);
}
