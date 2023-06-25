package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Assignment;
import com.solvd.db.mysql.model.ClassTable;
import com.solvd.db.utils.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO extends AbstractDAO<Assignment> implements IDAO<Assignment> {
    private static final Logger logger = LogManager.getLogger(StudentDAO.class);
    public static final String insertQuery = "insert into assignments(name, due_date, score, class_id) " +
            "values(?,?,?,?)";
    public static final String updateQuery = "update assignments set name = ?, due_date = ?, score = ?, class_id = ? where id = ?";

    @Override
    public boolean create(Assignment assignment) {
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, assignment.getName());
            preparedStatement.setDate(2, assignment.getDueDate());
            preparedStatement.setInt(3, assignment.getScore());
            preparedStatement.setLong(4, assignment.getClassId().getId());

            if (preparedStatement.executeUpdate()>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    logger.info("Assignment created with ID: " + generatedId);
                }
            } else {
                logger.warn("Failed to create assignment.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while creating assignment.", e);
        } return false;
    }

    @Override
    public Assignment getById(long id) {
        Assignment assignment = new Assignment();
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from assignment where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                assignment.setId(resultSet.getLong("id"));
                assignment.setName(resultSet.getString("name"));
                assignment.setDueDate(resultSet.getDate("due_date"));
                assignment.setScore(resultSet.getInt("score"));
                long classId = resultSet.getLong("class_id");
                ClassDAO classDAO = new ClassDAO();
                ClassTable classTable = classDAO.getById(classId);
                assignment.setClassId(classTable);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return assignment;
    }
    @Override
    public List<Assignment> getAll() {
        List<Assignment> assignments = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from assignments");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date dueDate = resultSet.getDate("due_date");
                int score = resultSet.getInt("score");
                int classId = resultSet.getInt("class_id");
                ClassDAO classDAO = new ClassDAO();
                ClassTable classTable = classDAO.getById(classId);
                Assignment assignment = new Assignment(id, name, dueDate, score, classTable);
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
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setLong(1,assignment.getId());
                preparedStatement.setString(2, assignment.getName());
                preparedStatement.setDate(3, assignment.getDueDate());
                preparedStatement.setInt(4, assignment.getScore());
                preparedStatement.setLong(5, assignment.getClassId().getId());

                int updatedRows = preparedStatement.executeUpdate();

                return updatedRows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }return false;
    }

    @Override
    public boolean delete(long id) {
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from assignments where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
