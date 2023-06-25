package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.EnrollmentDAO;
import com.solvd.db.mysql.model.Enrollment;
import com.solvd.db.utils.GenericDAO;
import java.util.List;

public class EnrollmentService implements GenericDAO<Enrollment>, GetAllInterface<Enrollment> {
    private EnrollmentDAO enrollmentDAO;

    public EnrollmentService(){
        enrollmentDAO =  new EnrollmentDAO();
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollmentDAO.getAll();
    }

    @Override
    public boolean create(Enrollment enrollment) {
        return enrollmentDAO.create(enrollment);
    }

    @Override
    public Enrollment getById(long id) {
        return enrollmentDAO.getById(id);
    }

    @Override
    public boolean update(Enrollment enrollment) {
        return enrollmentDAO.update(enrollment);
    }

    @Override
    public boolean delete(long id) {
        return enrollmentDAO.delete(id);
    }
}
