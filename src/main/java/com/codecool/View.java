package com.codecool;

import com.codecool.details.Access;
import com.codecool.details.Privilege;

import java.util.List;
import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);
    public void displayMenu(List<Privilege> privileges) {
        System.out.println("What would you like to do:");
        for (int i = 0; i < privileges.size(); i++) {
            System.out.printf("\t(%d) %s\n", i, privileges.get(i).toString());
        }
    }
    public static void main(String[] args) {
        View view = new View();
        view.displayMenu(Access.MENTOR.getPrivileges());
    }

    public String askUser(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }


}
