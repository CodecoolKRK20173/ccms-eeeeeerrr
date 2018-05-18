package com.codecool;

import com.codecool.DAO.CodecoolDAOEmployee;
import com.codecool.DAO.CodecoolDAOStudent;
import com.codecool.controllers.AppController;

public class App {
    public static void main(String[] args) {
        CodecoolDAOStudent csvDAOStudent = new CodecoolDAOStudent();
        CodecoolDAOEmployee csvDAOEmployee = new CodecoolDAOEmployee();
        AppController controller = new AppController(csvDAOStudent, csvDAOEmployee);
        controller.run();
    }
}
