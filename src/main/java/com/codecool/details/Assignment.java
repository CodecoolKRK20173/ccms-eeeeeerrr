package com.codecool.details;

public class Assignment {
    private final String NAME;
    private boolean isSubmitted;
    private int grade;

    public Assignment(String name){
        this.NAME = name;
        this.isSubmitted = false;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setSubmitted() {
        this.isSubmitted = true;
    }

    public String getNAME() {
        return NAME;
    }

    public boolean getIsSubmitted() {
        return this.isSubmitted;
    }
}
