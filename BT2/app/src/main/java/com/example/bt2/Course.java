package com.example.bt2;

import java.io.Serializable;

public class Course implements Serializable {
    public String courseId, courseName, schedule, teacher, room;

    public Course(String courseId, String courseName, String schedule, String teacher, String room) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.schedule = schedule;
        this.teacher = teacher;
        this.room = room;
    }
}
