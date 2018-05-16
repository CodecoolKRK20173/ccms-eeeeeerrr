package com.codecool.DAO;

import com.codecool.person.CodecoolPerson;
import com.codecool.person.Manager;
import com.codecool.person.Mentor;
import com.codecool.person.RegularEmployee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodecoolDAOEmployee implements DAOInterfaceEmp {
    private List<Mentor> mentorList = new ArrayList<>();
    private List<Manager> managerList = new ArrayList<>();
    private List<RegularEmployee> regularEmployeeList = new ArrayList<>();

    public void readFile(String fileName) {
        final int NAME_INDEX = 0;
        final int SURNAME_INDEX = 1;
        final int LOGIN_INDEX = 2;
        final int PASSWORD_INDEX = 3;
        final int ACCESS_INDEX = 4;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] myEmloyee = line.split(",");// STUDENT MANAGER, EMPLO?YEE, MENTOR
                if (myEmloyee[4].equals("MANAGER")) {
                    managerList.add(new Manager(myEmloyee[NAME_INDEX], myEmloyee[SURNAME_INDEX], myEmloyee[LOGIN_INDEX], myEmloyee[PASSWORD_INDEX], myEmloyee[ACCESS_INDEX]))
                } else {
                    if (myEmloyee[4].equals("MENTOR")) {
                        mentorList.add(new Mentor(myEmloyee[NAME_INDEX], myEmloyee[SURNAME_INDEX], myEmloyee[LOGIN_INDEX], myEmloyee[PASSWORD_INDEX], myEmloyee[ACCESS_INDEX]))
                    } else {
                        if (myEmloyee[4].equals("REGULAR_EMPLOYEE")) {
                            regularEmployeeList.add(new RegularEmployee(myEmloyee[NAME_INDEX], myEmloyee[SURNAME_INDEX], myEmloyee[LOGIN_INDEX], myEmloyee[PASSWORD_INDEX], myEmloyee[ACCESS_INDEX]))
                        }
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveToFile(String filename) {
    }

    public void addMentor(Mentor mentor) {
        mentorList.add(mentor);
    }

    public void removeMentor(Mentor mentor) {
        mentorList.remove(mentor);
    }

    public void editMentor(Mentor mentor) {

    }

    public List<Mentor> getAllMentors() {
        return mentorList;
    }
}


