package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.AssignmentDAO;
import com.solvd.db.mysql.model.Assignment;
import com.solvd.db.utils.GenericDAO;

import java.util.List;

public class AssignmentService implements GenericDAO<Assignment>, GetAllInterface<Assignment> {
    private AssignmentDAO assignmentDAO;

    public AssignmentService(){
        assignmentDAO = new AssignmentDAO();
    }

    @Override
    public List<Assignment> getAll() {
        return assignmentDAO.getAll();
    }

    @Override
    public boolean create(Assignment assignment) {
        return assignmentDAO.create(assignment);
    }

    @Override
    public Assignment getById(long id) {
        return assignmentDAO.getById(id);
    }

    @Override
    public boolean update(Assignment assignment) {
        return assignmentDAO.update(assignment);
    }

    @Override
    public boolean delete(long id) {
        return assignmentDAO.delete(id);
    }
}
