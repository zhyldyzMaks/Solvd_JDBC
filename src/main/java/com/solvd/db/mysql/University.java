package com.solvd.db.mysql;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.db.mysql.model.*;
import java.util.List;

public class University {
    @JsonProperty("assignments")
    private List<Assignment> assignments;

    @JsonProperty("departments")
    private List<Department> departments;

    @JsonProperty("majors")
    private List<Major> majors;

    @JsonProperty("contact_information")
    private List<ContactInformation> contactInformation;

    @JsonProperty("users")
    private List<User> users;

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }

    public List<ContactInformation> getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(List<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "University{" +
                "assignments=" + assignments +
                ", departments=" + departments +
                ", majors=" + majors +
                ", contactInformation=" + contactInformation +
                ", users=" + users +
                '}';
    }
}
