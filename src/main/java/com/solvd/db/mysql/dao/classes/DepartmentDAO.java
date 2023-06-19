package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.utils.GenericDAO;
import com.solvd.db.mysql.model.Department;
import com.solvd.db.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends AbstractDAO<Department> implements GetAllInterface<Department> {
    private static final Logger logger = LogManager.getLogger(DepartmentDAO.class);
    private static final String insertQuery = "insert into departments (name) values(?)";
    private static final String updateQuery = "update departments set name = ? where id = ?";
    private static final String readQuery = "select * from departments where id = ?";
    private static final String deleteQuery = "delete from departments where id = ?";

    @Override
    public boolean create(Department dept){
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, dept.getName());
            if (preparedStatement.executeUpdate()>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    logger.info("Department created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create department.");
            }
        } catch (SQLException e) {
            logger.error("Error while creating department.", e);
        } return false;
    }

    @Override
    public Department getById(long id) {
        Department dept = new Department();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(readQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                dept.setId(resultSet.getLong("id"));
                dept.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving department.", e);
        }
        return dept;
    }

    @Override
    public List<Department> getAll() {
        List<Department> allDepartments = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from departments")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Department dept = new Department(id, name);
                allDepartments.add(dept);
            }
        }catch (SQLException e){
            logger.error("Error while retrieving all departments.", e);
        }
        return allDepartments;
    }

    @Override
    public boolean update(Department department) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getId());
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating department.", e);
        }return false;
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteQuery)){
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting department.", e);
        }
        return false;
    }
}