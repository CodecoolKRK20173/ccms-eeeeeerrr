package com.codecool.controllers;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.View;
import com.codecool.details.*;
import com.codecool.person.CodecoolPerson;
import com.codecool.person.Student;

import java.util.List;

public class Controller implements Changeable {
    private CodecoolDAOStudent csvDAOStudent;
    private CodecoolDAOEmployee csvDAOEmployee;
    private static CodecoolPerson user;
    private List<String> assignmentList;


    public Controller() {
        this.csvDAOStudent = new CodecoolDAOStudent();
        this.csvDAOEmployee = new CodecoolDAOEmployee();
        this.assignmentList = new ReadAssignmentsFromFile().createlist();
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public CodecoolDAOStudent getCsvDAOStudent() {
        return csvDAOStudent;
    }

    public CodecoolDAOEmployee getCsvDAOEmployee() {
        return csvDAOEmployee;
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

    public void logOut() {
        exit();
        loginController.signIn();
    }

    public void displayGrades() {
        view.displayLine("Your grades: ");
        view.displayAssignments(((Student) user).getAssignmentList());
    }

    public void errorMessage() {
        view.displayLine("You did something wrong");
    }

    public void exit() {
        csvDAOStudent.saveToFile();
        csvDAOEmployee.saveToFile();
        view.displayLine("Goodbye :)");
    }
}
