package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.mapper.EnrollmentMapper;
import com.solvd.db.mysql.model.Enrollment;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class EnrollmentService implements GenericDAO<Enrollment>, GetAllInterface<Enrollment> {
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentService(SqlSessionFactory sqlSessionFactory){
        enrollmentMapper =  sqlSessionFactory.openSession().getMapper(EnrollmentMapper.class);
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollmentMapper.getAllEnrollments();
    }

    @Override
    public boolean create(Enrollment enrollment) {
        return enrollmentMapper.createEnrollment(enrollment);
    }

    @Override
    public Enrollment getById(long id) {
        return enrollmentMapper.getEnrollmentById(id);
    }

    @Override
    public boolean update(Enrollment enrollment) {
        return enrollmentMapper.updateEnrollment(enrollment);
    }

    @Override
    public boolean delete(long id) {
        return enrollmentMapper.deleteEnrollment(id);
    }
}
