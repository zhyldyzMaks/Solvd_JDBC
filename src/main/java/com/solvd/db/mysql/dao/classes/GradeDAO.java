package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Grade;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO implements IDAO<Grade> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @Override
    public boolean create(Grade grade){
        try ( Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into grades  (score, assignment_id) " +
                    "values(?,?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, grade.getScore());
            preparedStatement.setLong(2, grade.getAssignment());

            if (preparedStatement.executeUpdate()>0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Grade created with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to create grade.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public Grade getById(long id) throws SQLException {
        Grade grade = new Grade();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from grades where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                grade.setId(resultSet.getLong("id"));
                grade.setScore(resultSet.getInt("score"));
                grade.setAssignment(resultSet.getInt("assignment_id"));
            }
        }
        return grade;
    }
    @Override
    public List<Grade> getAll() {
        List<Grade> gradesList = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from grades");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int score = resultSet.getInt("score");
                int assId = resultSet.getInt("assignment_id");

                Grade grade = new Grade(id,score,assId);
                gradesList.add(grade);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gradesList;
    }

    @Override
    public boolean update(Grade grade) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update grades set score = ?, assignment_id = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, grade.getScore());
            preparedStatement.setLong(2, grade.getAssignment());
            preparedStatement.setLong(3, grade.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from grades where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
