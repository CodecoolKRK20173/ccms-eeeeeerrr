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
        this.studentList = new ArrayList<>();
        readFile();
    }

    public void addAssignment(Student student, Assignment assignment) {
        student.addAssignment(assignment);
    }

    public void gradeAssignment(Student student, Assignment assignment, int grade) {
        if (student.getAssignmentList.contains(assignment)) {
            assignment.setGrade(43);
        }
    }

    public void submitAssignment(Student student, Assignment assignment) {
        for(Assignment assignment1 : student.getAssignmentList()) {
            if (assignment1.equals(assignment)) {
                assignment.setIsSubmit(true);
            }
        }
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

    public void readFile() {
        final int NAME_INDEX = 0;
        final int SURNAME_INDEX = 1;
        final int ISSUBMITTED_INDEX = 1;
        final int LOGIN_INDEX = 2;
        final int GRADE_INDEX = 2;
        final int PASSWORD_INDEX = 3;
        final int ATTENDENCE_INDEX = 4;
        final int ASSIGNMENT_INDEX = 5;
        final int ACCESS_LIST_INDEX = 6;


        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line = reader.readLine();

            while(line != null){
                List<Assignment> assignmentList = new ArrayList<>();

                String[] oneLine = line.split(",");
                String[] assignment = oneLine[ASSIGNMENT_INDEX].split(";");

                String nameAssignment = assignment[NAME_INDEX];
                boolean isSubmitted = Boolean.parseBoolean(assignment[ISSUBMITTED_INDEX]);
                int grade = Integer.parseInt(assignment[GRADE_INDEX]);

                assignmentList.add(new Assignment(nameAssignment, isSubmitted, grade));

                Integer attendence = Integer.valueOf(oneLine[ATTENDENCE_INDEX]);
                addStudent(new Student(oneLine[NAME_INDEX], oneLine[SURNAME_INDEX], oneLine[LOGIN_INDEX],
                            oneLine[PASSWORD_INDEX], attendence, assignmentList, oneLine[ACCESS_LIST_INDEX]));

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("There is no such file");
            System.exit(0);
        }
    }

    public void saveToFile() {
        final int NAME_INDEX = 0;
        final int IS_SUBMITTED_INDEX = 1;
        final int GRADE_INDEX = 2;

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(this.file));
            for (Student student : studentList) {
                writer.printf("%s,%s,%s,%s,%s;%s;%s,%s", student.getName(), student.getSurName(), student.getLogin(),
                        student.getPassword(), student.getAssignmentList().get(NAME_INDEX),
                        Integer.toString(student.getAssignment().get(IS_SUBMITTED_INDEX)),
                        Boolean.toString(student.getAssignment().get(GRADE_INDEX)),
                        student.getAccessLevel());
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
