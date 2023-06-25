package com.solvd.db.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.db.mysql.University;
import com.solvd.db.mysql.model.Assignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    private static final Logger logger = LogManager.getLogger(JSONParser.class);
    File jsonFile = new File("src/main/resources/university.json");
    ObjectMapper objectMapper = new ObjectMapper();

    public void serialize(){
        try {
            File jsonFile2 = new File("src/main/resources/deserialize.json");
            University university = new University();
            List<Assignment> assignments = new ArrayList<>();
            Assignment assignment = new Assignment();
            assignment.setId(1);
            assignment.setName("Assignment 1");
            assignment.setScore(10);
            Date date = new Date(2023,9,10);
            assignment.setDueDate(date);
            assignments.add(assignment);
            university.setAssignments(assignments);
            objectMapper.writeValue(jsonFile2, university);
            logger.info("Serialization completed successfully.");
        } catch (IOException ex) {
            logger.warn("Error occurs while converting java format to JSON format.", ex);
        }

    }

    public void deserialize(){
        try {
            University university = objectMapper.readValue(jsonFile,University.class);
            logger.info(university.getAssignments());
            logger.info(university.getMajors());
            logger.info(university.getDepartments());
        } catch (IOException e) {
            logger.warn("Error occurs while converting JSON data to java format.", e);
        }
    }

    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();
        jsonParser.deserialize();
        jsonParser.serialize();
    }
}
