package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.StudentDAO;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.utils.GenericDAO;

import java.util.List;

public class StudentService implements GenericDAO<Student>, GetAllInterface<Student> {
    private StudentDAO studentDAO;

    public StudentService(){
        studentDAO = new StudentDAO();
    }

    @Override
    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public boolean create(Student student) {
        return studentDAO.create(student);
    }

    @Override
    public Student getById(long id) {
        return studentDAO.getById(id);
    }

    @Override
    public boolean update(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public boolean delete(long id) {
        return studentDAO.delete(id);
    }
}
