package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.utils.GenericDAO;
import com.solvd.db.mysql.model.Exam;
import com.solvd.db.mysql.model.ExamGrade;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamGradeDAO extends AbstractDAO<ExamGrade> implements GetAllInterface<ExamGrade> {
    private static final Logger logger = LogManager.getLogger(ExamGradeDAO.class);
    private static final String insertQuery = "insert into exam_grades (grade, exam_id) values(?,?)";
    private static final String updateQuery = "update exam_grades set grade = ?, exam_id = ?, student_id = ? where id = ?";
    private static final String readQuery = "select * from exam_grades where id = ?";
    private static final String deleteQuery = "delete from exam_grades where id = ?";

    @Override
    public boolean create(ExamGrade examGrade){
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, examGrade.getGrade());
            preparedStatement.setLong(2, examGrade.getExamId().getId());
            preparedStatement.setLong(3, examGrade.getStudentId().getId());
            if (preparedStatement.executeUpdate()>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    logger.info("Exam grade created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create exam grade.");
            }
        } catch (SQLException e) {
            logger.error("Error while creating exam.", e);
        } return false;
    }

    @Override
    public ExamGrade getById(long id){
        ExamGrade examGrade = new ExamGrade();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(readQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                examGrade.setId(resultSet.getLong("id"));
                examGrade.setGrade(resultSet.getString("grade"));
                int examId = resultSet.getInt("exam_id");
                int studentId = resultSet.getInt("student_id");
                ExamDAO examDAO = new ExamDAO();
                Exam exam = examDAO.getById(examId);
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getById(studentId);
                examGrade.setExamId(exam);
                examGrade.setStudentId(student);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving exam.", e);
        }
        return examGrade;
    }

    @Override
    public List<ExamGrade> getAll(){
        List<ExamGrade> allExamGrades = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from exam_grades")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String grade = resultSet.getString("grade");
                int examId = resultSet.getInt("exam_id");
                int studentId = resultSet.getInt("student_id");
                ExamDAO examDAO = new ExamDAO();
                Exam exam = examDAO.getById(examId);
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getById(studentId);
                ExamGrade examGrade = new ExamGrade(id, grade, exam, student);
                allExamGrades.add(examGrade);
            }
        }catch (SQLException e){
            logger.error("Error while retrieving all exams.", e);
        }
        return allExamGrades;
    }

    @Override
    public boolean update(ExamGrade examGrade) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, examGrade.getGrade());
            preparedStatement.setLong(2, examGrade.getExamId().getId());
            preparedStatement.setLong(3, examGrade.getStudentId().getId());
            preparedStatement.setLong(4, examGrade.getId());
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating exam.", e);
        }return false;
    }

    @Override
    public boolean delete(long id){
        try ( PreparedStatement preparedStatement = getConnection().prepareStatement(deleteQuery)){
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting exam.", e);
        }
        return false;
    }
}