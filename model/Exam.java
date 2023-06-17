package com.solvd.db.mysql.model;

import java.sql.Date;

public class Exam {
    private long id;
    private String name;
    private Date date;
    private Course course;

    public Exam(){}

    public Exam(String name, Date date, Course course) {
        this.name = name;
        this.date = date;
        this.course = course;
    }
    public Exam(long id, String name, Date date, Course course) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.course = course;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", course=" + course +
                '}';
    }
}
