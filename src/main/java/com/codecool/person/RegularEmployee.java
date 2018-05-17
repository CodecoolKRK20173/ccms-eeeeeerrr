package com.codecool.person;

import com.codecool.details.Access;

public class RegularEmployee extends CodecoolPerson {

    public RegularEmployee(String name, String surName, String login, String password) {
        super(name, surName, login, password);
        this.setAccessLevel(Access.REGULAR_EMPLOYEE);
    }
}
