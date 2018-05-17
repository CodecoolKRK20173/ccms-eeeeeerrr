package com.codecool.controllers;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.View;
import com.codecool.person.CodecoolPerson;

import java.util.ArrayList;
import java.util.List;

public class LoginController extends Controller {

    public void signIn() {
        String login = askLogin();
        String password = askPassword();
        super.setUserToNull();

        searchStudent(login, password);
        if (super.getUser() == null) {
            searchEmployee(login, password);
        }
        if (super.getUser() == null) {
            view.clearScreen();
            view.displayLine("Wrong login/password. Try again..");
            signIn();
        }
    }
    public String askLogin() {
        return view.askUser("Login");
    }

    private String askPassword() {
        return view.askUserPassword();
    }

    private void searchStudent(String login, String password) {
        for (CodecoolPerson student : super.getCsvDAOStudent().getAllStudent()) {
            if (student.getLogin().equals(login) && student.getPassword().equals(password)) {
                super.setUser(student);
            }
        }
    }

    private void searchEmployee(String login, String password) {
        for (CodecoolPerson employee : super.getCsvDAOEmployee().getAllEmployees()) {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password)) {
                super.setUser(employee);
            }
        }
    }

    public String uniqueLogin() {
        String login = askLogin();
        List<CodecoolPerson> persons = new ArrayList<>();
        persons.addAll(super.getCsvDAOEmployee().getAllEmployees());
        persons.addAll(super.getCsvDAOStudent().getAllStudent());

        for (CodecoolPerson person : persons) {
            if (person.getLogin().equals(login)) {
                view.displayLine("This account already exists.");
                login = uniqueLogin();
            }
        }
        return login;
    }
}
