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
    private CodecoolPerson user;
    private List<String> assignmentList;
    //private View view = new View();
    private StudentController studentController;
    private EmployeeController employeeController;
    //private LoginController loginController;

    public Controller() {
        this.csvDAOStudent = new CodecoolDAOStudent();
        this.csvDAOEmployee = new CodecoolDAOEmployee();
        this.assignmentList = new ReadAssignmentsFromFile().createlist();
        this.studentController = new StudentController();
        this.employeeController = new EmployeeController();
        //this.loginController = new LoginController();
        loginController.signIn(user, csvDAOStudent, csvDAOEmployee);
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

    public void setUserToNull() {
        this.user = null;
    }

    public void setUser(CodecoolPerson user) {
        this.user = user;
    }

    public void run() {
        Privilege privilege;
        do {
            displayMenu();
            privilege = choosePrivilege();
            handleMenu(privilege);
        } while (isRun(privilege));
    }

    private Privilege choosePrivilege() {
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

    private boolean isRun(Privilege privilege) {
        return privilege != Privilege.EXIT;
    }

    private void displayMenu() {
        view.displayMenu(user.getAccess().getPrivileges());
    }

    private void handleMenu(Privilege privilege) {
        switch (privilege) {
            case ADD_MENTOR:
                employeeController.addMentor();
                break;
            case REMOVE_MENTOR:
                employeeController.removeMentor();
                break;
            case EDIT_MENTOR:
                employeeController.editMentor();
                break;
            case GET_ALL_MENTORS:
                employeeController.displayMentors();
                break;
            case GET_ALL_STUDENTS:
                displayStudents();
                break;
            case ADD_ASSIGNMENT:
                addAssignment();
                break;
            case GRADE_ASSIGNMENT:
                gradeAssignment();
                break;
            case CHECK_ATTENDANCE:
                checkAttendance();
                break;
            case ADD_STUDENT:
                addStudent();
                break;
            case REMOVE_STUDENT:
                removeStudent();
                break;
            case EDIT_STUDENT:
                editStudent();
                break;
            case SUBMIT_ASSIGNMENT:
                submitAssignment();
                break;
            case GET_GRADES:
                displayGrades();
                break;
            case LOG_OUT:
                logOut();
                break;
            case EXIT:
                exit();
                break;
            default:
                errorMessage();
        }
    }

    private void logOut() {
        exit();
        loginController.signIn();
    }

    private void displayGrades() {
        view.displayLine("Your grades: ");
        view.displayAssignments(((Student) user).getAssignmentList());
    }

    private void errorMessage() {
        view.displayLine("You did something wrong");
    }

    private void exit() {
        csvDAOStudent.saveToFile();
        csvDAOEmployee.saveToFile();
        view.displayLine("Goodbye :)");
    }
}
