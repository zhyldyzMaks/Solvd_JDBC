package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.StudentDAO;
import com.solvd.db.mysql.mapper.StudentMapper;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentService implements GenericDAO<Student>, GetAllInterface<Student> {
    private final StudentMapper studentMapper;

    public StudentService(SqlSessionFactory sqlSessionFactory) {
        studentMapper = sqlSessionFactory.openSession().getMapper(StudentMapper.class);
    }

    @Override
    public List<Student> getAll() {
        return studentMapper.getAllStudents();
    }

    @Override
    public boolean create(Student student) {
        return studentMapper.createStudent(student);
    }

    @Override
    public Student getById(long id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public boolean update(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public boolean delete(long id) {
        return studentMapper.deleteStudent(id);
    }

    public Student getStudentByName(String name) {
        return studentMapper.getStudentByName(name);
    }

    public Student getStudentByUserId(long id) {
        return studentMapper.getStudentByUserId(id);
    }
}
