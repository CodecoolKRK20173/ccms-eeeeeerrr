package com.codecool.person;

import com.codecool.details.Access;

public class Mentor extends CodecoolPerson{

    public Mentor(String name, String surName, String login, String password) {
        super(name, surName, login, password);
        this.setAccessLevel(Access.MENTOR);
    }
}
