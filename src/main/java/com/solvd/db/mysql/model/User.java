package com.solvd.db.mysql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
@JsonRootName(value = "user")
public class User {
    @XmlAttribute
    @JsonProperty("id")
    private long id;
    @XmlElement
    @JsonProperty("username")
    private String username;
    @XmlElement
    @JsonProperty("password")
    private String password;
    @XmlElement(name = "student_id")
    @JsonIgnore
    private Student student;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}