package com.codecool.controllers;

import com.codecool.View;
import com.codecool.person.Mentor;

import java.util.List;

public class EmployeeController {
    private View view = new View();
    public Mentor createMentor() {
        String name;
        String surName;
        String login;
        String password;

        name = view.askUser("Name: ");
        surName = view.askUser("Surname: ");
        login = uniqueLogin();
        password = view.askUser("Password: ");

        return new Mentor(name, surName, login, password);
    }

    public void addMentor() {
        csvDAOEmployee.addMentor(createMentor());
    }

    public Mentor chooseMentor() {
        List<Mentor> mentors = csvDAOEmployee.getAllMentors();
        if (mentors.isEmpty()) {
            view.displayLine("No mentors.");
            return null;
        }

        String login;
        login = askLogin();
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
        csvDAOEmployee.removeMentor(chooseMentor());
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
        view.displayMentors(csvDAOEmployee.getAllMentors());
    }


}
