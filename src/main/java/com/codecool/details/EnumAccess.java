package com.codecool.details;

import java.util.List;

public enum EnumAccess {
    STUDENT {
        @Override
        public List<EnumPrivileges> getPrivileges() {

            return null;
        }
    },
    MANAGER {
        @Override
        public List<EnumPrivileges> getPrivileges() {

            return null;
        }
    },
    EMPLOYEE {
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
