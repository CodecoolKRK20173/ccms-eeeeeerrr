package com.codecool.person;

import com.codecool.details.Access;
import com.codecool.details.Assignment;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

public class Student extends CodecoolPerson {
    private int attendance;
    private List<Assignment> assignmentList;

    public Student(String name, String surName, String login, String password) {
        super(name, surName, login, password);
        this.accessLevel = Access.STUDENT;
        this.assignmentList = new ArrayList<>();
    }

    public int getAttendance() {
        return attendance;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public void addAssignment(Assignment assignment) {
        this.assignmentList.add(assignment);
    }

    public int getAttendence() {
        return 0;
    }

    public void setAttendence(int attend) {

    }

    public TemporalAccessor getAssignment() {
        return null;
    }
}
