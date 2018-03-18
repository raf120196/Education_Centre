package com.epam.renat_farakhutdinov.java.lesson3.task3.students;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Renat Farakhutdinov on 17.03.2018.
 */
public class Student {
    private String name;
    private String curriculumName;
    private Date startDate;
    private List<Course> courses;
    private boolean isFinishedEducation;

    public Student(String name, String curriculumName, Date startDate, List<Course> courses) {
        this.name = name;
        this.curriculumName = curriculumName;
        this.startDate = startDate;
        this.courses = courses;
        isFinishedEducation = false;
    }

    public String getName() {
        return name;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }


        Student student = (Student) obj;
        return name.equals(student.name) && curriculumName.equals(student.curriculumName)
                && startDate.equals(student.startDate) && courses.equals(student.courses);
    }

    @Override
    public String toString() {
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return "Student: [name = \"" + name + "\", curriculum = \"" + curriculumName + "\", start_date = \"" +
                sourceFormat.format(startDate) + "\", courses = \"" + courses.toString() + "\"]";
    }

    public boolean isFinishedEducation() {
        return isFinishedEducation;
    }

    public void setFinishedEducation(boolean finishedEducation) {
        isFinishedEducation = finishedEducation;
    }

    public String isFinishedEducationToStr() {
        if (isFinishedEducation) {
            return "Обучение закончено";
        } else {
            return "Обучение не закончено";
        }
    }

    public int getTotalDuration() {
        int totalDuration = 0;
        for (Course course : courses) {
            totalDuration += course.getDuration();
        }

        return totalDuration;
    }
}
