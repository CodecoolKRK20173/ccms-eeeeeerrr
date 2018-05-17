package com.codecool;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.details.Access;
import com.codecool.details.Privilege;
import com.codecool.person.CodecoolPerson;
import com.codecool.person.Mentor;

import java.sql.SQLOutput;
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
        return view.askUserPassword();
    }

    private CodecoolPerson searchStudent(String login, String password) {

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

    private Mentor createMentor() {
        String name;
        String surName;
        String login;
        String password;

        name = view.askUser("Name: ");
        surName = view.askUser("Surname: ");
        login = view.askUser("Login: ");
        password = view.askUser("Password: ");

        return new Mentor(name, surName, login, password);
    }

    private void addMentor() {
        csvDAOEmployee.addMentor(createMentor());
    }

    private Mentor chooseMentor() {
        String login;
        login = view.askUser("Provide login:  ");
        for (Mentor mentor : csvDAOEmployee.getAllMentors()) {
            if(mentor.getLogin().equals(login)) {
                return mentor;
            }
            view.displayLine("There's no such mentor");
            chooseMentor();
        }
        return null;
    }



    private void removeMentor(){
        view.displayLine("You are going to remove mentor: ");
        csvDAOEmployee.removeMentor(chooseMentor());
    }

    private void editMentor() {
        String answer;
        view.displayLine("You are going to edit mentor: ");
        Mentor mentor = chooseMentor();
        System.out.println(mentor);

        answer = view.askUser("Would you like to change name? (y/n)");
        if(answer.equals("y")) {
            mentor.setName(view.askUser("Name: "));
        }

        answer = view.askUser("Would you like to change surName? (y/n)");
        if(answer.equals("y")) {
            mentor.setSurName(view.askUser("Surname: "));
        }

        answer = view.askUser("Would you like to change login? (y/n)");
        if(answer.equals("y")) {
            mentor.setLogin(view.askUser("Login: "));
        }

        answer = view.askUser("Would you like to change password? (y/n)");
        if(answer.equals("y")) {
            mentor.setPassword(view.askUser("Password: "));
        }
    }

    public void displayMentors() {
        for (Mentor mentor : csvDAOEmployee.getAllMentors()) {
            System.out.println(mentor);
        }

    }
}
