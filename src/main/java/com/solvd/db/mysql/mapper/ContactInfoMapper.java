package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.ContactInformation;

public interface ContactInfoMapper {

    ContactInformation getContactInfoById(long id);

    boolean createContactInfo(ContactInformation contactInformation);

    boolean updateContactInfo(ContactInformation contactInformation);

    boolean deleteContactInfo(long id);
}
