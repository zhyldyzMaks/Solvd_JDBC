package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.Enrollment;
import java.util.List;

public interface EnrollmentMapper {

    Enrollment getEnrollmentById(long id);

    List<Enrollment> getAllEnrollments();

    boolean createEnrollment(Enrollment enrollment);

    boolean updateEnrollment(Enrollment enrollment);

    boolean deleteEnrollment(long id);
}
