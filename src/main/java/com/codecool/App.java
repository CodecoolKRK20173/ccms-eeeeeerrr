package com.codecool;

import com.codecool.controllers.AppController;
import com.codecool.controllers.Controller;

public class App {
    public static void main(String[] args) {
        AppController controller = new AppController();
        controller.run();
    }
}
