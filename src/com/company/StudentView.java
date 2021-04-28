package com.company;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class StudentView {
    StudentController control;
    private GridPane StartView;
    Button exitButton = new Button("Exit");
    Button FindStudentInfoButton = new Button("Find Student Info");
    Button FindCourseInfoButton = new Button("Find course");
    Button InsertSDGrade = new Button("Insert SD Grade");
    Label StudentLabel = new Label("Select StudentID:");
    Label CourseLabel = new Label("Select course:");
    Label insertGradeLabel = new Label ("Select grade for this student:");
    TextArea InfoText = new TextArea();

    ComboBox<Integer> StudentIDCombo = new ComboBox<Integer>();
    ComboBox<String> CourseCombo = new ComboBox<String>();
    ComboBox<Integer> InsertGradeCombo = new ComboBox<>();


    public StudentView(StudentController control){
        this.control = control;
        CreateAndConfigure();
    }
   private void CreateAndConfigure(){
        StartView = new GridPane();
        StartView.setMinSize(300,200);
        StartView.setPadding(new Insets(10, 10, 10, 10));
        StartView.setVgap(5);
        StartView.setHgap(1);

        StartView.add(StudentLabel,1,1);
        StartView.add(StudentIDCombo, 2, 1);
        StartView.add(FindStudentInfoButton, 1, 2);

        StartView.add(CourseLabel, 1,5);
        StartView.add(CourseCombo, 2, 5);
        StartView.add(FindCourseInfoButton,1, 6);

        StartView.add(insertGradeLabel, 4, 1);
        StartView.add(InsertGradeCombo, 5, 1);
        StartView.add(InsertSDGrade, 4, 2);

        StartView.add(InfoText, 1,7,15,7);

        StartView.add(exitButton,20,15);

        ObservableList<Integer> studentID = control.getStudentId();
        StudentIDCombo.setItems(studentID);
        StudentIDCombo.getSelectionModel().selectFirst();

        ObservableList<String> courseName = control.getCourseName();
        CourseCombo.setItems(courseName);
        CourseCombo.getSelectionModel().selectFirst();

        ObservableList<Integer> Grades = control.insertGrades();
        InsertGradeCombo.setItems(Grades);
        InsertGradeCombo.getSelectionModel().selectFirst();

   }

   public Parent asParent(){
        return StartView;
   }
}
