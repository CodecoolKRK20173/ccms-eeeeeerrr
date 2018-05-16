package com.codecool;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.person.CodecoolPerson;

import java.util.List;

public class Controller {
    private CodecoolDAOStudent csvDAOStudent;
    private CodecoolDAOEmployee csvDAOEmployee;
    private CodecoolPerson user;
    private List<String> assignmentList;
    private View view = new View();

    public void signIn() {
        String login = view.askUserLogin();
        String password = view.askUserPassword();

        user = searchStudent(login, password);
        if (user == null) {
            user = searchEmployee(login, password);
        }
    }

    private CodecoolPerson searchStudent(String login, String password) {
        csvDAOStudent = new CodecoolDAOStudent();

        for (CodecoolPerson student : csvDAOStudent.getAllStudents()) {
            if (student.getLogin().equals(login) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    private CodecoolPerson searchEmployee(String login, String password) {
        csvDAOEmployee = new CodecoolDAOEmployee();

        for (CodecoolPerson employee : csvDAOEmployee.getAllEmployee()) {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password)) {
                return employee;
            }
        }
        return null;
    }
    
    public void handleMenu() {

    }
}
