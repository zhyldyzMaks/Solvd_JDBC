package com.solvd.db.mysql.model;

import java.sql.Date;

public class Transcript {
    private long id;
    private String grade;
    private Date completionDate;
    private Student studentId;
    private Course courseId;

    public Transcript(){}

    public Transcript(String grade, Date completionDate, Student studentId, Course courseId) {
        this.grade = grade;
        this.completionDate = completionDate;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Transcript(long id, String grade, Date completionDate, Student studentId, Course courseId) {
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

    public Student getStudent() {
        return studentId;
    }

    public void setStudent(Student student) {
        this.studentId = studentId;
    }

    public Course getCourse() {
        return courseId;
    }

    public void setCourseId(Course course) {
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
