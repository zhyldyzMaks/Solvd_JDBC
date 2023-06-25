package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.model.ContactInformation;
import com.solvd.db.mysql.model.Major;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.mysql.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends AbstractDAO<Student> implements GetAllInterface<Student> {
    private static final Logger logger = LogManager.getLogger(StudentDAO.class);
    private static final String createStudentQuery = "insert into students (id, name, admission_date, " +
            "user_id, major_id, contact_info_id) values (?,?,?,?,?,?)";
    private static final String updateStudentQuery = "update students set name = ?, admission_date = ?, user_id = ?," +
            " major_id = ?, contact_info_id = ? where id = ?";
    private static final String selectQuery = "select * from students where id = ?";
    private static final String selectAllQuery = "SELECT * FROM students";
    private static final String deleteQuery = "delete from students where id = ?";

    @Override
    public boolean create(Student student) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(createStudentQuery)) {
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setDate(3, student.getAdmissionDate());
            preparedStatement.setLong(4, student.getUserId().getId());
            preparedStatement.setLong(5, student.getMajorId().getId());
            preparedStatement.setLong(6, student.getContactInfo().getId());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while creating student", e);
        }
        return false;
    }

    @Override
    public Student getById(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToObject(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while getting student by ID", e);
        }
        return null;
    }

    public Student getStudentByName(String name) {
        String getByNameQuery = "select * from students WHERE name = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(getByNameQuery)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToObject(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while getting student by name", e);
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        List<Student> allStudents = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(selectAllQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = mapResultSetToObject(resultSet);
                allStudents.add(student);
            }
        } catch (SQLException e) {
            logger.error("Error occurred while getting all students", e);
        }
        return allStudents;
    }

    @Override
    public boolean update(Student student) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateStudentQuery)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, student.getAdmissionDate());
            preparedStatement.setLong(3, student.getUserId().getId());
            preparedStatement.setLong(4, student.getMajorId().getId());
            preparedStatement.setLong(5, student.getContactInfo().getId());
            preparedStatement.setLong(6, student.getId());
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while updating student", e);
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
            logger.error("Error occurred while deleting student", e);
        }
        return false;
    }

    public Student getStudentByUserID(long userId) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from students where user_id = ?")) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                UserDAO userDAO = new UserDAO();
                User user = userDAO.getById(userId);
                student.setUserId(user);
                long majorId = resultSet.getLong("major_id");
                MajorDAO majorDAO = new MajorDAO();
                Major major = majorDAO.getById(majorId);
                student.setMajorId(major);
                long contactInfoId = resultSet.getLong("contact_info_id");
                ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
                ContactInformation contactInformation = contactInfoDAO.getById(contactInfoId);
                student.setContactInfo(contactInformation);
                return student;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while getting student by user ID", e);
        }
        return null;
    }

    private Student mapResultSetToObject(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Date admissionDate = resultSet.getDate("admission_date");
        long userId = resultSet.getLong("user_id");
        long majorId = resultSet.getLong("major_id");
        long contactInfoId = resultSet.getLong("contact_info_id");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getById(userId);
        MajorDAO majorDAO = new MajorDAO();
        Major major = majorDAO.getById(majorId);
        ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
        ContactInformation contactInformation = contactInfoDAO.getById(contactInfoId);
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAdmissionDate(admissionDate);
        student.setUserId(user);
        student.setMajorId(major);
        student.setContactInfo(contactInformation);
        return student;
    }
}
