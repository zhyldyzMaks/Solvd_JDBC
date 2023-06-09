package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Major;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MajorDAO implements IDAO<Major> {
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    @Override
    public boolean create(Major major) {
        try ( Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into majors  (name, description, department_id) " +
                    "values(?,?,?)";
            statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, major.getName());
            statement.setString(2, major.getDescription());
            statement.setLong(3,major.getDepartmentId());

            if (statement.executeUpdate()>0){
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Major created with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to create major.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }
    @Override
    public Major getById(long id) throws SQLException {
        Major major = new Major();
        try ( Connection connection = ConnectionManager.getConnection()) {
            statement = connection.prepareStatement("select * from majors where id = ?");
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                major.setId(resultSet.getLong("id"));
                major.setName(resultSet.getString("name"));
                major.setDescription(resultSet.getString("description"));
                major.setDepartment(resultSet.getLong("dep_id"));
            }
        }
        return major;
    }
    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            statement = connection.prepareStatement("delete from majors where id = ?");
            statement.setLong(1,id);
            int deletedRows = statement.executeUpdate();
            return  deletedRows > 0;
        }
    }
    @Override
    public List<Major> getAll() throws SQLException {
        System.out.println("List of all majors in the University");
        List<Major> allMajors = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            statement = connection.prepareStatement("select * from majors");
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int deptId = resultSet.getInt("dep_id");

                Major major = new Major(id, name, description,deptId);
                allMajors.add(major);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allMajors;
    }

    @Override
    public boolean update(Major major) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update majors set name = ?, description = ?, dep_id = ? where id = ?";
            statement = connection.prepareStatement(updateQuery);
            statement.setString(1, major.getName());
            statement.setString(2, major.getDescription());
            statement.setLong(3, major.getDepartmentId());
            statement.setLong(4, major.getId());

            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

}
