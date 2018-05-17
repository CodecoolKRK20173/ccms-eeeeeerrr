package com.codecool.details;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadAssignmentsFromFile {

    private String file = "src/main/resources/assignment.csv";

    public List<String> createlist(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            List<String> assignmentList = new ArrayList<>();
            String line = reader.readLine();

            while(line != null){
                assignmentList.add(line);
                line = reader.readLine();
            }

            return assignmentList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}