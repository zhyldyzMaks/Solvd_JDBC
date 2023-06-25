package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.ExamDAO;
import com.solvd.db.mysql.model.Exam;
import com.solvd.db.utils.GenericDAO;
import java.util.List;

public class ExamService implements GenericDAO<Exam>, GetAllInterface<Exam> {
    private ExamDAO examDAO;

    public ExamService(){
        examDAO = new ExamDAO();
    }

    @Override
    public List<Exam> getAll() {
        return examDAO.getAll();
    }

    @Override
    public boolean create(Exam exam) {
        return examDAO.create(exam);
    }

    @Override
    public Exam getById(long id) {
        return examDAO.getById(id);
    }

    @Override
    public boolean update(Exam exam) {
        return examDAO.update(exam);
    }

    @Override
    public boolean delete(long id) {
        return examDAO.delete(id);
    }
}
