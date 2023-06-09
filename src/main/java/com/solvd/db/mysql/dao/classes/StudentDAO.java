package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IDAO<Student> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Student getStudentByUserID(long userId) throws SQLException {
        Student student = null;
        Connection connection = ConnectionManager.getConnection();
        preparedStatement = connection.prepareStatement("select * from students where user_id = ?");
        preparedStatement.setLong(1,userId);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Date admissionDate = resultSet.getDate("admission_date");
            int usrId = resultSet.getInt("user_id");
            int majorId = resultSet.getInt("major_id");
            int contactInfo = resultSet.getInt("contact_info");


//            UserDAO userDAO = new UserDAO();
//            User user = userDAO.getById(userId);
//
//            MajorDAO majorDAO = new MajorDAO();
//            Major major = majorDAO.getMajorByID(resultSet.getInt("id"));
            student = new Student(id, name, admissionDate, usrId, majorId, contactInfo);
        }
        return student;
    }

    @Override
    public boolean create(Student student) throws SQLException {
        String insertQuery = "insert into students (id, name, admission_date, user_id, major_id, contact_info_id) " +
                "values (?,?,?,?,?,?)";
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setLong(1,student.getId());
            statement.setString(2, student.getName());
            statement.setDate(3, student.getAdmissionDate());
            statement.setLong(4, student.getUserId());
            statement.setLong(5, student.getMajorId());
            statement.setLong(6, student.getContactInfo());
            statement.executeUpdate();
        }
        return false;
    }

    @Override
    public Student getById(long id) throws SQLException {
        Student student = new Student();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from students where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setUserId(resultSet.getInt("user_id"));
                student.setMajorId(resultSet.getInt("major_id"));
                student.setContactInfo(resultSet.getInt("contact_info_id"));
            }
        }
        return student;
    }

    public Student getStudentByName(String name){
        Student student = new Student();
        String query = "select * from students WHERE name = ?";
        try (Connection connection = ConnectionManager.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date admissionDate = resultSet.getDate("admission_date");
                return new Student(id, name, admissionDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> allStudents = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from students");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date admissionDate = resultSet.getDate("admission_date");
                int userId = resultSet.getInt("user_id");
                int majorId = resultSet.getInt("major_id");
                int contactInfoId = resultSet.getInt("contact_info_id");

                Student student = new Student(id, name, admissionDate, userId, majorId, contactInfoId);
                allStudents.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public boolean update(Student student) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update students set name = ?, admission_date = ?, user_id = ?, major_id = ?," +
                    " contact_info_id = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, student.getName());
            statement.setDate(2, student.getAdmissionDate());
            statement.setLong(3, student.getUserId());
            statement.setLong(4, student.getMajorId());
            statement.setLong(5, student.getContactInfo());
            statement.setLong(6, student.getId());

            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from students where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
