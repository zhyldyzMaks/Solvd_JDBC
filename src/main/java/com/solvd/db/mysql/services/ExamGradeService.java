package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.ExamGradeDAO;
import com.solvd.db.mysql.model.ExamGrade;
import com.solvd.db.utils.GenericDAO;
import java.util.List;

public class ExamGradeService implements GenericDAO<ExamGrade>, GetAllInterface<ExamGrade> {
    private ExamGradeDAO examGradeDAO;

    public ExamGradeService(){
        examGradeDAO = new ExamGradeDAO();
    }

    @Override
    public List<ExamGrade> getAll() {
        return examGradeDAO.getAll();
    }

    @Override
    public boolean create(ExamGrade examGrade) {
        return examGradeDAO.create(examGrade);
    }

    @Override
    public ExamGrade getById(long id) {
        return examGradeDAO.getById(id);
    }

    @Override
    public boolean update(ExamGrade examGrade) {
        return examGradeDAO.update(examGrade);
    }

    @Override
    public boolean delete(long id) {
        return examGradeDAO.delete(id);
    }
}
