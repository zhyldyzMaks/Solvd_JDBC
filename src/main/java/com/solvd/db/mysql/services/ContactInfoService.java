package com.solvd.db.mysql.services;

import com.solvd.db.mysql.dao.classes.ContactInfoDAO;
import com.solvd.db.mysql.model.ContactInformation;
import com.solvd.db.utils.GenericDAO;

public class ContactInfoService implements GenericDAO<ContactInformation> {
    private ContactInfoDAO contactInfoDAO;

    public ContactInfoService(){
        contactInfoDAO = new ContactInfoDAO();
    }

    @Override
    public boolean create(ContactInformation contactInformation) {
        return contactInfoDAO.create(contactInformation);
    }

    @Override
    public ContactInformation getById(long id) {
        return contactInfoDAO.getById(id);
    }

    @Override
    public boolean update(ContactInformation contactInformation) {
        return contactInfoDAO.update(contactInformation);
    }

    @Override
    public boolean delete(long id) {
        return contactInfoDAO.delete(id);
    }
}
