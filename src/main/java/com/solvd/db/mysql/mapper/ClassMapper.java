package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.ClassTable;
import java.util.List;

public interface ClassMapper {

    ClassTable getClassById(long id);

    List<ClassTable> getAllClasses();

    boolean createClass(ClassTable classTable);

    boolean updateClass(ClassTable classTable);

    boolean deleteClass(long id);
}
