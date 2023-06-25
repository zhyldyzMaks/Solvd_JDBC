package com.solvd.db.mysql.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "exam_grade")
public class ExamGrade {
    @XmlAttribute
    private long id;
    @XmlElement
    private String grade;
    @XmlElement
    private Exam examId;
    @XmlElement
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