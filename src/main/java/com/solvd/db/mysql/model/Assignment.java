package com.solvd.db.mysql.model;

import java.sql.Date;

public class Assignment {
    private long id;
    private String name;
    private Date dueDate;
    private int score;
    private long classId;

    public Assignment(){}

    public Assignment(String name, Date dueDate, int score, long classId) {
        this.name = name;
        this.dueDate = dueDate;
        this.score = score;
        this.classId = classId;
    }

    public Assignment(long id, String name, Date dueDate, int score, long classId) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.score = score;
        this.classId = classId;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Assignments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", due date='" + dueDate + '\'' +
                ", score=" + score +
                ", class id=" + classId +
                '}';
    }
}
