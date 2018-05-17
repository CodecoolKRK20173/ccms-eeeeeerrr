package com.codecool.person;

import com.codecool.details.Access;
import com.codecool.details.Assignment;

import java.util.List;

public class Student extends CodecoolPerson {
    private int attendance;
    private List<Assignment> assignmentList;

    public Student(String name, String surName, String login, String password, int attendance,
                   List<Assignment> assignmentList) {
        super(name, surName, login, password);
        this.accessLevel = Access.STUDENT;
        this.assignmentList = assignmentList;
        this.attendance = attendance;
    }

    public int getAttendance() {
        return this.attendance;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void addAssignment(Assignment assignment) {
        this.assignmentList.add(assignment);
    }

    public void addAttendance() {
        this.attendance++;
    }
}
