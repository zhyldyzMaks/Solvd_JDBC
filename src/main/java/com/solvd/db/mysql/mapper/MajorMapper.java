package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.Major;
import java.util.List;

public interface MajorMapper {

    Major getMajorById(long id);

    List<Major> getAllMajors();

    boolean createMajor(Major major);

    boolean updateMajor(Major major);

    boolean deleteMajor(long id);
}
