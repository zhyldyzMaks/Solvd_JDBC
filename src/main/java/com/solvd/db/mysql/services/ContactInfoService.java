package com.solvd.db.mysql.services;

import com.solvd.db.mysql.mapper.ContactInfoMapper;
import com.solvd.db.mysql.model.ContactInformation;
import com.solvd.db.utils.GenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;

public class ContactInfoService implements GenericDAO<ContactInformation> {
    private final ContactInfoMapper contactInfoMapper;

    public ContactInfoService(SqlSessionFactory sqlSessionFactory){
        contactInfoMapper = sqlSessionFactory.openSession().getMapper(ContactInfoMapper.class);
    }

    @Override
    public boolean create(ContactInformation contactInformation) {
        return contactInfoMapper.createContactInfo(contactInformation);
    }

    @Override
    public ContactInformation getById(long id) {
        return contactInfoMapper.getContactInfoById(id);
    }

    @Override
    public boolean update(ContactInformation contactInformation) {
        return contactInfoMapper.updateContactInfo(contactInformation);
    }

    @Override
    public boolean delete(long id) {
        return contactInfoMapper.deleteContactInfo(id);
    }
}
