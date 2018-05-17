package com.codecool.DAO;

import com.codecool.person.CodecoolPerson;
import com.codecool.person.Manager;
import com.codecool.person.Mentor;
import com.codecool.person.RegularEmployee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CodecoolDAOEmployee implements DAOInterfaceEmp {
    private String file = "src/main/resources/employee.csv";
    private List<Mentor> mentorList;
    private List<Manager> managerList;
    private List<RegularEmployee> regularEmployeeList;

    public CodecoolDAOEmployee() {
        this.mentorList = new ArrayList<>();
        this.managerList = new ArrayList<>();
        this.regularEmployeeList = new ArrayList<>();
        readFile();
    }

    public List<CodecoolPerson> getAllEmployees() {
        List<CodecoolPerson> employeeList = new ArrayList<>();
        employeeList.addAll(mentorList);
        employeeList.addAll(managerList);
        employeeList.addAll(regularEmployeeList);
        return employeeList;
    }

    public void readFile() {
        final int NAME_INDEX = 0;
        final int SURNAME_INDEX = 1;
        final int LOGIN_INDEX = 2;
        final int PASSWORD_INDEX = 3;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                String[] myEmloyee = line.split(",");
                if (myEmloyee[4].equals("MANAGER")) {
                    managerList.add(new Manager(myEmloyee[NAME_INDEX], myEmloyee[SURNAME_INDEX], myEmloyee[LOGIN_INDEX], myEmloyee[PASSWORD_INDEX]));
                } else {
                    if (myEmloyee[4].equals("MENTOR")) {
                        mentorList.add(new Mentor(myEmloyee[NAME_INDEX], myEmloyee[SURNAME_INDEX], myEmloyee[LOGIN_INDEX], myEmloyee[PASSWORD_INDEX]));
                    } else {
                        if (myEmloyee[4].equals("REGULAR_EMPLOYEE")) {
                            regularEmployeeList.add(new RegularEmployee(myEmloyee[NAME_INDEX], myEmloyee[SURNAME_INDEX], myEmloyee[LOGIN_INDEX], myEmloyee[PASSWORD_INDEX]));
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


    public void saveToFile() {
        try (FileWriter fw = new FileWriter(this.file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            for(CodecoolPerson c : getAllEmployees()) {
                out.printf("%s,%s,%s,%s,%s\n", c.getName(), c.getSurName(), c.getLogin(), c.getPassword(), c.getAccess().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMentor(Mentor mentor) {
        mentorList.add(mentor);
    }

    public void removeMentor(Mentor mentor) {
        mentorList.remove(mentor);
    }

    public List<Mentor> getAllMentors() {
        return mentorList;
    }

}


