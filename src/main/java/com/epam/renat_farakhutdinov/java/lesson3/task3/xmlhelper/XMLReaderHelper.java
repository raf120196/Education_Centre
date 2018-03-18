package com.epam.renat_farakhutdinov.java.lesson3.task3.xmlhelper;

import com.epam.renat_farakhutdinov.java.lesson3.task3.students.Course;
import com.epam.renat_farakhutdinov.java.lesson3.task3.students.Student;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Renat Farakhutdinov on 17.03.2018.
 */
public class XMLReaderHelper {
    public static List<Student> getStudentsInfo(String pathname) throws Exception {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(false);
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(pathname));
            document.getDocumentElement().normalize();

            return parseDocument(document);
        } catch (Exception exception) {
            String message = "XML parsing error!";
            throw new Exception(message);
        }
    }

    private static List<Student> parseDocument(Document doc) throws Exception {
        List<Student> students = new ArrayList<Student>();

        Node configuration = doc.getElementsByTagName("configuration").item(0);
        if (configuration.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) configuration;
            NodeList listOfStudents = element.getElementsByTagName("student");

            for (int i = 0; i < listOfStudents.getLength(); i++) {
                students.add(parseStudent(listOfStudents.item(i)));
            }
        }

        return students;
    }

    private static Student parseStudent(Node node) throws Exception {
        String name = getName(node);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            Node curriculum = element.getElementsByTagName("curriculum").item(0);
            String curriculumName = getName(curriculum);

            if (curriculum.getNodeType() == Node.ELEMENT_NODE) {
                Element curriculumElement = (Element) curriculum;

                Node startDate = curriculumElement.getElementsByTagName("start_date").item(0);
                DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sourceFormat.parse(startDate.getTextContent());
                date.setHours(10);
                date.setMinutes(0);
                date.setSeconds(0);

                NodeList coursesList = curriculumElement.getElementsByTagName("course");
                List<Course> courses = new ArrayList<Course>();
                for (int i = 0; i < coursesList.getLength(); i++) {
                    courses.add(parseCourse(coursesList.item(i)));
                }

                return new Student(name, curriculumName, date, courses);
            }
        }

        throw new Exception("XML Student Parsing exception!");
    }

    private static Course parseCourse(Node node) {
        Element course = (Element) node;

        Node courseName = course.getElementsByTagName("course_name").item(0);
        Node duration = course.getElementsByTagName("duration").item(0);

        return new Course(courseName.getTextContent(), Integer.parseInt(duration.getTextContent()));
    }

    private static String getName(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        Node nameAttrib = attributes.getNamedItem("name");

        return nameAttrib.getNodeValue();
    }
}
