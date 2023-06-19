package com.solvd.db.mysql.model;

import com.solvd.db.utils.DateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Date;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "assignment")
public class Assignment {
    @XmlAttribute
    private long id;
    @XmlElement
    private String name;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dueDate;
    @XmlElement
    private int score;
    @XmlAttribute
    private ClassTable classId;


    public Assignment(){}

    public Assignment(String name, Date dueDate, int score, ClassTable classId) {
        this.name = name;
        this.dueDate = dueDate;
        this.score = score;
        this.classId = classId;
    }

    public Assignment(long id, String name, Date dueDate, int score, ClassTable classId) {
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

    public ClassTable getClassId() {
        return classId;
    }

    public void setClassId(ClassTable classId) {
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