package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.mapper.ClassMapper;
import com.solvd.db.mysql.model.ClassTable;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class ClassService implements GenericDAO<ClassTable>, GetAllInterface<ClassTable> {
    private final ClassMapper classMapper;

    public ClassService(SqlSessionFactory sqlSessionFactory){
        classMapper = sqlSessionFactory.openSession().getMapper(ClassMapper.class);
    }

    @Override
    public List<ClassTable> getAll() {
        return classMapper.getAllClasses();
    }

    @Override
    public boolean create(ClassTable classTable) {
        return classMapper.createClass(classTable);
    }

    @Override
    public ClassTable getById(long id) {
        return classMapper.getClassById(id);
    }

    @Override
    public boolean update(ClassTable classTable) {
        return classMapper.updateClass(classTable);
    }

    @Override
    public boolean delete(long id) {
        return classMapper.deleteClass(id);
    }
}
