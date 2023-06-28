package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.GetAllInterface;
import com.solvd.db.mysql.mapper.MajorMapper;
import com.solvd.db.mysql.model.Major;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class MajorService implements GenericDAO<Major>, GetAllInterface<Major> {
    private final MajorMapper majorMapper;

    public MajorService(SqlSessionFactory sqlSessionFactory){
        majorMapper = sqlSessionFactory.openSession().getMapper(MajorMapper.class);
    }

    @Override
    public List<Major> getAll() {
        return majorMapper.getAllMajors();
    }

    @Override
    public boolean create(Major major) {
        return majorMapper.createMajor(major);
    }

    @Override
    public Major getById(long id) {
        return majorMapper.getMajorById(id);
    }

    @Override
    public boolean update(Major major) {
        return majorMapper.updateMajor(major);
    }

    @Override
    public boolean delete(long id) {
        return majorMapper.deleteMajor(id);
    }
}
