package com.codecool.person;

public class RegularEmployee extends CodecoolPerson {

    public RegularEmployee(String name, String surName, String login, String password) {
        super(name, surName, login, password);
        this.accessLevel = Access.REGULAR_EMPLOYEE;
    }
}
