package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.mapper.DepartmentMapper;
import com.solvd.db.mysql.model.Department;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class DepartmentService implements GenericDAO<Department>, GetAllInterface<Department> {
    private final DepartmentMapper departmentMapper;

    public DepartmentService(SqlSessionFactory sqlSessionFactory){
        departmentMapper = sqlSessionFactory.openSession().getMapper(DepartmentMapper.class);
    }
    @Override
    public List<Department> getAll() {
        return departmentMapper.getAllDepartments();
    }

    @Override
    public boolean create(Department department) {
        return departmentMapper.createDepartment(department);
    }

    @Override
    public Department getById(long id) {
        return departmentMapper.getDepartmentById(id);
    }

    @Override
    public boolean update(Department department) {
        return departmentMapper.updateDepartment(department);
    }

    @Override
    public boolean delete(long id) {
        return departmentMapper.deleteDepartment(id);
    }
}
