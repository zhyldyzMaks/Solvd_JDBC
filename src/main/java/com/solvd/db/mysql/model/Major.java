package com.solvd.db.mysql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "major")
public class Major {
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("department_id")
    private Department departmentId;

    public Major(){}

    public Major(String name, String description, Department departmentId) {
        this.name = name;
        this.description = description;
        this.departmentId = departmentId;
    }

    public Major(long id, String name, String description, Department departmentId) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartment(Department department) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", department=" + departmentId +
                '}';
    }
}