package com.solvd.db.mysql.model;

public class ExamGrade {
    private long id;
    private String grade;
    private Exam examId;
    private Student studentId;

    public ExamGrade(){}

    public ExamGrade(String grade, Exam examId) {
        this.grade = grade;
        this.examId = examId;
    }

    public ExamGrade(long id, String grade, Exam examId, Student studentId) {
        this.id = id;
        this.grade = grade;
        this.examId = examId;
        this.studentId = studentId;
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

    public Exam getExamId() {
        return examId;
    }

    public void setExamId(Exam examId) {
        this.examId = examId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "ExamGrade{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", examId=" + examId +
                ", studentId=" + studentId +
                '}';
    }
}