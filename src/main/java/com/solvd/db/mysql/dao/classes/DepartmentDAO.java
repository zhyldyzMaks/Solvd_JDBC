package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Department;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements IDAO<Department> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public boolean create(Department dept) throws SQLException {
        try ( Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into departments (name) " +
                    "values(?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dept.getName());

            if (preparedStatement.executeUpdate()>0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Department created with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to create department.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public Department getById(long id) throws SQLException {
        Department dept = new Department();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from departments where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                dept.setId(resultSet.getLong("id"));
                dept.setName(resultSet.getString("name"));
            }
        }
        return dept;
    }

    @Override
    public List<Department> getAll() throws SQLException {
        List<Department> allDepartments = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from departments");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Department dept = new Department(id, name);
               allDepartments.add(dept);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allDepartments;
    }

    @Override
    public boolean update(Department department) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update departments set name = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from departments where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
