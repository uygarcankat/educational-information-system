package com.patikadev.model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Quizsolve {
    private int id;
    private String title;
    private String Solve;

    public Quizsolve(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSolve() {
        return Solve;
    }

    public void setSolve(String solve) {
        Solve = solve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static ArrayList<String> getList(String title){


        ArrayList<String> solveList = new ArrayList<>();

        try {
            String query="SELECT solve FROM QuizSolve WHERE title=? ";
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {

                String solve=rs.getString("solve");
                solveList.add(solve);


            }
        }
        catch(Exception e){
            e.getMessage();
        }

        return solveList;
    }



    public static boolean add(String title,String solve){


        String query="INSERT INTO QuizSolve (title,solve) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            pr.setString(2, solve);
            return pr.executeUpdate()!=-1;
        }

        catch(Exception e){

            e.getMessage();
        }

        return true;

    }

}
