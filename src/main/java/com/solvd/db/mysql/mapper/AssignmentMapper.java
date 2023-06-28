package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.Assignment;
import java.util.List;

public interface AssignmentMapper {
    Assignment getAssignmentById(long id);

    List<Assignment> getAllAssignments();

    boolean createAssignment(Assignment assignment);

    boolean updateAssignment(Assignment assignment);

    boolean deleteAssignment(long id);
}
