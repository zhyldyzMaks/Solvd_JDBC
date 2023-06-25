package com.solvd.db.mysql.model;

import java.sql.Date;

public class Enrollment {
    private long id;
    private Date date;
    private Student studentId;
    private Course courseId;

    public Enrollment(){}

    public Enrollment(Date date, Student studentId, Course courseId){
        this.date = date;
        this.studentId = studentId;
        this.courseId = courseId;
    }
    public Enrollment(long id, Date date, Student studentId, Course courseId){
        this.id = id;
        this.date = date;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEnrollmentDate() {
        return date;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.date = enrollmentDate;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", enrollmentDate='" + date + '\'' +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
