package com.solvd.db.mysql.model;

public class Grade {
    private long id;
    private int score;
    private long assignment;

    public Grade(){}

    public Grade(int score, long assignment) {
        this.score = score;
        this.assignment = assignment;
    }

    public Grade(long id, int score, long assignment) {
        this.id = id;
        this.score = score;
        this.assignment = assignment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getAssignment() {
        return assignment;
    }

    public void setAssignment(long assignment) {
        this.assignment = assignment;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", score=" + score +
                ", assignment=" + assignment +
                '}';
    }
}
