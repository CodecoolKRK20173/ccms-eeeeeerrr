package com.codecool.person;

import com.codecool.details.Access;

public class Manager extends CodecoolPerson{

    public Manager(String name, String surName, String login, String password) {
        super(name, surName, login, password);
        this.setAccessLevel(Access.MANAGER);
    }
}
