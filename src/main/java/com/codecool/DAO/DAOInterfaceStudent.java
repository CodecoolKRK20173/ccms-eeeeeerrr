package com.codecool.DAO;

import com.codecool.details.Assignment;
import com.codecool.person.Student;

import java.util.List;

public interface DAOInterfaceStudent {

    AccessLevelList ACCESS;
    void addAssignment(Assignment assignment);
    void gradeAssignment(Student student, Assignment assignment, int grade);
    void checkAttendence(Student student);
    void addStudent(Student student);
    void removeStudent(Student student);
    void editStudent(Student student);
    void submitAssignment(Student student, Assignment assignment);
    List<Assignment> getAllGrades(Student student);
    List<Student> getAllStudent();

}

