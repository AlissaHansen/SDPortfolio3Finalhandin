package com.company;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        String url = "jdbc:sqlite:C:/Users/Bandit/Desktop/RUC/Kurser/SD/SDPortfolio3Finalhandin/StudentDatabase.db";
        StudentModel SDB = new StudentModel(url);
        StudentController control = new StudentController(SDB);
        StudentView view = new StudentView(control);
        control.setView(view);
        primaryStage.setTitle("Student School Information");
        primaryStage.setScene(new Scene(view.asParent(), 600, 475));
        primaryStage.show();

    }
    public static void main(String[] args){
        launch(args);
    }


}

   /* public static void main(String[] args) {
	// write your code here
    }
} */
