package com.epam.renat_farakhutdinov.java.lesson3.task3.students;

/**
 * Created by Renat Farakhutdinov on 17.03.2018.
 */
public class Course {
    private String name;
    private int duration;

    public Course(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Course course = (Course) obj;
        return name.equals(course.name) && duration == course.duration;
    }

    @Override
    public String toString() {
        return "Course: [name = \"" + name + "\", duration = \"" + duration + "\"]";
    }
}
