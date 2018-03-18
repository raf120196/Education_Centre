package com.epam.renat_farakhutdinov.java.lesson3.task3.manager;

import com.epam.renat_farakhutdinov.java.lesson3.task3.students.Student;

import java.util.Date;

/**
 * Created by Renat Farakhutdinov on 18.03.2018.
 */
public class CoursesManager {
    private static long HOURS2MS = 60 * 60 * 1000;
    private static long DAYS2MS = HOURS2MS * 24;

    public static Date getEndDate(Student student) {
        int totalDuration = student.getTotalDuration();
        int totalDays = totalDuration / 8;
        int endHours = totalDuration % 8;

        Date finishDate;
        if (endHours == 0) {
            finishDate = new Date(student.getStartDate().getTime() + (totalDays - 1) * DAYS2MS + 8 * HOURS2MS);
        } else {
            finishDate = new Date(student.getStartDate().getTime() + totalDays * DAYS2MS + endHours * HOURS2MS);
        }

        return finishDate;
    }

    public static String getTimeInfo(Student student) {
        Date endDate = getEndDate(student);
        Date currentDate = new Date();

        StringBuilder sb = new StringBuilder();
        if (currentDate.after(endDate)) {
            student.setFinishedEducation(true);
            sb.append("После окончания прошло ");
            long timeAfterEnd = currentDate.getTime() - endDate.getTime();
            long days = timeAfterEnd / DAYS2MS;
            long hours = (timeAfterEnd - days * DAYS2MS) / HOURS2MS;

            sb.append(days).append(" д. ");
            sb.append(hours).append(" ч.");
        } else {
            sb.append("До окончания осталось ");
            long diff = endDate.getTime() - currentDate.getTime();
            long diffInDays = diff / DAYS2MS;

            long days = diffInDays;
            long hours = 0;

            if (currentDate.getHours() != 10 && endDate.getHours() != 18) {
                days = diffInDays;
                hours = 18 - currentDate.getHours() + endDate.getHours() - 10;
            }

            if (currentDate.getHours() == 10 && endDate.getHours() != 18) {
                days = diffInDays;
                hours = endDate.getHours() - 10;
            }

            if (currentDate.getHours() != 10 && endDate.getHours() == 18) {
                days = diffInDays;
                hours = 18 - currentDate.getHours();
            }

            if (currentDate.getHours() == 10 && endDate.getHours() == 18) {
                days = diffInDays + 1;
                hours = 0;
            }

            sb.append(days).append(" д. ");
            sb.append(hours).append(" ч.");
        }

        return sb.toString();
    }
}
