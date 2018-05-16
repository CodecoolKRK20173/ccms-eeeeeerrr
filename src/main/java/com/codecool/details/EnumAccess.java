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

            return null;
        }
    },
    MENTOR {
        @Override
        public List<EnumPrivileges> getPrivileges() {

            return null;
        }
    };
    public abstract List<EnumPrivileges> getPrivileges();
}
