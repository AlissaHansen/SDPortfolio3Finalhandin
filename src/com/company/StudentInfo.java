package com.company;

public class StudentInfo {
    String FirstName;
    String LastName;
    int StudentNo;
    int gradeES;
    int gradeSD;
    Double Average;

    public StudentInfo(int StudentNo, String FirstName, String LastName, int gradeES, int gradeSD, double Average) {
        this.StudentNo = StudentNo;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.gradeES = gradeES;
        this.gradeSD = gradeSD;
        this.Average = Average;
    }
}
