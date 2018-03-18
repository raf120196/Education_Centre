package com.epam.renat_farakhutdinov.java.lesson3.task3;

import com.epam.renat_farakhutdinov.java.lesson3.task3.manager.CoursesManager;
import com.epam.renat_farakhutdinov.java.lesson3.task3.xmlhelper.XMLReaderHelper;
import com.epam.renat_farakhutdinov.java.lesson3.task3.students.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Renat Farakhutdinov on 17.03.2018.
 */
public class Main {
    public static void main(String[] args) {
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Scanner in = new Scanner(System.in);

        try {
            List<Student> students = XMLReaderHelper.getStudentsInfo("D:\\Java homeworks\\Education Centre\\src\\students.xml");

            System.out.println("0 - сокращенный вид отчета;");
            System.out.println("1 - полный вид отчета.\n");

            System.out.print("Выбор: ");
            int mode = in.nextInt();

            switch (mode) {
                case 0:
                    for (Student student : students) {
                        String info = CoursesManager.getTimeInfo(student);
                        System.out.println(String.format("%s (%s) - %s. %s",
                                student.getName(), student.getCurriculumName(), student.isFinishedEducationToStr(), info));
                    }
                    break;
                case 1:
                    for (Student student : students) {
                        String info = CoursesManager.getTimeInfo(student);
                        System.out.println(String.format("%s - 10:00-18:00 - (%s) - %d ч. - %s - %s. %s",
                                student.getName(), student.getCurriculumName(), student.getTotalDuration(),
                                sourceFormat.format(CoursesManager.getEndDate(student)), student.isFinishedEducationToStr(), info));
                    }
                    break;
                default:
                    System.out.println("Неверная команда!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
