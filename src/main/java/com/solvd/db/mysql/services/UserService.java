package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.StudentDAO;
import com.solvd.db.mysql.dao.classes.UserDAO;
import com.solvd.db.mysql.mapper.UserMapper;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.mysql.model.User;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

public class UserService implements GenericDAO<User>, GetAllInterface<User> {
    private UserMapper userMapper;

    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAllUsers();
    }

    @Override
    public boolean create(User user) {
        return userMapper.createUser(user);
    }

    @Override
    public User getById(long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean update(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public boolean delete(long id) {
        return userMapper.deleteUser(id);
    }

    public User getUser(User user, long userId) throws SQLException {
        user = userMapper.getUserById(userId);
        if(user != null){
            Student student = new StudentDAO().getStudentByUserID(userId);
            user.setStudent(student);
        }
        return user;
    }
}
