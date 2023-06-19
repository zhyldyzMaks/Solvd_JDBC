package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.utils.GenericDAO;
import com.solvd.db.mysql.model.Course;
import com.solvd.db.mysql.model.Enrollment;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO extends AbstractDAO<Enrollment> implements GetAllInterface<Enrollment> {
    private static final Logger logger = LogManager.getLogger(EnrollmentDAO.class);

    private static final String insertQuery = "insert into enrollments (student_id, course_id, enrollment_date) " +
            "values(?,?,?)";
    private static final String updateQuery = "update enrollments set date = ?, student_id = ?, course_id = ? where id = ?";
    private static final String selectQuery = "select * from enrollments where id = ?";
    private static final String deleteQuery = "delete from enrollments where id = ?";

    public boolean create(Enrollment enrollment) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, enrollment.getEnrollmentDate());
            preparedStatement.setLong(2, enrollment.getStudentId().getId());
            preparedStatement.setLong(3, enrollment.getCourseId().getId());
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    logger.info("Enrollment created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create enrollment.");
            }
        } catch (SQLException e) {
            logger.error("Error while creating enrollment.", e);
        }
        return false;
    }

    @Override
    public Enrollment getById(long id) {
        Enrollment enrollment = new Enrollment();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                enrollment.setId(resultSet.getLong("id"));
                enrollment.setEnrollmentDate(resultSet.getDate("date"));
                long studentId = resultSet.getLong("student_id");
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getById(studentId);
                enrollment.setStudentId(student);
                long courseId = resultSet.getLong("course_id");
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);
                enrollment.setCourseId(course);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving enrollment.", e);
        }
        return enrollment;
    }

    @Override
    public List<Enrollment> getAll() {
        List<Enrollment> allEnrollments = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from enrollments")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getById(studentId);
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);
                Enrollment enrollment = new Enrollment(id, date, student, course);
                allEnrollments.add(enrollment);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving all enrollments.", e);
        }
        return allEnrollments;
    }

    @Override
    public boolean update(Enrollment enrollment) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setDate(1, enrollment.getEnrollmentDate());
            preparedStatement.setLong(2, enrollment.getStudentId().getId());
            preparedStatement.setLong(3, enrollment.getCourseId().getId());
            preparedStatement.setLong(4, enrollment.getId());
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating enrollment.", e);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteQuery)) {
            preparedStatement.setLong(1, id);
            int deletedRows = preparedStatement.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting enrollment.", e);
        }
        return false;
    }

    public List<Enrollment> getEnrollmentsForStudent(Student student) throws SQLException {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "select * from enrollment where student_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setLong(1, student.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int enrollmentId = resultSet.getInt("id");
                Date enrollmentDate = resultSet.getDate("date");
                int courseId = resultSet.getInt("course_id");
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);
                Enrollment enrollment = new Enrollment(enrollmentId, enrollmentDate, student, course);
                enrollments.add(enrollment);
                logger.info("Retrieved enrollment: {}", enrollment);
            }
        }catch (SQLException e){
            logger.error("Error while retrieving enrollments for student: {}", student, e);
            throw e;
        }
        return enrollments;
    }
}