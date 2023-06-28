package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.model.Assignment;
import com.solvd.db.mysql.model.ClassTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO extends AbstractDAO<Assignment> implements GetAllInterface<Assignment> {
    private static final Logger logger = LogManager.getLogger(AssignmentDAO.class);
    private static final String insertQuery = "insert into assignments(name, due_date, score, class_id) " +
            "values(?,?,?,?)";
    private static final String updateQuery = "update assignments set name = ?, due_date = ?, score = ?, class_id = ? where id = ?";
    private static final String selectQuery = "select * from assignments where id = ?";
    private static final String deleteQuery = "delete from assignments where id = ?";

    @Override
    public boolean create(Assignment assignment) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, assignment.getName());
            preparedStatement.setDate(2, assignment.getDueDate());
            preparedStatement.setInt(3, assignment.getScore());
            preparedStatement.setLong(4, assignment.getClassId().getId());
            if (preparedStatement.executeUpdate()>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    logger.info("Assignment created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create assignment.");
            }
        } catch (SQLException e) {
            logger.error("Error while creating assignment.", e);
        } return false;
    }

    @Override
    public Assignment getById(long id) {
        Assignment assignment = new Assignment();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                assignment = mapResultSetToAssignment(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving assignment.", e);
        }
        return assignment;
    }

    public List<Assignment> getAll() {
        List<Assignment> assignments = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from assignments")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Assignment assignment = mapResultSetToAssignment(resultSet);
                assignments.add(assignment);
            }
        } catch (SQLException e){
            logger.error("Error while retrieving all assignments.", e);
        }
        return assignments;
    }

    @Override
    public boolean update(Assignment assignment) {
        try ( PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, assignment.getName());
            preparedStatement.setDate(2, assignment.getDueDate());
            preparedStatement.setInt(3, assignment.getScore());
            preparedStatement.setLong(4, assignment.getClassId().getId());
            preparedStatement.setLong(5,assignment.getId());
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating assignment.", e);
        }return false;
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteQuery)){
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting assignment.", e);
        }
        return false;
    }

    private Assignment mapResultSetToAssignment(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Date dueDate = resultSet.getDate("due_date");
        int score = resultSet.getInt("score");
        long classId = resultSet.getLong("class_id");
        ClassDAO classDAO = new ClassDAO();
        ClassTable classTable = classDAO.getById(classId);
        return new Assignment(id, name, dueDate, score, classTable);
    }
}