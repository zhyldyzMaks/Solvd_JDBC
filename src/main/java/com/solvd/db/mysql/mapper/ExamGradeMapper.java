package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.ExamGrade;
import java.util.List;

public interface ExamGradeMapper {

    ExamGrade getExamGradeById(long id);

    List<ExamGrade> getAllExamGrades();

    boolean createExamGrade(ExamGrade examGrade);

    boolean updateExamGrade(ExamGrade examGrade);

    boolean deleteExamGrade(long id);
}
