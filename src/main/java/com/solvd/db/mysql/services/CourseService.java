package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.CourseDAO;
import com.solvd.db.mysql.model.Course;
import com.solvd.db.utils.GenericDAO;
import java.util.List;

public class CourseService implements GenericDAO<Course>, GetAllInterface<Course> {
    private CourseDAO courseDAO;

    public CourseService(){
        courseDAO = new CourseDAO();
    }
    @Override
    public List<Course> getAll() {
        return courseDAO.getAll();
    }

    @Override
    public boolean create(Course course) {
        return courseDAO.create(course);
    }

    @Override
    public Course getById(long id) {
        return courseDAO.getById(id);
    }

    @Override
    public boolean update(Course course) {
        return courseDAO.update(course);
    }

    @Override
    public boolean delete(long id) {
        return courseDAO.delete(id);
    }
}
