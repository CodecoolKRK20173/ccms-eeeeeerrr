package com.codecool.person;

public class Mentor extends CodecoolPerson{

    public Mentor(String name, String surName, String login, String password) {
        super(name, surName, login, password);
        this.accessLevel = Access.MENTOR;
    }
}
