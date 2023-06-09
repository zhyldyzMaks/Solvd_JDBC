package com.solvd.db.mysql.model;

public class Major {
    private long id;
    private String name;
    private String description;
    private long departmentId;

    public Major(){}

    public Major(String name, String description, long departmentId) {
        this.name = name;
        this.description = description;
        this.departmentId = departmentId;
    }

    public Major(long id, String name, String description, long departmentId) {
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

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartment(long department) {
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
