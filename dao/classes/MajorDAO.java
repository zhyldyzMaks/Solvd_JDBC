package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Department;
import com.solvd.db.mysql.model.Major;
import com.solvd.db.utils.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MajorDAO extends AbstractDAO<Major> implements IDAO<Major> {
    private static final Logger logger = LogManager.getLogger(TranscriptDAO.class);
    public static final String insertQuery = "insert into majors  (name, description, department_id) values(?,?,?)";
    public static final String updateQuery = "update majors set name = ?, description = ?, dep_id = ? where id = ?";

    @Override
    public boolean create(Major major) {
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, major.getName());
            statement.setString(2, major.getDescription());
            statement.setLong(3,major.getDepartmentId().getId());

            if (statement.executeUpdate()>0){
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    logger.info("Major created with ID: " + generatedId);
                }
            } else {
                logger.warn("Failed to create major.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while creating major.", e);
        } return false;
    }
    @Override
    public Major getById(long id){
        Major major = new Major();
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from majors where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                major.setId(resultSet.getLong("id"));
                major.setName(resultSet.getString("name"));
                major.setDescription(resultSet.getString("description"));
                long departmentId = resultSet.getLong("dep_id");
                DepartmentDAO departmentDAO = new DepartmentDAO();
                Department department = departmentDAO.getById(departmentId);
                major.setDepartment(department);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving major.", e);
        }
        return major;
    }
    @Override
    public boolean delete(long id){
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement statement = connection.prepareStatement("delete from majors where id = ?");
            statement.setLong(1,id);
            int deletedRows = statement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting major.", e);
        }
        return false;
    }
    @Override
    public List<Major> getAll(){
        System.out.println("List of all majors in the University");
        List<Major> allMajors = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement statement = connection.prepareStatement("select * from majors");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int deptId = resultSet.getInt("dep_id");
                DepartmentDAO departmentDAO = new DepartmentDAO();
                Department department = departmentDAO.getById(deptId);

                Major major = new Major(id, name, description,department);
                allMajors.add(major);
            }
        }catch (SQLException e){
            logger.error("Error while retrieving all majors.", e);
        }
        return allMajors;
    }

    @Override
    public boolean update(Major major) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, major.getName());
            statement.setString(2, major.getDescription());
            statement.setLong(3, major.getDepartmentId().getId());
            statement.setLong(4, major.getId());

            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating major.", e);
        }return false;
    }
}
