package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.ContactInformation;
import com.solvd.db.mysql.model.Major;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.mysql.model.User;
import com.solvd.db.utils.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends AbstractDAO<Student> implements IDAO<Student> {
    private static final Logger logger = LogManager.getLogger(StudentDAO.class);
    public static final String createStudentQuery = "insert into students (id, name, admission_date, " +
            "user_id, major_id, contact_info_id) values (?,?,?,?,?,?)";
    public static final String updateStudentQuery = "update students set name = ?, admission_date = ?, user_id = ?," +
            " major_id = ?, contact_info_id = ? where id = ?";

    @Override
    public boolean create(Student student) {

        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(createStudentQuery);
            preparedStatement.setLong(1,student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setDate(3, student.getAdmissionDate());
            preparedStatement.setLong(4, student.getUserId().getId());
            preparedStatement.setLong(5, student.getMajorId().getId());
            preparedStatement.setLong(6, student.getContactInfo().getId());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e){
            logger.error("Error occurred while creating student", e);
        }
        return false;
    }

    @Override
    public Student getById(long id) {
        try ( Connection connection = ConnectionManager.getConnection()) {
            String getQuery = "select * from students where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                long userId = resultSet.getLong("user_id");
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
        }catch (SQLException e){
            logger.error("Error occurred while getting student by ID", e);
        }
        return null;
    }

    public Student getStudentByName(String name){
        String getByNameQuery = "select * from students WHERE name = ?";
        try (Connection connection = ConnectionManager.getConnection()){
            Student student = new Student();
            PreparedStatement preparedStatement = connection.prepareStatement(getByNameQuery);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setAdmissionDate(resultSet.getDate("admission_date"));
            long userId = resultSet.getLong("user_id");
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
        } catch (SQLException e) {
            logger.error("Error occurred while getting student by name", e);
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        List<Student> allStudents = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String getAllQuery = "SELECT * FROM students";
            PreparedStatement preparedStatement = connection.prepareStatement(getAllQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            UserDAO userDAO = new UserDAO();
            MajorDAO majorDAO = new MajorDAO();
            ContactInfoDAO contactInfoDAO = new ContactInfoDAO();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                long userId = resultSet.getLong("user_id");
                User user = userDAO.getById(userId);
                student.setUserId(user);
                long majorId = resultSet.getLong("major_id");
                Major major = majorDAO.getById(majorId);
                student.setMajorId(major);
                long contactInfoId = resultSet.getLong("contact_info_id");
                ContactInformation contactInformation = contactInfoDAO.getById(contactInfoId);
                student.setContactInfo(contactInformation);
                allStudents.add(student);
            }
        }catch (SQLException e){
            logger.error("Error occurred while getting all students", e);
        }
        return allStudents;
    }

    @Override
    public boolean update(Student student) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateStudentQuery);
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
        }return false;
    }

    @Override
    public boolean delete(long id)  {
        try(Connection connection = ConnectionManager.getConnection()){
            String deleteQuery = "delete from students where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error occurred while deleting student", e);
        } return false;
    }

    public Student getStudentByUserID(long userId) {
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where user_id = ?");
            preparedStatement.setLong(1,userId);
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
        }return null;
    }
}