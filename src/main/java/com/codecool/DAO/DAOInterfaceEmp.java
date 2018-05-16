package com.codecool.DAO;

import com.codecool.person.Mentor;

import java.util.List;

public interface DAOInterfaceEmp {
    void addMentor(Mentor mentor);
    void removeMentor(Mentor mentor);
    void editMentor(Mentor mentor);
    List<Mentor> getAllMentors();
}
