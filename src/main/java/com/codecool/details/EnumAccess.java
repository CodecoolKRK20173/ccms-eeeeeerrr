package com.codecool.details;

import java.util.List;

public enum EnumAccess {
    STUDENT {
        @Override
        public List<EnumPrivileges> getPrivileges() {
            List<EnumPrivileges> privileges = new ArrayList<>();
            privileges.add(EnumPrivileges.SUBMIT_ASSIGMENT);
            privileges.add(EnumPrivileges.GET_GRADES);

            return privileges;
        }
    },
    MANAGER {
        @Override
        public List<EnumPrivileges> getPrivileges() {

            List<EnumPrivileges> privileges = new ArrayList<>();
            privileges.add(EnumPrivileges.ADD_MENTOR);
            privileges.add(EnumPrivileges.REMOVE_MENTOR);
            privileges.add(EnumPrivileges.EDIT_MENTOR);
            privileges.add(EnumPrivileges.GET_ALL_MENTORS);
            privileges.add(EnumPrivileges.GET_ALL_STUDENTS);

            return privileges;
        }
    },
    REGULAR_EMPLOYEE {
        @Override
        public List<EnumPrivileges> getPrivileges() {
            List<EnumPrivileges> privileges = new ArrayList<>();

            privileges.add(EnumPrivileges.GET_ALL_STUDENTS);

            return privileges;
        }
    },
    MENTOR {
        @Override
        public List<EnumPrivileges> getPrivileges() {
            List<EnumPrivileges> privileges = new ArrayList<>();

            privileges.add(EnumPrivileges.GET_ALL_STUDENTS);
            privileges.add(EnumPrivileges.ADD_ASSIGNMENT);
            privileges.add(EnumPrivileges.GRADE_ASSIGNMENT);
            privileges.add(EnumPrivileges.CHECK_ATTENDANCE);
            privileges.add(EnumPrivileges.ADD_STUDENT);
            privileges.add(EnumPrivileges.REMOVE_STUDENT);
            privileges.add(EnumPrivileges.EDIT_STUDENT);

            return privileges;
        }
    };
    public abstract List<EnumPrivileges> getPrivileges();
}
