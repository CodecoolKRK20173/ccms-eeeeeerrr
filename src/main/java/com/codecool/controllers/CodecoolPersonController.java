package com.codecool.controllers;

import com.codecool.View;
import com.codecool.person.CodecoolPerson;

public abstract class CodecoolPersonController {
    private LoginController loginController;
    private View view = new View();
    public void changePassword(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change password? (y/n)");
        if(answer.equals("y")) {
            person.setPassword(view.askUser("Password: "));
        }
    }

    public void changeLogin(CodecoolPerson person) {
        String answer = view.askUser("Would you like to change login? (y/n)");
        if(answer.equals("y")) {
            person.setLogin(loginController.uniqueLogin());
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
}
