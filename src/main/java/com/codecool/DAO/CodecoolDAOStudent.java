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


    public List<Assignment> getAllGrades(Student student) {
        return student.getAssignmentList();
    }

    public List<Student> getAllStudent() {
        return this.studentList;
    }

    public void readFile(String file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while(line != null){

                String[] oneLine = line.split(",");

                String name = oneLine[0];
                String surName = oneLine[1];
                String login = oneLine[2];
                String password = oneLine[3];
                int attendence = Integer.parseInt(oneLine[4]);

                List<Assignment> assignmentList = new ArrayList<Assignment>();

                String[] assignment = oneLine[5].split(";");
                String nameAssignment = assignment[0];
                boolean isSubmitted = Boolean.parseBoolean(assignment[1]);
                int grade = Integer.parseInt(assignment[2]);

                assignmentList.add(new Assignment(nameAssignment, isSubmitted, grade));
                addStudent(new Student(oneLine[0],oneLine[1],oneLine[2],oneLine[3],oneLine[4],assignmentList));

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("There is no such file");
            System.exit(0);
        }
    }

    public void saveToFile() {

        String filename = "student.csv";
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename, true));
            for (Student student : studentList) {
                writer.println(student.getName() + "," + student.getSurName() + "," + student.getLogin() + "," +
                        student.getPassword() + "," + student.getAssignmentList().get(0) + ";" +
                        Integer.toString(student.getAssignment().get(1)) + ";" +
                        Boolean.toString(student.getAssignment().get(2));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
