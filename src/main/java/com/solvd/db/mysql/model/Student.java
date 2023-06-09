package com.solvd.db.mysql.model;

import java.sql.Date;

public class Student {
    private long id;
    private String name;
    private Date admissionDate;
    private long userId;
    private long majorId;
    private long contactInfo;


    public Student(){}
    public Student(long id, String name, Date admissionDate) {
        this.id = id;
        this.name = name;
        this.admissionDate = admissionDate;
    }
    public Student(long id, String name, Date admissionDate, long userId, long majorId, long contactInfo){
        this.id = id;
        this.name = name;
        this.admissionDate = admissionDate;
        this.userId = userId;
        this.majorId = majorId;
        this.contactInfo = contactInfo;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMajorId() {
        return majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }
    //    public User getUser() {

    public long getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(long contactInfo) {
        this.contactInfo = contactInfo;
    }
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Major getMajor() {
//        return major;
//    }
//
//    public void setMajor(Major major) {
//        this.major = major;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admissionDate=" + admissionDate +
                ", userId=" + userId +
                ", majorId=" + majorId +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
