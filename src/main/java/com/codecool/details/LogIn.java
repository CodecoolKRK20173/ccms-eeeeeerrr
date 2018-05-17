package com.codecool.details;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.View;
import com.codecool.person.CodecoolPerson;

import java.util.ArrayList;
import java.util.List;

public class LogIn {
    private View view = new View();

    public void signIn(CodecoolPerson user, CodecoolDAOStudent csvDAOStudent, CodecoolDAOEmployee csvDAOEmployee) {
        String login = askLogin();
        String password = askPassword();
        user = null;

        searchStudent(login, password, csvDAOStudent, user);
        if (user == null) {
            searchEmployee(login, password, csvDAOEmployee,  user);
        }
        if (user == null) {
            view.clearScreen();
            view.displayLine("Wrong login/password. Try again..");
            signIn(user, csvDAOStudent, csvDAOEmployee);
        }
    }
    private String askLogin() {
        return view.askUser("Login");
    }

    private String askPassword() {
        return view.askUserPassword();
    }

    private void searchStudent(String login, String password, CodecoolDAOStudent csvDAOStudent, CodecoolPerson user) {
        for (CodecoolPerson student : csvDAOStudent.getAllStudent()) {
            if (student.getLogin().equals(login) && student.getPassword().equals(password)) {
                user = student;
            }
        }
    }

    private void searchEmployee(String login, String password, CodecoolDAOEmployee csvDAOEmployee, CodecoolPerson user) {
        for (CodecoolPerson employee : csvDAOEmployee.getAllEmployees()) {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password)) {
                user = employee;
            }
        }
    }

    public String uniqueLogin(CodecoolDAOEmployee csvDAOEmployee, CodecoolDAOStudent csvDAOStudent) {
        String login = askLogin();
        List<CodecoolPerson> persons = new ArrayList<>();
        persons.addAll(csvDAOEmployee.getAllEmployees());
        persons.addAll(csvDAOStudent.getAllStudent());

        for (CodecoolPerson person : persons) {
            if (person.getLogin().equals(login)) {
                view.displayLine("This account already exists.");
                login = uniqueLogin(csvDAOEmployee, csvDAOStudent);
            }
        }
        return login;
    }
}
