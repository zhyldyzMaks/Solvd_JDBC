package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.ContactInformation;
import com.solvd.db.mysql.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ContactInfoDAO implements IDAO<ContactInformation> {
    private static final Logger logger = LogManager.getLogger(TranscriptDAO.class);
    public static final String insertQuery = "insert into contact_information (first_name, last_name, email, address, phone_number) " +
            "values(?,?,?,?,?)";
    public static final String updateQuery = "update contact_information set  first_name = ?, last_name = ?, email = ?," +
            " address = ?, phone_number = ? where id = ?";

    public boolean create(ContactInformation contactInfo) {
        Student student = new Student();
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, contactInfo.getName());
            preparedStatement.setString(2, contactInfo.getLastName());
            preparedStatement.setString(3, contactInfo.getEmail());
            preparedStatement.setString(4, contactInfo.getAddress());
            preparedStatement.setString(5, contactInfo.getPhoneNumber());

            if (preparedStatement.executeUpdate()>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    logger.info("Contact information of student with ID " +  generatedId + " created.");
                }
            } else {
                logger.warn("Failed to create contact information for student with ID: " + student.getId());
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while creating contact information for student.", e);
        } return false;
    }

    @Override
    public ContactInformation getById(long id) {
        ContactInformation contactInfo = new ContactInformation();
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from contact_information where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                contactInfo.setId(resultSet.getLong("id"));
                contactInfo.setName(resultSet.getString("first_name"));
                contactInfo.setLastName(resultSet.getString("last_name"));
                contactInfo.setEmail(resultSet.getString("email"));
                contactInfo.setAddress(resultSet.getString("address"));
                contactInfo.setPhoneNumber(resultSet.getString("phone_number"));
            }
        } catch (SQLException e) {
            logger.error("Error while getting contact information for student.", e);
        }
        return contactInfo;
    }

    @Override
    public boolean update(ContactInformation contactInfo) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, contactInfo.getName());
            preparedStatement.setString(2, contactInfo.getLastName());
            preparedStatement.setString(3, contactInfo.getEmail());
            preparedStatement.setString(4, contactInfo.getAddress());
            preparedStatement.setString(5, contactInfo.getPhoneNumber());
            preparedStatement.setLong(6, contactInfo.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating contact information for student.", e);
        }return false;
    }

    @Override
    public boolean delete(long id) {
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from contact_information where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting contact information for student.", e);
        }
        return false;
    }
}
