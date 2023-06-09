package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Enrollment;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO implements IDAO<Enrollment> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public boolean create(Enrollment enrollment) {
        try ( Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into enrollments (student_id, course_id, enrollment_date) " +
                    "values(?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, enrollment.getEnrollmentDate());
            preparedStatement.setLong(2, enrollment.getStudentId());
            preparedStatement.setLong(3, enrollment.getCourseId());

            if (preparedStatement.executeUpdate()>0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Enrollment created with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to create enrollment.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public Enrollment getById(long id) throws SQLException {
        Enrollment enrollment = new Enrollment();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from enrollments where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                enrollment.setId(resultSet.getLong("id"));
                enrollment.setEnrollmentDate(resultSet.getDate("date"));
                enrollment.setStudentId(resultSet.getLong("student_id"));
                enrollment.setCourseId(resultSet.getLong("course_id"));
            }
        }
        return enrollment;
    }
    @Override
    public List<Enrollment> getAll() {
        List<Enrollment> allEnrollments= new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from enrollments");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");

                Enrollment enrollment = new Enrollment(id, date, studentId,courseId);
                allEnrollments.add(enrollment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allEnrollments;
    }

    @Override
    public boolean update(Enrollment enrollment) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update enrollments set date = ?, student_id = ?, course_id = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDate(1, enrollment.getEnrollmentDate());
            preparedStatement.setLong(2, enrollment.getStudentId());
            preparedStatement.setLong(3, enrollment.getCourseId());
            preparedStatement.setLong(4, enrollment.getId());

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from enrollments where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
