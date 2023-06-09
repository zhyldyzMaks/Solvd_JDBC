package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Course;
import com.solvd.db.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements IDAO<Course> {
    ResultSet resultSet =  null;

    @Override
    public boolean create(Course course) throws SQLException {
        String insertQuery = "insert into courses (id, name, admission_date) values (?,?,?)";
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setLong(1, course.getId());
            statement.setString(2, course.getName());
            statement.setLong(3, course.getDepartmentId());
            statement.setInt(4, course.getCredits());
            statement.executeUpdate();
            if (resultSet.next()) {
                System.out.println("Course created successfully");
            } else {
                System.out.println("Failed to create course.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }
    @Override
    public Course getById(long id) throws SQLException {
        Course course = new Course();
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from courses where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                course.setId(resultSet.getLong("id"));
                course.setName(resultSet.getString("name"));
                course.setDepartmentId(resultSet.getLong("department_id"));
                course.setCredits(resultSet.getInt("credits"));
            }
        }
        return course;
    }

    @Override
    public List<Course> getAll() throws SQLException {
        List<Course> allCourses = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from courses");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int deptId = resultSet.getInt("department_id");
                int credit = resultSet.getInt("credits");

                Course course = new Course(id, name, deptId, credit);
                allCourses.add(course);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allCourses;
    }

    @Override
    public boolean update(Course course) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update courses set name = ?, department_id = ?, credits = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setLong(2, course.getDepartmentId());
            preparedStatement.setInt(3, course.getCredits());
            preparedStatement.setLong(4, course.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from courses where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
