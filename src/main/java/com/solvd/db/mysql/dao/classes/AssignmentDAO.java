package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Assignment;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO implements IDAO<Assignment> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @Override
    public boolean create(Assignment assignment) {
        try ( Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into assignments(name, due_date, score, class_id) " +
                    "values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, assignment.getName());
            preparedStatement.setDate(2, assignment.getDueDate());
            preparedStatement.setInt(3, assignment.getScore());
            preparedStatement.setLong(4, assignment.getClassId());

            if (preparedStatement.executeUpdate()>0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Assignment created with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to create assignment.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public Assignment getById(long id) throws SQLException {
        Assignment assignment = new Assignment();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from assignment where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                assignment.setId(resultSet.getLong("id"));
                assignment.setName(resultSet.getString("name"));
                assignment.setDueDate(resultSet.getDate("due_date"));
                assignment.setScore(resultSet.getInt("score"));
                assignment.setClassId(resultSet.getLong("class_id"));
            }
        }
        return assignment;
    }
    @Override
    public List<Assignment> getAll() {
        List<Assignment> assignments = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from assignments");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date dueDate = resultSet.getDate("due_date");
                int score = resultSet.getInt("score");
                int classId = resultSet.getInt("class_id");

                Assignment assignment = new Assignment(id, name, dueDate, score, classId);
                assignments.add(assignment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return assignments;
    }

    @Override
    public boolean update(Assignment assignment) {
            try (Connection connection = ConnectionManager.getConnection()) {
                String updateQuery = "update assignments set name = ?, due_date = ?, score = ?, class_id = ? where id = ?";
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setLong(1,assignment.getId());
                preparedStatement.setString(2, assignment.getName());
                preparedStatement.setDate(3, assignment.getDueDate());
                preparedStatement.setInt(4, assignment.getScore());
                preparedStatement.setLong(5, assignment.getClassId());

                int updatedRows = preparedStatement.executeUpdate();

                return updatedRows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from assignments where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
