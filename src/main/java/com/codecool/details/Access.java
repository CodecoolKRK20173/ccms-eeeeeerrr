package com.codecool.details;

import java.util.ArrayList;
import java.util.List;

public enum Access {
    STUDENT {
        @Override
        public List<Privilege> initPrivileges() {
            List<Privilege> privileges = new ArrayList<>();

            privileges.add(Privilege.EXIT);
            privileges.add(Privilege.SUBMIT_ASSIGNMENT);
            privileges.add(Privilege.GET_GRADES);
            privileges.add(Privilege.LOG_OUT);

            return privileges;
        }
    },

    MANAGER {
        @Override
        public List<Privilege> initPrivileges() {
            List<Privilege> privileges = new ArrayList<>();

            privileges.add(Privilege.EXIT);
            privileges.add(Privilege.ADD_MENTOR);
            privileges.add(Privilege.REMOVE_MENTOR);
            privileges.add(Privilege.EDIT_MENTOR);
            privileges.add(Privilege.GET_ALL_MENTORS);
            privileges.add(Privilege.GET_ALL_STUDENTS);
            privileges.add(Privilege.LOG_OUT);

            return privileges;
        }
    },

    REGULAR_EMPLOYEE {
        @Override
        public List<Privilege> initPrivileges() {
            List<Privilege> privileges = new ArrayList<>();

            privileges.add(Privilege.EXIT);
            privileges.add(Privilege.GET_ALL_STUDENTS);
            privileges.add(Privilege.LOG_OUT);

            return privileges;
        }
    },

    MENTOR {
        @Override
        public List<Privilege> initPrivileges() {
            List<Privilege> privileges = new ArrayList<>();

            privileges.add(Privilege.EXIT);
            privileges.add(Privilege.GET_ALL_STUDENTS);
            privileges.add(Privilege.ADD_ASSIGNMENT);
            privileges.add(Privilege.GRADE_ASSIGNMENT);
            privileges.add(Privilege.CHECK_ATTENDANCE);
            privileges.add(Privilege.ADD_STUDENT);
            privileges.add(Privilege.REMOVE_STUDENT);
            privileges.add(Privilege.EDIT_STUDENT);
            privileges.add(Privilege.LOG_OUT);

            return privileges;
        }
    };

    private List<Privilege> privileges = initPrivileges();
    public abstract List<Privilege> initPrivileges();
    public List<Privilege> getPrivileges() {
        return privileges;
    }
}
