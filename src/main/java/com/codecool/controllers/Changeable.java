package com.codecool.controllers;

import com.codecool.View;
import com.codecool.person.CodecoolPerson;

public interface Changeable {
    LoginController loginController = new LoginController();
    View view = new View();

    default void changePassword(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change password? (y/n)");
        if(answer.equals("y")) {
            person.setPassword(view.askUser("Password: "));
        }
    }

    default void changeLogin(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change login? (y/n)");
        if(answer.equals("y")) {
            person.setLogin(new LoginController().uniqueLogin());
        }
    }

    default void changeSurname(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change surName? (y/n)");
        if(answer.equals("y")) {
            person.setSurName(view.askUser("Surname: "));
        }
    }

    default void changeName(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change name? (y/n)");
        if(answer.equals("y")) {
            person.setName(view.askUser("Name: "));
        }
    }
}
