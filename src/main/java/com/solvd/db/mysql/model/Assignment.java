package com.solvd.db.mysql.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.db.utils.JAXBDateAdapter;
import com.solvd.db.utils.JSONDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Date;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "assignment")
public class Assignment {
    @XmlAttribute
    @JsonProperty("id")
    private long id;
    @XmlElement
    @JsonProperty("name")
    private String name;
    //@XmlJavaTypeAdapter(JAXBDateAdapter.class)
    @JsonSerialize(using = JSONDateAdapter.class)
    private Date dueDate;
    @XmlElement
    @JsonProperty("max_score")
    private int maxScore;
    @XmlAttribute
    @JsonProperty("class_id")
    private ClassTable classId;


    public Assignment(){}

    public Assignment(String name, Date dueDate, int maxScore, ClassTable classId) {
        this.name = name;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
        this.classId = classId;
    }

    public Assignment(long id, String name, Date dueDate, int maxScore, ClassTable classId) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
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
        return maxScore;
    }

    public void setScore(int maxScore) {
        this.maxScore = maxScore;
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
                ", maximum score=" + maxScore +
                ", class id=" + classId +
                '}';
    }
}