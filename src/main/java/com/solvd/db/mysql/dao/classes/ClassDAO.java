package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.utils.GenericDAO;
import com.solvd.db.mysql.model.ClassTable;
import com.solvd.db.mysql.model.Course;
import com.solvd.db.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO extends AbstractDAO<ClassTable> implements GetAllInterface<ClassTable> {
    private static final Logger logger = LogManager.getLogger(ClassDAO.class);
    private static final String insertQuery = "insert into classes  (room_number, schedule, course_id) values(?,?,?)";
    private static final String updateQuery = "update classes set room_number = ?, schedule = ?, course_id = ? where id = ?";
    private static final String selectQuery = "select * from classes where id = ?";
    private static final String deleteQuery = "delete from classes where id = ?";

    @Override
    public boolean create(ClassTable classTable) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, classTable.getRoomNumber());
            preparedStatement.setString(2, classTable.getSchedule());
            preparedStatement.setLong(3, classTable.getCourseId().getId());
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    logger.info("Class created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create class.");
            }
        } catch (SQLException e) {
            logger.error("Error while creating class.", e);
        }
        return false;
    }

    @Override
    public ClassTable getById(long id) {
        ClassTable classTable = new ClassTable();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                classTable.setId(resultSet.getLong("id"));
                classTable.setRoomNumber(resultSet.getString("room_number"));
                classTable.setSchedule(resultSet.getString("schedule"));
                long courseId = resultSet.getLong("course_id");
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);
                classTable.setCourseId(course);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving class.", e);
        }
        return classTable;
    }

    @Override
    public List<ClassTable> getAll() {
        List<ClassTable> allClasses = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from classes")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String roomNumber = resultSet.getString("room_number");
                String schedule = resultSet.getString("schedule");
                int courseId = resultSet.getInt("course_id");
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);
                ClassTable classTable = new ClassTable(id, roomNumber, schedule, course);
                allClasses.add(classTable);
            }
        }catch (SQLException e){
            logger.error("Error while retrieving all classes.", e);
        }
        return allClasses;
    }

    @Override
    public boolean update(ClassTable classTable) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, classTable.getRoomNumber());
            preparedStatement.setString(2, classTable.getSchedule());
            preparedStatement.setLong(3, classTable.getCourseId().getId());
            preparedStatement.setLong(4,classTable.getId());
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating class.", e);
        }return false;
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteQuery)){
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting class.", e);
        }
        return false;
    }
}