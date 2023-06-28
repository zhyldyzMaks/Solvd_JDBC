package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.Exam;
import java.util.List;

public interface ExamMapper {

    Exam getExamById(long id);

    List<Exam> getAllExams();

    boolean createExam(Exam exam);

    boolean updateExam(Exam exam);

    boolean deleteExam(long id);
}
