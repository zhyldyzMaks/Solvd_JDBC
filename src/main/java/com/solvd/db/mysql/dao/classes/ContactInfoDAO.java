package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.ContactInformation;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactInfoDAO implements IDAO<ContactInformation> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public boolean create(ContactInformation contactInfo) {
        Student student = new Student();
        try ( Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into contact_information (first_name, last_name, email, address, phone_number) " +
                    "values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, contactInfo.getName());
            preparedStatement.setString(2, contactInfo.getLastName());
            preparedStatement.setString(3, contactInfo.getEmail());
            preparedStatement.setString(4, contactInfo.getAddress());
            preparedStatement.setString(5, contactInfo.getPhoneNumber());

            if (preparedStatement.executeUpdate()>0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Contact information of student with ID " +  student.getId() + " created.");
                }
            } else {
                System.out.println("Failed to create contact information for student with ID: " + student.getId());
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public ContactInformation getById(long id) throws SQLException {
        ContactInformation contactInfo = new ContactInformation();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from contact_information where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                contactInfo.setId(resultSet.getLong("id"));
                contactInfo.setName(resultSet.getString("first_name"));
                contactInfo.setLastName(resultSet.getString("last_name"));
                contactInfo.setEmail(resultSet.getString("email"));
                contactInfo.setAddress(resultSet.getString("address"));
                contactInfo.setPhoneNumber(resultSet.getString("phone_number"));
            }
        }
        return contactInfo;
    }
    @Override
    public List<ContactInformation> getAll() {
        List<ContactInformation> allContactInfo = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from contact_information ");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");

                ContactInformation contactInfo = new ContactInformation(id, name, lastName, email, address, phoneNumber);
                allContactInfo.add(contactInfo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allContactInfo;
    }

    @Override
    public boolean update(ContactInformation contactInfo) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update contact_information set  first_name = ?, last_name = ?, email = ?," +
                    " address = ?, phone_number = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, contactInfo.getName());
            preparedStatement.setString(2, contactInfo.getLastName());
            preparedStatement.setString(3, contactInfo.getEmail());
            preparedStatement.setString(4, contactInfo.getAddress());
            preparedStatement.setString(5, contactInfo.getPhoneNumber());
            preparedStatement.setLong(6, contactInfo.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from contact_information where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
