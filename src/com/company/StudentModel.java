package com.company;

import java.sql.*;
import java.util.ArrayList;

public class StudentModel {
    Connection conn = null;
    String url;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    StudentModel(String url) {
        this.url = url;
    }

    public void connect() throws SQLException {
        conn = DriverManager.getConnection(this.url);
    }

    public void close() throws SQLException {
        if (conn != null)
            conn.close();
    }

    public void CreateStatement() throws SQLException {
        this.stmt = conn.createStatement();
    }

    // Students

    public ArrayList<Integer> SQLQueryStudentNumbers() {
        ArrayList<Integer> StudentNumbers = new ArrayList();
        String sql = "Select StudentNo From Students";

        try {
            rs = stmt.executeQuery(sql);

            while(rs != null && rs.next()) {
                int StudentNo = rs.getInt(1);
                StudentNumbers.add(StudentNo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        rs = null;
        return StudentNumbers;
    }
    public void PreparedStmtStudentQuery() {
        String sql = "Select Students.StudentNo, FirstName, LastName, GradeES, GradeSD  FROM Students" +
                " INNER JOIN GRADES" +
                " ON Students.StudentNo = Grades.StudentNo WHERE Students.StudentNo =?;";

        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
    public ArrayList<StudentInfo> FindStudentInfo(int StudentNo) {
        ArrayList<StudentInfo> StudentInformationList = new ArrayList<>();

        try {
            pstmt.setInt(1, StudentNo);

            rs = pstmt.executeQuery();
            if (rs == null)
                System.out.println("No records fetched.");
            while(rs != null && rs.next()) {
                StudentInformationList.add(new StudentInfo(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return StudentInformationList;
    }

    // Courses
    public ArrayList<String> SQLQueryCourseNames() {
        ArrayList<String> CourseNames = new ArrayList();
        String sql = "Select Name From Courses";

        try {
            rs = stmt.executeQuery(sql);

            while(rs != null && rs.next()) {
                String Name = rs.getString(1);
                CourseNames.add(Name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        rs = null;
        return CourseNames;
    }
    public void PreparedStmtCourseQuery() {
        String sql = "Select Name, Teacher, Average FROM Courses WHERE Courses.Name =?;";

        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<CourseInfo> FindCourseInfo(String Name) {
        ArrayList<CourseInfo> CourseInformationList = new ArrayList<>();

        try {
            pstmt.setString(1, Name);

            rs = pstmt.executeQuery();
            if (rs == null)
                System.out.println("No records fetched.");
            while(rs != null && rs.next()) {
                CourseInformationList.add(new CourseInfo(rs.getString(1),
                        rs.getString(2),rs.getDouble(3)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return CourseInformationList;
    }
}
