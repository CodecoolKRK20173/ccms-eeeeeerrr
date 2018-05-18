package com.codecool.controllers;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.View;
import com.codecool.details.*;
import com.codecool.person.CodecoolPerson;
import com.codecool.person.Student;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    View view = new View();
    private CodecoolDAOStudent csvDAOStudent;
    private CodecoolDAOEmployee csvDAOEmployee;
    private static CodecoolPerson user;
    private List<String> assignmentList;


    public Controller() {
        this.assignmentList = new ReadAssignmentsFromFile().createlist();
    }

    public CodecoolDAOStudent getCsvDAOStudent() {
        return csvDAOStudent;
    }

    public CodecoolDAOEmployee getCsvDAOEmployee() {
        return csvDAOEmployee;
    }

    public void setCsvDAOStudent(CodecoolDAOStudent csvDAOStudent) {
        this.csvDAOStudent = csvDAOStudent;
    }

    public void setCsvDAOEmployee(CodecoolDAOEmployee csvDAOEmployee) {
        this.csvDAOEmployee = csvDAOEmployee;
    }

    public CodecoolPerson getUser() {
        return user;
    }

    public List<String> getAssignmentList() {
        return assignmentList;
    }

    public void setUserToNull() {
        this.user = null;
    }

    public void setUser(CodecoolPerson user) {
        this.user = user;
    }

    public Privilege choosePrivilege() {
        List<Privilege> privileges = user.getAccess().getPrivileges();
        Integer answer = Integer.valueOf(view.askUser("Which option would you like to choose(number)"));
        for(int i = 0; i < privileges.size(); i++) {
            if(answer.equals(i)) {
                return privileges.get(i);
            }
        }
        view.displayLine("There's no such option!");
        return choosePrivilege();
    }

    public boolean isRun(Privilege privilege) {
        return privilege != Privilege.EXIT;
    }

    public void displayMenu() {
        view.displayMenu(user.getAccess().getPrivileges());
    }

    public void displayGrades() {
        view.displayLine("Your grades: ");
        view.displayAssignments(((Student) user).getAssignmentList());
    }

    public void errorMessage() {
        view.displayLine("You did something wrong");
    }

    public void changePassword(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change password? (y/n)");
        if(answer.equals("y")) {
            person.setPassword(view.askUser("Password: "));
        }
    }

    public void changeLogin(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change login? (y/n)");
        if(answer.equals("y")) {
            person.setLogin(uniqueLogin());
        }
    }

    public void changeSurname(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change surName? (y/n)");
        if(answer.equals("y")) {
            person.setSurName(view.askUser("Surname: "));
        }
    }

    public void changeName(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change name? (y/n)");
        if(answer.equals("y")) {
            person.setName(view.askUser("Name: "));
        }
    }

    public void signIn() {
        String login = askLogin();
        String password = askPassword();
        setUserToNull();

        searchStudent(login, password);
        if (getUser() == null) {
            searchEmployee(login, password);
        }
        if (getUser() == null) {
            view.clearScreen();
            view.displayLine("Wrong login/password. Try again..");
            signIn();
        }
    }
    public String askLogin() {
        view.clearScreen();
        return view.askUser("Login");
    }

    private String askPassword() {
        return view.askUserPassword();
    }

    private void searchStudent(String login, String password) {
        for (CodecoolPerson student : getCsvDAOStudent().getAllStudent()) {
            if (student.getLogin().equals(login) && student.getPassword().equals(password)) {
                setUser(student);
            }
        }
    }

    private void searchEmployee(String login, String password) {
        for (CodecoolPerson employee : getCsvDAOEmployee().getAllEmployees()) {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password)) {
                setUser(employee);
            }
        }
    }

    public String uniqueLogin() {
        String login = askLogin();
        List<CodecoolPerson> persons = new ArrayList<>();
        persons.addAll(getCsvDAOEmployee().getAllEmployees());
        persons.addAll(getCsvDAOStudent().getAllStudent());

        for (CodecoolPerson person : persons) {
            if (person.getLogin().equals(login)) {
                view.displayLine("This account already exists.");
                login = uniqueLogin();
            }
        }
        return login;
    }
}

