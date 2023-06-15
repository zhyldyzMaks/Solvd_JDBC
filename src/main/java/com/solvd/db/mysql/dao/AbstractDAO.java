package com.solvd.db.mysql.dao;

import java.util.List;

public abstract class AbstractDAO<T> {
    protected abstract List<T> getAll();
}
