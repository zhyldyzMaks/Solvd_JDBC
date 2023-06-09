package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.ExamGrade;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamGradeDAO implements IDAO<ExamGrade> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @Override
    public boolean create(ExamGrade examGrade) throws SQLException {
        try ( Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into exam_grades (grade, exam_id) " +
                    "values(?,?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, examGrade.getGrade());
            preparedStatement.setLong(2, examGrade.getExam());

            if (preparedStatement.executeUpdate()>0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Exam grade created with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to create exam grade.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public ExamGrade getById(long id) throws SQLException {
        ExamGrade examGrade = new ExamGrade();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from exam_grades where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                examGrade.setId(resultSet.getLong("id"));
                examGrade.setGrade(resultSet.getString("grade"));
                examGrade.setId(resultSet.getInt("exam_id"));
            }
        }
        return examGrade;
    }

    @Override
    public List<ExamGrade> getAll() throws SQLException {
        List<ExamGrade> allExamGrades = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from exam_grades");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String grade = resultSet.getString("grade");
                int examId = resultSet.getInt("exam_id");

                ExamGrade examGrade = new ExamGrade(id, grade, examId);
                allExamGrades.add(examGrade);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allExamGrades;
    }

    @Override
    public boolean update(ExamGrade examGrade) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update exam_grades set grade = ?, exam_id = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, examGrade.getGrade());
            preparedStatement.setLong(2, examGrade.getExam());
            preparedStatement.setLong(3, examGrade.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from exam_grades where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
