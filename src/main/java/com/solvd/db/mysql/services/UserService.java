package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.StudentDAO;
import com.solvd.db.mysql.dao.classes.UserDAO;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.mysql.model.User;
import com.solvd.db.utils.GenericDAO;

import java.sql.SQLException;
import java.util.List;

public class UserService implements GenericDAO<User>, GetAllInterface<User> {
    private UserDAO userDAO = new UserDAO();

    public UserService(){
        userDAO = new UserDAO();
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public boolean create(User user) {
        return userDAO.create(user);
    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(long id) {
        return userDAO.delete(id);
    }

    public User getUser(User user, long userId) throws SQLException {
        user = userDAO.getById(userId);
        if(user != null){
            Student student = new StudentDAO().getStudentByUserID(userId);
            user.setStudent(student);
        }
        return user;
    }
}
