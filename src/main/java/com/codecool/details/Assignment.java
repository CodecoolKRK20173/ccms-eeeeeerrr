package com.codecool.details;

public class Assignment {

    private final String NAME;
    private boolean isSubmitted;
    private Integer grade;

    public Assignment(String name){
        this.NAME = name;
        this.isSubmitted = false;
        this.grade = null;
    }

    public Assignment(String name, boolean isSubmitted) {
        this.NAME = name;
        this.isSubmitted = isSubmitted;
        this.grade = null;
    }

    public Assignment(String name, boolean isSubmitted, Integer grade) {
        this.NAME = name;
        this.isSubmitted = isSubmitted;
        this.grade = grade;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setSubmitted(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public String getNAME() {
        return NAME;
    }

    public boolean getIsSubmitted() {
        return this.isSubmitted;
    }
}
