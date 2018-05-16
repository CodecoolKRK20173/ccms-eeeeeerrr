package com.codecool;

import com.codecool.details.Access;
import com.codecool.details.Privilege;

public class App {
    public static void main(String[] args) {
        Access enumAccess = Access.MANAGER;
        for (Privilege enumPrivileges : enumAccess.getPrivileges()) {
            System.out.println(enumPrivileges.toString());
        }

        Access enumAccess2 = Access.STUDENT;
        for (Privilege enumPrivileges : enumAccess2.getPrivileges()) {
            System.out.println(enumPrivileges.toString());
        }
        Access enumAccess3 = Access.MENTOR;
        for (Privilege enumPrivileges : enumAccess3.getPrivileges()) {
            System.out.println(enumPrivileges.toString());
        }
    }
}
