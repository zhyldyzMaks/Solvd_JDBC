package com.solvd.db.utils;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    public static void main(String[] args) {
        String xmlFilePath = "src/main/resources/university.xml";
        String xsdFilePath = "src/main/resources/university.xsd";

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
            System.out.println("XML file is valid against the XSD schema..");
        } catch (SAXException | IOException e) {
            System.out.println("XML file is not valid against the XSD schema.");
            e.printStackTrace();
        }
    }
}
