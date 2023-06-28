package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.mapper.ExamGradeMapper;
import com.solvd.db.mysql.model.ExamGrade;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class ExamGradeService implements GenericDAO<ExamGrade>, GetAllInterface<ExamGrade> {
    private final ExamGradeMapper examGradeMapper;

    public ExamGradeService(SqlSessionFactory sqlSessionFactory){
        examGradeMapper = sqlSessionFactory.openSession().getMapper(ExamGradeMapper.class);
    }

    @Override
    public List<ExamGrade> getAll() {
        return examGradeMapper.getAllExamGrades();
    }

    @Override
    public boolean create(ExamGrade examGrade) {
        return examGradeMapper.createExamGrade(examGrade);
    }

    @Override
    public ExamGrade getById(long id) {
        return examGradeMapper.getExamGradeById(id);
    }

    @Override
    public boolean update(ExamGrade examGrade) {
        return examGradeMapper.updateExamGrade(examGrade);
    }

    @Override
    public boolean delete(long id) {
        return examGradeMapper.deleteExamGrade(id);
    }
}
