package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.utils.GenericDAO;
import com.solvd.db.mysql.model.User;
import com.solvd.db.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> implements GetAllInterface<User> {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static final String insertQuery = "insert into users (username, password) values(?,?)";
    private static final String updateQuery = "update users set username = ?, password = ? where id = ?";
    private static final String selectQuery = "select * from users where id = ?";
    private static final String deleteQuery = "delete from users where id = ?";

    public boolean create(User user) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            if (preparedStatement.executeUpdate()>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    logger.info("User created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create user.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while creating user.", e);
        } return false;
    }

    @Override
    public User getById(long id){
        User user = new User();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving user.", e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("select * from users")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                User user = new User(id, username, password);
                allUsers.add(user);
            }
        }catch (SQLException e){
            logger.error("Error while retrieving all users.", e);
        }
        return allUsers;
    }

    @Override
    public boolean update(User user) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating user.", e);
        }return false;
    }

    @Override
    public boolean delete(long id){
        try ( PreparedStatement preparedStatement = getConnection().prepareStatement(deleteQuery)){
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting user.", e);
        }
        return false;
    }
}