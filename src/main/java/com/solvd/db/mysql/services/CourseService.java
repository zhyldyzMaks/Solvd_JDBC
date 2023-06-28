package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.CourseDAO;
import com.solvd.db.mysql.mapper.CourseMapper;
import com.solvd.db.mysql.model.Course;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CourseService implements GenericDAO<Course>, GetAllInterface<Course> {
    private final CourseMapper courseMapper;

    public CourseService(SqlSessionFactory sqlSessionFactory){
        courseMapper = sqlSessionFactory.openSession().getMapper(CourseMapper.class);
    }
    @Override
    public List<Course> getAll() {
        return courseMapper.getAllCourses();
    }

    @Override
    public boolean create(Course course) {
        return courseMapper.createCourse(course);
    }

    @Override
    public Course getById(long id) {
        return courseMapper.getCourseById(id);
    }

    @Override
    public boolean update(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public boolean delete(long id) {
        return courseMapper.deleteCourse(id);
    }
}
