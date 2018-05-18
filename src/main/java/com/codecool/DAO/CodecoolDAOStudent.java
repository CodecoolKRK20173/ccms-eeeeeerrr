package com.codecool.DAO;

import com.codecool.details.Assignment;
import com.codecool.person.CodecoolPerson;
import com.codecool.person.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CodecoolDAOStudent implements DAOInterfaceStudent{

    private List<Student> studentList;
    private String file = "src/main/resources/student.csv";

    public CodecoolDAOStudent() {
        this.studentList = new ArrayList<>();
        readFile();
    }

    public void addAssignment(Student student, Assignment assignment) {
        student.addAssignment(assignment);
    }

    public void gradeAssignment(Student student, Assignment assignment, int grade) {
        if (student.getAssignmentList().contains(assignment)) {
            assignment.setGrade(43);
        }
    }

    public void submitAssignment(Student student, Assignment assignment) {
        for(Assignment assignment1 : student.getAssignmentList()) {
            if (assignment1.equals(assignment)) {
                assignment.setSubmitted(true);
            }
        }
    }

    public void addStudent(Student student) {
        if (!this.studentList.contains(student)) {
            this.studentList.add(student);
        }
    }

    public void removeStudent(Student student) {
        this.studentList.remove(student);
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
        final int LOGIN_INDEX = 2;
        final int PASSWORD_INDEX = 3;
        final int ATTENDENCE_INDEX = 4;
        final int ASSIGNMENT_INDEX = 5;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))){
            String line = reader.readLine();

            while(line != null){
                List<Assignment> assignmentList = new ArrayList<>();

                String[] oneLine = line.split(",");
                if (oneLine.length > ASSIGNMENT_INDEX) {
                    String[] assignment = oneLine[ASSIGNMENT_INDEX].split(";");
                    for (int i = 0; i < assignment.length; i+=3) {
                        String nameAssignment = assignment[i];
                        boolean isSubmitted = Boolean.parseBoolean(assignment[i + 1]);
                        if (assignment[i + 2].equalsIgnoreCase("null")) {
                            assignmentList.add(new Assignment(nameAssignment, isSubmitted));
                        } else {
                            int grade = Integer.parseInt(assignment[i + 2]);
                            assignmentList.add(new Assignment(nameAssignment, isSubmitted, grade));
                        }
                    }
                }
                Integer attendance = Integer.valueOf(oneLine[ATTENDENCE_INDEX]);
                addStudent(new Student(oneLine[NAME_INDEX], oneLine[SURNAME_INDEX], oneLine[LOGIN_INDEX],
                            oneLine[PASSWORD_INDEX], attendance, assignmentList));

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("There is no such file");
            System.exit(0);
        }
    }

    public void saveToFile() {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(this.file, false));
            for (Student student : this.studentList) {
                writer.printf("%s,%s,%s,%s,%s,", student.getName(), student.getSurName(), student.getLogin(),
                        student.getPassword(), student.getAttendance());
                for (Assignment assignment : student.getAssignmentList()) {
                        writer.printf("%s;%s;",assignment.getNAME(),
                                Boolean.toString(assignment.getIsSubmitted()));
                        if (assignment.getGrade() == null) {
                            writer.printf("%s;", "null");
                        } else {
                            writer.printf("%s;", Integer.toString(assignment.getGrade()));
                        }

                } writer.print("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
