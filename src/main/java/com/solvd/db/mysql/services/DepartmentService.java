package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.dao.classes.DepartmentDAO;
import com.solvd.db.mysql.model.Department;
import com.solvd.db.utils.GenericDAO;

import java.util.List;

public class DepartmentService implements GenericDAO<Department>, GetAllInterface<Department> {
    private DepartmentDAO departmentDAO;

    public DepartmentService(){
        departmentDAO = new DepartmentDAO();
    }
    @Override
    public List<Department> getAll() {
        return departmentDAO.getAll();
    }

    @Override
    public boolean create(Department department) {
        return departmentDAO.create(department);
    }

    @Override
    public Department getById(long id) {
        return departmentDAO.getById(id);
    }

    @Override
    public boolean update(Department department) {
        return departmentDAO.update(department);
    }

    @Override
    public boolean delete(long id) {
        return departmentDAO.delete(id);
    }
}
