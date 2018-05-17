package com.codecool;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.details.Access;
import com.codecool.details.Assignment;
import com.codecool.details.Privilege;
import com.codecool.details.ReadAssignmentsFromFile;
import com.codecool.person.CodecoolPerson;
import com.codecool.person.Student;
import com.codecool.person.Mentor;
import com.codecool.person.Student;

import java.util.ArrayList;
import java.util.Arrays;
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
        this.assignmentList = new ReadAssignmentsFromFile().createlist();
        signIn();
    }

    public void signIn() {
        String login = askLogin();
        String password = askPassword();

        searchStudent(login, password);
        if (user == null) {
            searchEmployee(login, password);
        }
    }
    private String askLogin() {
        return view.askUser("Provide login");
    }

    private String askPassword() {
        return view.askUserPassword();
    }

    private void searchStudent(String login, String password) {
        for (CodecoolPerson student : csvDAOStudent.getAllStudent()) {
            if (student.getLogin().equals(login) && student.getPassword().equals(password)) {
                this.user = student;
            }
        }
    }

    private void searchEmployee(String login, String password) {
        for (CodecoolPerson employee : csvDAOEmployee.getAllEmployees()) {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password)) {
                this.user = employee;
            }
        }
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
        List<Privilege> priviledgeList = user.getAccess().getPrivileges();
        Integer answer = Integer.valueOf(view.askUser("Which option would you like to choose(number)"));
        for(int i = 0; i < priviledgeList.size(); i++) {
            if(answer.equals(i)) {
                return priviledgeList.get(i);
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
                csvDAOStudent.saveToFile();
                csvDAOEmployee.saveToFile();
                exit();
                break;
            default:
                errorMessage();

        }

    }

    private void addAssignment() {
        int countOfAssignments;
        Assignment assignment;

        for (Student student : csvDAOStudent.getAllStudent()) {
            countOfAssignments = student.getAssignmentList().size();
            assignment = new Assignment(this.assignmentList.get(countOfAssignments));
            student.addAssignment(assignment);
        }
    }

    private void gradeAssignment() {
        Student student = chooseStudent();
        Assignment assignment = chooseAssignment(student);
        int grade = chooseGrade();
        assignment.setGrade(grade);
    }

    private int chooseGrade() {
        List<Integer> possibleGrades = new ArrayList<>(Arrays.asList(-3, 0, 2, 4, 7, 10, 12));
        int grade = view.askNumber("Grade: ");
        if (!possibleGrades.contains(grade)) {
            chooseGrade();
        }
        return grade;
    }

    private void checkAttendance() {
        view.displayLine("Check attendance (y/n): ");
        for (Student student : csvDAOStudent.getAllStudent()) {
            checkAttendance(student);
        }
    }

    private void checkAttendance(Student student) {
        String present = view.askUser(String.format("\t%s %s | ", student.getName(), student.getSurName()));
        if (present.equals("y")) {
            student.addAttendance();
        }
    }

    private void submitAssignment() {
        view.displayAssignments(((Student) user).getAssignmentList());
        chooseAssignment().setSubmitted(true);
    }

    private Assignment chooseAssignment() {
        return chooseAssignment((Student) this.user);
    }

    private Assignment chooseAssignment(Student student) {
        List<Assignment> assignments = student.getAssignmentList();

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
        }
        view.displayLine("There's no such mentor");
        return chooseMentor();
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

        int attendance = 0;
        List<Assignment> assignmentList = new ArrayList<>();

        return new Student(name, surName, login, password, attendance, assignmentList);
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
        }
        view.displayLine("There's no such student");
        return chooseStudent();
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
