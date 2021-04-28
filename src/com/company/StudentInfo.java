package com.company;

public class StudentInfo {
    String FirstName;
    String LastName;
    int StudentNo;
    int gradeES;
    int gradeSD;

    public StudentInfo(int StudentNo, String FirstName, String LastName, int gradeES, int gradeSD) {
        this.StudentNo = StudentNo;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.gradeES = gradeES;
        this.gradeSD = gradeSD;
    }
}
