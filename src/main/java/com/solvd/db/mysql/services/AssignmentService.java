package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.mapper.AssignmentMapper;
import com.solvd.db.mysql.model.Assignment;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class AssignmentService implements GenericDAO<Assignment>, GetAllInterface<Assignment> {
    private final AssignmentMapper assignmentMapper;

    public AssignmentService(SqlSessionFactory sqlSessionFactory) {
        assignmentMapper = sqlSessionFactory.openSession().getMapper(AssignmentMapper.class);
    }

    @Override
    public List<Assignment> getAll() {
        return assignmentMapper.getAllAssignments();
    }

    @Override
    public boolean create(Assignment assignment) {
        return assignmentMapper.createAssignment(assignment);
    }

    @Override
    public Assignment getById(long id) {
        return assignmentMapper.getAssignmentById(id);
    }

    @Override
    public boolean update(Assignment assignment) {
        return assignmentMapper.updateAssignment(assignment);
    }

    @Override
    public boolean delete(long id) {
        return assignmentMapper.deleteAssignment(id);
    }
}
