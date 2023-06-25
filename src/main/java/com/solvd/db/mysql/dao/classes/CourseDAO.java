package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.model.Course;
import com.solvd.db.mysql.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends AbstractDAO<Course> implements GetAllInterface<Course> {
    private static final Logger logger = LogManager.getLogger(CourseDAO.class);
    private static final String insertQuery = "insert into courses (id, name, admission_date) values (?,?,?)";
    private static final String updateQuery = "update courses set name = ?, department_id = ?, credits = ? where id = ?";
    private static final String selectQuery = "select * from courses where id = ?";
    private static final String deleteQuery = "delete from courses where id = ?";

    @Override
    public boolean create(Course course) {
        try (PreparedStatement statement = getConnection().prepareStatement(insertQuery)) {
            statement.setLong(1, course.getId());
            statement.setString(2, course.getName());
            statement.setLong(3, course.getDepartmentId().getId());
            statement.setInt(4, course.getCredits());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Course created successfully");
                return true;
            } else {
                logger.warn("Failed to create course.");
            }
        } catch (SQLException e) {
            logger.error("Error while creating course.", e);
        } return false;
    }

    @Override
    public Course getById(long id) {
        Course course = new Course();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                course = mapResultSetToCourse(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving course.", e);
        }
        return course;
    }

    @Override
    public List<Course> getAll(){
        List<Course> allCourses = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from courses")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Course course = mapResultSetToCourse(resultSet);
                allCourses.add(course);
            }
        }catch (SQLException e){
            logger.error("Error while retrieving all courses.", e);
        }
        return allCourses;
    }

    @Override
    public boolean update(Course course) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setLong(2, course.getDepartmentId().getId());
            preparedStatement.setInt(3, course.getCredits());
            preparedStatement.setLong(4, course.getId());
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating course.", e);
        }return false;
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteQuery)){
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting course.", e);
        }
        return false;
    }

    private Course mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        int credits = resultSet.getInt("credits");
        long departmentId = resultSet.getLong("department_id");
        DepartmentDAO departmentDAO = new DepartmentDAO();
        Department department = departmentDAO.getById(departmentId);
        return new Course(id, name, credits, department);
    }
}