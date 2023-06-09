package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.classes.StudentDAO;
import com.solvd.db.mysql.dao.classes.UserDAO;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.mysql.model.User;

import java.sql.SQLException;

public class UserService {
    User user = null;
    UserDAO userDAO = new UserDAO();

    public User getUser(long userId) throws SQLException {
    user = userDAO.getById(userId);
    if(user != null){
        Student student = new StudentDAO().getStudentByUserID(userId);
        user.setStudent(student);
    }
    return user;
    }

}
