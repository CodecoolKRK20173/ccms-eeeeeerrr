package com.codecool.controllers;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.View;
import com.codecool.person.Mentor;

import java.util.List;

public class EmployeeController extends Controller{
    private View view = new View();

    public EmployeeController(CodecoolDAOEmployee csvDAOEmployee, CodecoolDAOStudent csvDAOStudent) {
        super.setCsvDAOEmployee(csvDAOEmployee);
        super.setCsvDAOStudent(csvDAOStudent);
    }

    public Mentor createMentor() {
        String name;
        String surName;
        String login;
        String password;

        name = view.askUser("Name: ");
        surName = view.askUser("Surname: ");
        login = super.uniqueLogin();
        password = view.askUser("Password: ");

        return new Mentor(name, surName, login, password);
    }

    public void addMentor() {
        super.getCsvDAOEmployee().addMentor(createMentor());
    }

    public Mentor chooseMentor() {
        List<Mentor> mentors = super.getCsvDAOEmployee().getAllMentors();
        if (mentors.isEmpty()) {
            view.displayLine("No mentors.");
            return null;
        }

        String login;
        login = super.askLogin();
        for (Mentor mentor : mentors) {
            if(mentor.getLogin().equals(login)) {
                return mentor;
            }
        }
        view.displayLine("There's no such mentor");
        return chooseMentor();
    }

    public void removeMentor(){
        view.displayLine("You are going to remove mentor: ");
        super.getCsvDAOEmployee().removeMentor(chooseMentor());
    }

    public void editMentor() {
        view.displayLine("You are going to edit mentor: ");
        Mentor mentor = chooseMentor();
        if (mentor == null) {
            return;
        }
        System.out.println(mentor);

        changeName(mentor);
        changeSurname(mentor);
        changeLogin(mentor);
        changePassword(mentor);
    }

    public void displayMentors() {
        view.displayMentors(super.getCsvDAOEmployee().getAllMentors());
    }
}

