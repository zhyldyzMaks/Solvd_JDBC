package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.MajorDAO;
import com.solvd.db.mysql.model.Major;
import com.solvd.db.utils.GenericDAO;
import java.util.List;

public class MajorService implements GenericDAO<Major>, GetAllInterface<Major> {
    private MajorDAO majorDAO;

    public MajorService(){
        majorDAO = new MajorDAO();
    }

    @Override
    public List<Major> getAll() {
        return majorDAO.getAll();
    }

    @Override
    public boolean create(Major major) {
        return majorDAO.create(major);
    }

    @Override
    public Major getById(long id) {
        return majorDAO.getById(id);
    }

    @Override
    public boolean update(Major major) {
        return majorDAO.update(major);
    }

    @Override
    public boolean delete(long id) {
        return majorDAO.delete(id);
    }
}
