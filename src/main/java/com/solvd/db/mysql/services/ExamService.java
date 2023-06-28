package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.mapper.ExamMapper;
import com.solvd.db.mysql.model.Exam;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class ExamService implements GenericDAO<Exam>, GetAllInterface<Exam> {
    private final ExamMapper examMapper;

    public ExamService(SqlSessionFactory sqlSessionFactory){
        examMapper = sqlSessionFactory.openSession().getMapper(ExamMapper.class);
    }

    @Override
    public List<Exam> getAll() {
        return examMapper.getAllExams();
    }

    @Override
    public boolean create(Exam exam) {
        return examMapper.createExam(exam);
    }

    @Override
    public Exam getById(long id) {
        return examMapper.getExamById(id);
    }

    @Override
    public boolean update(Exam exam) {
        return examMapper.updateExam(exam);
    }

    @Override
    public boolean delete(long id) {
        return examMapper.deleteExam(id);
    }
}
