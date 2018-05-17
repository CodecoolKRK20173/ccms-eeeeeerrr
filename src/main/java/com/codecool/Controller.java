package com.codecool;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.details.Access;
import com.codecool.details.Privilege;
import com.codecool.person.CodecoolPerson;
import com.codecool.person.Mentor;
import com.codecool.person.Student;

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

    private void exit() {
        
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

    public void displayStudents() {
        for (Student student : csvDAOStudent.getAllStudent()) {
            System.out.println(student);
        }
    }

    private Student createStudent() {
        String name;
        String surName;
        String login;
        String password;

        name = view.askUser("Name: ");
        surName = view.askUser("Surname: ");
        login = view.askUser("Login: ");
        password = view.askUser("Password: ");

        return new Student(name, surName, login, password);
    }

    private void addStudent() {
        csvDAOStudent.addStudent(createStudent());
    }

    private Student chooseStudent() {
        String login;
        login = view.askUser("Provide login:  ");
        for (Student student : csvDAOStudent.getAllStudent()) {
            if(student.getLogin().equals(login)) {
                return student;
            }
            view.displayLine("There's no such student");
            chooseStudent();
        }
        return null;
    }

    private void removeStudent(){
        view.displayLine("You are going to remove student: ");
        csvDAOStudent.removeStudent(chooseStudent());
    }

    private void editStudent() {
        String answer;
        view.displayLine("You are going to edit student: ");
        Student student = chooseStudent();
        System.out.println(student);

        answer = view.askUser("Would you like to change name? (y/n)");
        if(answer.equals("y")) {
            student.setName(view.askUser("Name: "));
        }

        answer = view.askUser("Would you like to change surName? (y/n)");
        if(answer.equals("y")) {
            student.setSurName(view.askUser("Surname: "));
        }

        answer = view.askUser("Would you like to change login? (y/n)");
        if(answer.equals("y")) {
            student.setLogin(view.askUser("Login: "));
        }

        answer = view.askUser("Would you like to change password? (y/n)");
        if(answer.equals("y")) {
            student.setPassword(view.askUser("Password: "));
        }
    }




}
