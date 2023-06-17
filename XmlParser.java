package com.solvd.db.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlParser {
    private static final Logger logger = LogManager.getLogger(XmlParser.class);

    public static void parseUniversity(Element universityElement) {
        NodeList users = universityElement.getElementsByTagName("users");
        if (users.getLength() > 0) {
            Element usersElement = (Element) users.item(0);
            parseUsers(usersElement);
        }
        NodeList students = universityElement.getElementsByTagName("students");
        if (students.getLength() > 0) {
            Element studentsElement = (Element) students.item(0);
            parseStudents(studentsElement);
        }
        NodeList contactInformation = universityElement.getElementsByTagName("contact_information");
        if (contactInformation.getLength() > 0) {
            Element contactInformationElement = (Element) contactInformation.item(0);
            parseContactInformation(contactInformationElement);
        }
        NodeList exams = universityElement.getElementsByTagName("exams");
        if (exams.getLength() > 0) {
            Element examsElement = (Element) exams.item(0);
            parseExams(examsElement);
        }
        NodeList examGrades = universityElement.getElementsByTagName("exam_grades");
        if (examGrades.getLength() > 0) {
            Element examGradesElement = (Element) examGrades.item(0);
            parseExamGrades(examGradesElement);
        }
    }

    public static void parseUsers(Element usersElement) {
        NodeList userElements = usersElement.getElementsByTagName("user");
        for (int i = 0; i < userElements.getLength(); i++) {
            Element userElement = (Element) userElements.item(i);
            String userId = userElement.getAttribute("id");
            String username = getTextContent(userElement, "username");
            String password = getTextContent(userElement, "password");

            logger.info("User ID: " + userId);
            logger.info("Username: " + username);
            logger.info("Password: " + password);
            logger.info("");
        }
    }

    public static void parseStudents(Element studentsElement){
        NodeList studentElements = studentsElement.getElementsByTagName("student");
        for (int i = 0; i < studentElements.getLength(); i++) {
            Element studentElement = (Element) studentElements.item(i);
            String studentId = studentElement.getAttribute("id");
            String name = getTextContent(studentElement, "name");
            String admissionDate = getTextContent(studentElement, "admission_date");

            logger.info("Student ID: " + studentId);
            logger.info("Name: " + name);
            logger.info("Admission Date: " + admissionDate);
            logger.info("");
        }
    }

    public static void parseContactInformation(Element contactInformationElement){
        NodeList contactInfoElements = contactInformationElement.getElementsByTagName("contact_info1");
        for (int i = 0; i < contactInfoElements.getLength(); i++) {
            Element contactInfoElement = (Element) contactInfoElements.item(i);
            String contactInfoId = contactInfoElement.getAttribute("id");
            String firstName = getTextContent(contactInfoElement, "first_name");
            String lastName = getTextContent(contactInfoElement, "last_name");
            String email = getTextContent(contactInfoElement, "email");
            String address = getTextContent(contactInfoElement, "address");
            String phoneNumber = getTextContent(contactInfoElement, "phone_number");

            logger.info("Contact Info ID: " + contactInfoId);
            logger.info("First Name: " + firstName);
            logger.info("Last Name: " + lastName);
            logger.info("Email: " + email);
            logger.info("Address: " + address);
            logger.info("Phone Number: " + phoneNumber);
            logger.info("");
        }
    }

    public static void parseExams(Element examsElement){
        NodeList examElements = examsElement.getElementsByTagName("exam");
        for (int i = 0; i < examElements.getLength(); i++) {
            Element examElement = (Element) examElements.item(i);
            String id = getTextContent(examElement, "id");
            String name = getTextContent(examElement, "name");
            String date = getTextContent(examElement, "date");
            String courseId = getTextContent(examElement, "course_id");

            logger.info("Exam ID: " + id);
            logger.info("Name: " + name);
            logger.info("Date: " + date);
            logger.info("Course ID: " + courseId);
            logger.info("");
        }
    }

    public static void parseExamGrades(Element examGradesElement){
        NodeList examGradeElements = examGradesElement.getElementsByTagName("exam_grade");
        for (int i = 0; i < examGradeElements.getLength(); i++){
            Element examGradeElement = (Element) examGradeElements.item(i);
            String gradeId = examGradeElement.getAttribute("id");
            String grade = getTextContent(examGradeElement, "grade");
            String examId = getTextContent(examGradeElement,"id");

            logger.info("Grade ID: " + gradeId);
            logger.info("Grade: " + grade);
            logger.info("Exam ID: " + examId);

            Element studentIdElement = (Element) examGradeElement.getElementsByTagName("student_id").item(0);
            String studentId = studentIdElement.getAttribute("id");
            String studentName = getTextContent(studentIdElement, "name");
            String admissionDate = getTextContent(studentIdElement, "admission_date");

            logger.info("Student ID: " + studentId);
            logger.info("Student Name: " + studentName);
            logger.info("Admission Date: " + admissionDate);
            logger.info("");
        }
    }

    public static String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Element tagElement = (Element) nodeList.item(0);
            return tagElement.getTextContent();
        }
        return "";
    }

    public static void main(String[] args) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/main/resources/university.xml"));
            Element element = document.getDocumentElement();
            parseUniversity(element);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}