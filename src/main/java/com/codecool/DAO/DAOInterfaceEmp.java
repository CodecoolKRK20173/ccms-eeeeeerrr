package com.codecool.DAO;

import com.codecool.person.Mentor;

import java.util.List;

public interface DAOInterfaceEmp {
    void addMentor(Mentor mentor);
    void removeMentor(Mentor mentor);
    List<Mentor> getAllMentors();
}
