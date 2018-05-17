package com.codecool;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.details.Access;
import com.codecool.details.Assignment;
import com.codecool.details.Privilege;
import com.codecool.person.CodecoolPerson;
import com.codecool.person.Student;

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
        switch (privilege) {
            case ADD_MENTOR:
                addMentor();
                break;
            case REMOVE_MENTOR:
                removeMentor();
                break;
            case EDIT_MENTOR:
                editMentor();
                break;
            case GET_ALL_MENTORS:
                displayMentors();
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
            case EXIT:
                exit();
                break;
            default:
                errorMessage();

        }

    }

    private void submitAssignment() {
        view.displayAssignments(((Student) user).getAssignmentList());
        chooseAssignment().setSubmitted(true);
    }

    private Assignment chooseAssignment() {
        List<Assignment> assignments = ((Student) user).getAssignmentList();

       int assignmentNumber = view.askNumber("Choose assignment: ");
       if (assignmentNumber <= 0 || assignmentNumber > assignmentList.size()) {
           view.displayLine("There's no such Assignment");
           chooseAssignment();
       }

        return assignments.get(assignmentNumber);
    }

    private void displayGrades() {
        view.displayLine("Your grades: ");
        view.displayAssignments(((Student) user).getAssignmentList());
    }

    private void errorMessage() {
        view.displayLine("You did something wrong");
    }

    private void exit() {
        view.displayLine("Goodbye :)");
    }


}
