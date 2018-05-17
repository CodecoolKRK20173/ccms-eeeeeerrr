package com.codecool;

import com.codecool.details.Access;
import com.codecool.details.Assignment;
import com.codecool.details.Privilege;

import java.io.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);
    Console cnsl;
    public void displayMenu(List<Privilege> privileges) {
        displayLine("What would you like to do:");
        for (int i = 0; i < privileges.size(); i++) {
            System.out.printf("\t(%d) %s\n", i, privileges.get(i).toString());
        }
    }
    public static void main(String[] args) {
        View view = new View();
        view.displayMenu(Access.MENTOR.getPrivileges());
    }

    public String askUser(String message) {
        displayLine(message);
        return scanner.nextLine();
    }

    public String askUserPassword() {
        cnsl = System.console();
        String password;
        char[] asterix;

        if(cnsl !=  null) {
            char[] passwordArray = cnsl.readPassword("Provide password: ");
            asterix = new char[passwordArray.length];
            Arrays.fill(asterix, '*');
            displayLine(String.valueOf(asterix));
            password = new String(passwordArray);

        } else {
            password = askUser("Provide password: ");
        }
        return password;

    }

    public void displayLine(String lineContent) {
        System.out.println(lineContent);
    }

    public void clearScreen() {
        displayLine("\033[H\033[2J");
    }

    public void displayGrades(List<Assignment> assignments) {
        System.out.println("Your grades:");
        for (Assignment assignment : assignments) {
            System.out.printf("\t %s: %d \n", assignment.getNAME(), assignment.getGrade());
        }
    }
}
