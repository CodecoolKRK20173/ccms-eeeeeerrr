package com.codecool.controllers;

import com.codecool.View;
import com.codecool.details.Assignment;
import com.codecool.person.CodecoolPerson;
import com.codecool.person.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentController {
    private View view = new View();


    public void displayStudents() {
        view.displayStudents(csvDAOStudent.getAllStudent());
    }

    private Student createStudent() {
        String name;
        String surName;
        String login;
        String password;

        name = view.askUser("Name: ");
        surName = view.askUser("Surname: ");
        login = uniqueLogin();
        password = view.askUser("Password: ");

        int attendance = 0;
        List<Assignment> assignmentList = new ArrayList<>();

        return new Student(name, surName, login, password, attendance, assignmentList);
    }

    private void addStudent() {
        csvDAOStudent.addStudent(createStudent());
    }

    private Student chooseStudent() {
        List<Student> students = csvDAOStudent.getAllStudent();
        if (students.isEmpty()) {
            view.displayLine("No students.");
            return null;
        }
        String login = askLogin();
        for (Student student : students) {
            if(student.getLogin().equals(login)) {
                return student;
            }
        }
        view.displayLine("There's no such student");
        return chooseStudent();
    }
    private void removeStudent(){
        view.displayLine("You are going to remove student: ");
        csvDAOStudent.removeStudent(chooseStudent());
    }

    private void editStudent() {
        view.displayLine("You are going to edit student: ");
        CodecoolPerson student = chooseStudent();
        if (student == null) {
            return;
        }
        System.out.println(student);

        changeName(student);
        changePassword(student);
        changeLogin(student);
        changePassword(student);
    }
    public void displayStudents() {
        view.displayStudents(csvDAOStudent.getAllStudent());
    }
    private void addAssignment() {
        int countOfAssignments;
        Assignment assignment;

        for (Student student : csvDAOStudent.getAllStudent()) {
            countOfAssignments = student.getAssignmentList().size();
            assignment = new Assignment(this.assignmentList.get(countOfAssignments));
            student.addAssignment(assignment);
        }
    }

    private void gradeAssignment() {
        Student student = chooseStudent();
        if (student == null) {
            return;
        }
        Assignment assignment = chooseAssignment(student);
        if (assignment == null) {
            return;
        }
        if (assignment.getIsSubmitted()) {
            int grade = chooseGrade();
            assignment.setGrade(grade);
        }
    }
    private int chooseGrade() {
        List<Integer> possibleGrades = new ArrayList<>(Arrays.asList(-3, 0, 2, 4, 7, 10, 12));
        int grade = view.askNumber("Grade: ");
        if (!possibleGrades.contains(grade)) {
            return chooseGrade();
        }
        return grade;
    }

    private void checkAttendance() {
        view.displayLine("Check attendance (y/n): ");
        for (Student student : csvDAOStudent.getAllStudent()) {
            checkAttendance(student);
        }
    }

    private void checkAttendance(Student student) {
        String present = view.askUser(String.format("\t%s %s | ", student.getName(), student.getSurName()));
        if (present.equals("y")) {
            student.addAttendance();
        }
    }

    private void submitAssignment() {
        Assignment assignment = chooseAssignment();
        if (assignment != null) {
            assignment.setSubmitted(true);
        }
    }

    private Assignment chooseAssignment() {
        return chooseAssignment((Student) this.user);
    }

    private Assignment chooseAssignment(Student student) {
        List<Assignment> assignments = student.getAssignmentList();
        view.displayAssignments(assignments);
        if (assignments.isEmpty()) {
            view.displayLine("No assignments.");
            return null;
        }

        int assignmentNumber = view.askNumber("Choose assignment: ") - 1;
        if ((assignmentNumber < 0) || (assignmentNumber >= assignments.size())) {
            view.displayLine("There's no such Assignment");
            return chooseAssignment(student);
        }
        return assignments.get(assignmentNumber);
    }
    private void displayGrades() {
        view.displayLine("Your grades: ");
        view.displayAssignments(((Student) user).getAssignmentList());
    }
}
