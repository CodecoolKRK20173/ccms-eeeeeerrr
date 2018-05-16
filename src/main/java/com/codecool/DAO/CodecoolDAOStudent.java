package com.codecool.DAO;

import com.codecool.details.Assignment;
import com.codecool.person.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CodecoolDAOStudent implements DAOInterfaceStudent{

    private List<Student> studentList;
    private String file = "student.csv";

    public CodecoolDAOStudent() {
        this.studentList = new ArrayList<Student>();
        readFile(file);
    }

    public void addAssignment(Student student, Assignment assignment) {
        student.getAssignmentList().add(assignment);
    }

    public void gradeAssignment(Student student, Assignment assignment, int grade) {
        if (student.getAssignmentList.contains(assignment)) {
            assignment.setGrade(43);
        }
    }

    public void submitAssignment(Student student, Assignment assignment) {
        student.getAssignmentList();
        //change is submitted
    }

    public void checkAttendence(Student student) {

        int attend = student.getAttendence();
        attend++;
        student.setAttendence(attend);
    }

    public void addStudent(Student student) {
        if (!this.studentList.contains(student)) {
            this.studentList.add(student);
        }
    }

    public void removeStudent(Student student) {
        this.studentList.remove(student);
    }

    public void editStudent(Student student) {

    }


    