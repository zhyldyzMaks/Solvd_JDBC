package com.solvd.db.mysql.model;

import java.sql.Date;

public class Enrollment {
    private long id;
    private Date date;
    private long studentId;
    private long courseId;

    public Enrollment(){}

    public Enrollment(Date date, long studentId, long courseId){
        this.date = date;
        this.studentId = studentId;
        this.courseId = courseId;
    }
    public Enrollment(long id, Date date, long studentId, long courseId){
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

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
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
