package com.solvd.db.mysql.model;

public class Course {
    private long id;
    private String name;
    private int credits;
    private Department departmentId;

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
