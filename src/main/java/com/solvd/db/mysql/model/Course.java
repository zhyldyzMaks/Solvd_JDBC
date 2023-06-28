package com.solvd.db.mysql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "course")
public class Course {
    @XmlAttribute
    @JsonProperty("id")
    private long id;
    @XmlElement
    @JsonProperty("name")
    private String name;
    @XmlElement
    @JsonProperty("credits")
    private int credits;
    @JsonProperty("department_id")
    private Department departmentId;
    @XmlElementWrapper(name = "examList")
    @XmlElement(name = "exam")
    @JsonIgnore
    private List<Exam> examList;

    public Course(){}

    public Course(long id, String name, int credits, Department departmentId) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.departmentId = departmentId;
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", departmentId=" + departmentId +
                '}';
    }
}