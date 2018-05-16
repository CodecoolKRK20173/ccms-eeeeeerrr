package com.codecool;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.details.Access;
import com.codecool.details.Privilege;
import com.codecool.person.CodecoolPerson;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private CodecoolDAOStudent csvDAOStudent;
    private CodecoolDAOEmployee csvDAOEmployee;
    private CodecoolPerson user;
    private List<String> assignmentList;
    private View view = new View();

    public Controller() {
        this.csvDAOStudent = new CodecoolDAOStudent();
        this.csvDAOEmployee = new CodecoolDAOEmployee();
        this.assignmentList = new ArrayList<>();
    }

    public void signIn() {
        String login = askLogin();
        String password = askPassword();

        user = searchStudent(login, password);
        if (user == null) {
            user = searchEmployee(login, password);
        }
    }
    private String askLogin() {
        return view.askUser("Provide login");
    }

    private String askPassword() {
        return view.askUser("Provide password");
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

        for (CodecoolPerson employee : csvDAOEmployee.getAllEmployee()) {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password)) {
                return employee;
            }
        }
        return null;
    }

    public void run() {
        Privilege privilege;
        do {
            displayMenu();
            privilege = choosePrivilage();
            handleMenu(privilege);
        } while (isRun(privilege));
    }

    private Privilege choosePrivilage() {
        return null;
    }

    private boolean isRun(Privilege privilege) {
        return privilege != Privilege.EXIT;
    }

    private void displayMenu() {
        view.displayMenu(user.getAccess().getPrivileges());
    }

    private void handleMenu(Privilege privilege) {


    }


}
