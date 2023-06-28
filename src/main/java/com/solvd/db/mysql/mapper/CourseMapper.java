package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.Course;
import java.util.List;

public interface CourseMapper {

    Course getCourseById(long id);

    List<Course> getAllCourses();

    boolean createCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(long id);
}
