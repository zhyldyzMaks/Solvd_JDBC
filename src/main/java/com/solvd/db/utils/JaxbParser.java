package com.solvd.db.utils;

import com.solvd.db.mysql.model.Course;
import com.solvd.db.mysql.model.Exam;
import com.solvd.db.mysql.model.User;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class JaxbParser {
    private static final Logger logger = LogManager.getLogger(JaxbParser.class);
    private Course course;

    public void unmarshalXml(){
        File xmlFile = new File("src/main/resources/course.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(Course.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            course = (Course) unmarshaller.unmarshal(xmlFile);
            String courseName = course.getName();
            logger.info("Course name: "+ courseName);
            List<Exam> examList = course.getExamList();
            for (Exam exam : examList) {
                logger.info("Exam name: " + exam.getName());
                logger.info("Exam date: " + exam.getDate());
            }
        } catch (JAXBException e) {
            logger.warn("Failed to parse xml file.", e);
            throw new RuntimeException(e);
        }
    }

        public void marshalXml(){
        File xmlFile = new File("src/main/resources/course.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(Course.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(course, xmlFile);
        } catch (JAXBException e) {
            logger.warn("Failed to marshal Course class to xml file.", e);
        }
    }

    public void setCourse(Course course){
        this.course = course;
    }

    public static void main(String[] args) {
        JaxbParser jaxbParser = new JaxbParser();
        Course course = new Course();
        jaxbParser.setCourse(course);
        jaxbParser.marshalXml();
        jaxbParser.unmarshalXml();
    }
}
