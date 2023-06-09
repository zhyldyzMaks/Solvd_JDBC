package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.ClassTable;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements IDAO<ClassTable> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @Override
    public boolean create(ClassTable classTable) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into classes  (room_number, schedule, course_id) " +
                    "values(?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, classTable.getRoomNumber());
            preparedStatement.setString(2, classTable.getSchedule());
            preparedStatement.setLong(3, classTable.getCourseId());

            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Class created with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to create class.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public ClassTable getById(long id) throws SQLException {
        ClassTable classTable = new ClassTable();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from classes where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                classTable.setId(resultSet.getLong("id"));
                classTable.setRoomNumber(resultSet.getString("room_number"));
                classTable.setSchedule(resultSet.getString("schedule"));
                classTable.setCourseId(resultSet.getLong("course_id"));
            }
        }
        return classTable;
    }
    @Override
    public List<ClassTable> getAll() {
        List<ClassTable> allClasses = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from classes");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String roomNumber = resultSet.getString("room_number");
                String schedule = resultSet.getString("schedule");
                int courseId = resultSet.getInt("course_id");

                ClassTable classTable = new ClassTable(id, roomNumber, schedule, courseId);
                allClasses.add(classTable);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allClasses;
    }

    @Override
    public boolean update(ClassTable classTable) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update classes set room_number = ?, schedule = ?, course_id = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, classTable.getRoomNumber());
            preparedStatement.setString(2, classTable.getSchedule());
            preparedStatement.setLong(3, classTable.getCourseId());
            preparedStatement.setLong(4,classTable.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from classes where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
