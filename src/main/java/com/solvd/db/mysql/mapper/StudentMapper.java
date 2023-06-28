package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.Student;
import java.util.List;

public interface StudentMapper {

    Student getStudentById(long id);

    Student getStudentByName(String name);

    Student getStudentByUserId(long userId);

    List<Student> getAllStudents();

    boolean createStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(long id);
}
