package com.solvd.db.mysql.model;

public class ClassTable {
    private long id;
    private String roomNumber;
    private String schedule;
    private long courseId;

    public ClassTable(){}

    public ClassTable(String roomNumber, String schedule, long courseId) {
        this.roomNumber = roomNumber;
        this.schedule = schedule;
        this.courseId = courseId;
    }
    public ClassTable(long id, String roomNumber, String schedule, long courseId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.schedule = schedule;
        this.courseId = courseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ClassTable{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", schedule='" + schedule + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
