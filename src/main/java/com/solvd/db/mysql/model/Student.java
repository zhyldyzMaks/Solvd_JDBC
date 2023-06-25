package com.solvd.db.mysql.model;

import jakarta.xml.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "student")
public class Student {
    @XmlElement
    private long id;
    @XmlElement
    private String name;
    @XmlElement
    private Date admissionDate;
    @XmlElement
    private User userId;
    @XmlElement
    private Major majorId;
    @XmlElement
    private ContactInformation contactInfoId;
    @XmlElement
    private Transcript transcript;
    @XmlElementWrapper(name = "enrollments")
    @XmlElement(name = "enrollment")
    private List<Enrollment> enrollmentsForStudent;
    @XmlElementWrapper(name = "exam_grades")
    @XmlElement(name = "exam_grade")
    private List<ExamGrade> allGrades;

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

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public List<Enrollment> getEnrollmentsForStudent() {
        return enrollmentsForStudent;
    }

    public void setEnrollmentsForStudent(List<Enrollment> enrollmentsForStudent) {
        this.enrollmentsForStudent = enrollmentsForStudent;
    }

    public List<ExamGrade> getAllGrades() {
        return allGrades;
    }

    public void setAllGrades(List<ExamGrade> allGrades) {
        this.allGrades = allGrades;
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
                ", transcript=" + transcript +
                ", enrollmentsForStudent=" + enrollmentsForStudent +
                ", allGrades=" + allGrades +
                '}';
    }
}