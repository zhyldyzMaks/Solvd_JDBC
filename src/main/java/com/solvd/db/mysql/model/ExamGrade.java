package com.solvd.db.mysql.model;

public class ExamGrade {
    private long id;
    private String grade;
    private long exam;

    public ExamGrade(){}

    public ExamGrade(String grade, long exam) {
        this.grade = grade;
        this.exam = exam;
    }

    public ExamGrade(long id, String grade, long exam) {
        this.id = id;
        this.grade = grade;
        this.exam = exam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public long getExam() {
        return exam;
    }

    public void setExam(long exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "ExamGrade{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", exam=" + exam +
                '}';
    }
}
