package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.ClassDAO;
import com.solvd.db.mysql.model.ClassTable;
import com.solvd.db.utils.GenericDAO;

import java.util.List;

public class ClassService implements GenericDAO<ClassTable>, GetAllInterface<ClassTable> {
    private ClassDAO classDAO;

    public ClassService(){
        classDAO = new ClassDAO();
    }

    @Override
    public List<ClassTable> getAll() {
        return classDAO.getAll();
    }

    @Override
    public boolean create(ClassTable classTable) {
        return classDAO.create(classTable);
    }

    @Override
    public ClassTable getById(long id) {
        return classDAO.getById(id);
    }

    @Override
    public boolean update(ClassTable classTable) {
        return classDAO.update(classTable);
    }

    @Override
    public boolean delete(long id) {
        return classDAO.delete(id);
    }
}
