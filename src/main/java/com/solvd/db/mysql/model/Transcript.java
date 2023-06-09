package com.solvd.db.mysql.model;

import java.sql.Date;

public class Transcript {
    private long id;
    private String grade;
    private Date completionDate;
    private long studentId;
    private long courseId;

    public Transcript(){}

    public Transcript(String grade, Date completionDate, long studentId, long courseId) {
        this.grade = grade;
        this.completionDate = completionDate;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Transcript(long id, String grade, Date completionDate, long studentId, long courseId) {
        this.id = id;
        this.grade = grade;
        this.completionDate = completionDate;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public long getStudent() {
        return studentId;
    }

    public void setStudent(long student) {
        this.studentId = studentId;
    }

    public long getCourse() {
        return courseId;
    }

    public void setCourse(long course) {
        this.courseId = course;
    }

    @Override
    public String toString() {
        return "Transcript{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", completionDate='" + completionDate + '\'' +
                ", student=" + studentId +
                ", course=" + courseId +
                '}';
    }
}
