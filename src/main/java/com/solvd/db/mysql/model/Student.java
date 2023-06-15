package com.solvd.db.mysql.model;

import java.sql.Date;

public class Student {
    private long id;
    private String name;
    private Date admissionDate;
    private User userId;
    private Major majorId;
    private ContactInformation contactInfoId;

    public Student(){}
    public Student(long id, String name, Date admissionDate) {
        this.id = id;
        this.name = name;
        this.admissionDate = admissionDate;
    }
    public Student(long id, String name, Date admissionDate, User userId, Major majorId, ContactInformation contactInfoId){
        this.id = id;
        this.name = name;
        this.admissionDate = admissionDate;
        this.userId = userId;
        this.majorId = majorId;
        this.contactInfoId = contactInfoId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Major getMajorId() {
        return majorId;
    }

    public void setMajorId(Major majorId) {
        this.majorId = majorId;
    }

    public ContactInformation getContactInfo() {
        return contactInfoId;
    }

    public void setContactInfo(ContactInformation contactInfoId) {
        this.contactInfoId = contactInfoId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admissionDate=" + admissionDate +
                ", userId=" + userId +
                ", majorId=" + majorId +
                ", contactInfoId=" + contactInfoId +
                '}';
    }
}