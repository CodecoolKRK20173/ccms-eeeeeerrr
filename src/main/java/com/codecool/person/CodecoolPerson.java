package com.codecool.person;

import com.codecool.details.Access;

public class CodecoolPerson {
    String name;
    String surName;
    String login;
    String password;
    Access accessLevel;

    public CodecoolPerson(String name, String surName, String login, String password) {
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessLevel() {
        return accessLevel.toString();
    }

    public Access getAccess() {
        return null;
    }
}
