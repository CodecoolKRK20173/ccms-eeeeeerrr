package com.codecool.person;

public class Manager extends CodecoolPerson{

    public Manager(String name, String surName, String login, String password) {
        super(name, surName, login, password);
        this.accessLevel = Access.MANAGER;
    }
}
