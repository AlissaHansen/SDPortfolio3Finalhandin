package com.company;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentModel model;
    StudentView view;

    public StudentController (StudentModel model){
        this.model = model;
        try {
            model.connect();
            model.CreateStatement();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void setView(StudentView view) {
        this.view = view;
        view.exitButton.setOnAction(e-> Platform.exit());
        EventHandler<ActionEvent> PrintStudentInfo = e->HandlePrintStudentInfo(view.StudentIDCombo.getValue(),view.InfoText);
        EventHandler<ActionEvent> PrintCourseInfo = e-> HandlePrintCourseInfo(view.CourseCombo.getValue(),view.InfoText);
        view.FindStudentInfoButton.setOnAction(PrintStudentInfo);
        view.FindCourseInfoButton.setOnAction(PrintCourseInfo);
    }

    public ObservableList<Integer> getStudentId(){
        ArrayList<Integer> Ids = model.SQLQueryStudentNumbers();
        ObservableList<Integer> StudentIDs = FXCollections.observableList(Ids);
        return StudentIDs;
    }

    public void HandlePrintStudentInfo (int StudentId, TextArea txtArea){
        txtArea.clear();
        model.PreparedStmtStudentQuery();

        ArrayList<StudentInfo> Info = model.FindStudentInfo(StudentId);
        for (int i = 0; i < Info.size(); i++){
            int StudentNumber = Info.get(i).StudentNo;
            String FirstName = Info.get(i).FirstName;
            String LastName = Info.get(i).LastName;
            int gradeSD = Info.get(i).gradeSD;
            int gradeES = Info.get(i).gradeES;
            txtArea.appendText("Student with studentNo " + StudentNumber + ": \n" + FirstName + " "
                    + LastName + " Recieved the grade: " + gradeSD + " in SD" + " and the grade: " + gradeES + " in ES");

        }

    }
    public ObservableList<String> getCourseName(){
        ArrayList<String > courses = model.SQLQueryCourseNames();
        ObservableList<String> courseNames = FXCollections.observableList(courses);
        return courseNames;
    }
    public void HandlePrintCourseInfo (String Name, TextArea txtArea){
        txtArea.clear();
        model.PreparedStmtCourseQuery();

        ArrayList<CourseInfo> InfoCourses = model.FindCourseInfo(Name);
        for (int i = 0; i < InfoCourses.size(); i++){
            String CourseName = InfoCourses.get(i).CourseName;
            String Teacher = InfoCourses.get(i).Teacher;
            Double Average = InfoCourses.get(i).Average;
            txtArea.appendText("The course: " + CourseName + " has the teacher: " + Teacher + " and the average grade is: " + Average);

        }

    }
}
