package com.solvd.db.mysql;

import com.solvd.db.mysql.dao.classes.StudentDAO;
import com.solvd.db.mysql.dao.classes.UserDAO;
import com.solvd.db.mysql.model.Major;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.mysql.model.User;
import com.solvd.db.mysql.services.UserService;
import com.solvd.db.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        User user = new User();
        user.setUsername("Asel");
        user.setPassword("asel123");

        UserDAO userDAO = new UserDAO();
        userDAO.create(user);

        User user1;
        try {
            user1 = new UserDAO().getById(15);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user1);

        long userToBeDeleted = 2;
        boolean deleted = userDAO.delete(userToBeDeleted);
        if(deleted){
            System.out.println("User with id " + userToBeDeleted+ " deleted successfully");
        } else {
            System.out.println("User not found or failed to delete");
        }
        UserService userService = new UserService();
        userService.getUser(16);

        List<User> users = userDAO.getAll();
        for (User userr : users){
            System.out.println("User ID: " + userr.getId());
            System.out.println("Username : " + userr.getUsername());
            System.out.println("Password " + userr.getPassword());
            System.out.println("-----------------------------------");
        }

        StudentDAO studentDAO = new StudentDAO();
        Major major = new Major();
        Student student1 = new Student(16,"Alya Alyar",new Date(2002,8,10),14,1,1);
        boolean isSuccess = studentDAO.create(student1);
        if (isSuccess){
            System.out.println("Student created successfully.");
        }else {
            System.out.println("Failed to create student.");
        }
        Student student2;
        student2 = new StudentDAO().getStudentByName("Alex");
        System.out.println(student2);

        long studentToBeDeleted = 3;
        boolean deletedStudent = studentDAO.delete(studentToBeDeleted);
        if(deletedStudent){
            System.out.println("Student with id " + studentToBeDeleted+ " deleted successfully");
        } else {
            System.out.println("Student not found or failed to delete");
        }

        List<Student> students = studentDAO.getAll();
        System.out.println("List of all students:");
        for (Student student : students) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name : " + student.getName());
            System.out.println("Admission date: " + student.getAdmissionDate());
            System.out.println("User Id: " + student.getUserId());
//            System.out.println("-----------------------------------");
//        }

        }

    }
}
