package com.codecool.controllers;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.details.Privilege;

public class AppController extends Controller {

    private StudentController studentController;
    private EmployeeController employeeController;

    public AppController(CodecoolDAOStudent csvDAOStudent, CodecoolDAOEmployee csvDAOEmployee) {
        studentController = new StudentController(csvDAOStudent, csvDAOEmployee);
        employeeController = new EmployeeController(csvDAOEmployee, csvDAOStudent);
        this.setCsvDAOEmployee(csvDAOEmployee);
        this.setCsvDAOStudent(csvDAOStudent);
        signIn();
    }

    public void handleMenu(Privilege privilege) {
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
                studentController.displayStudents();
                break;
            case ADD_ASSIGNMENT:
                studentController.addAssignment();
                break;
            case GRADE_ASSIGNMENT:
                studentController.gradeAssignment();
                break;
            case CHECK_ATTENDANCE:
                studentController.checkAttendance();
                break;
            case ADD_STUDENT:
                studentController.addStudent();
                break;
            case REMOVE_STUDENT:
                studentController.removeStudent();
                break;
            case EDIT_STUDENT:
                studentController.editStudent();
                break;
            case SUBMIT_ASSIGNMENT:
                studentController.submitAssignment();
                break;
            case GET_GRADES:
                studentController.displayGrades();
                break;
            case LOG_OUT:
                logOut();
                break;
            case EXIT:
                exit();
                break;
            default:
                super.errorMessage();
        }
    }

    public void exit() {
        getCsvDAOStudent().saveToFile();
        getCsvDAOEmployee().saveToFile();
        view.displayLine("Goodbye :)");
    }

    public void logOut() {
        exit();
        signIn();
    }

    public void run() {
        Privilege privilege;
        do {
            displayMenu();
            privilege = choosePrivilege();
            handleMenu(privilege);
        } while (isRun(privilege));
    }
}
