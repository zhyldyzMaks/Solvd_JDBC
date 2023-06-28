package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.Department;
import java.util.List;

public interface DepartmentMapper {

    Department getDepartmentById(long id);

    List<Department> getAllDepartments();

    boolean createDepartment(Department department);

    boolean updateDepartment(Department department);

    boolean deleteDepartment(long id);
}
