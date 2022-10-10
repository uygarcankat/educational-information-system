package com.patikadev.model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Quiz {

    private int id;
    private String title;
    private String Quiz;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuiz() {
        return Quiz;
    }

    public void setQuiz(String quiz) {
        Quiz = quiz;
    }



    public static ArrayList<Quiz> getListByTitle(String title){


        ArrayList<Quiz> QuizList=new ArrayList<>();
        Quiz obj;
        try {
            String query="SELECT * FROM Quiz WHERE title=?";
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,title);
            ResultSet rs=pr.executeQuery();

            while(rs.next()){

                obj=new Quiz();
                obj.setId(rs.getInt("id"));
                obj.setTitle(rs.getString("title"));
                obj.setQuiz(rs.getString("Quiz"));
                QuizList.add(obj);

            }
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return QuizList;

    }


    public static boolean add (String quiz,String title){

        String query="INSERT INTO QUIZ (Quiz,title) VALUES (?,?)";
        try{
        PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
        pr.setString(1,quiz);
        pr.setString(2,title);

        return pr.executeUpdate()!=-1;}

        catch(Exception e){
            e.getMessage();
        }

        return true;
    }
    public static ArrayList<Quiz> getListComboBox (){

        String query="SELECT DISTINCT title,id from Quiz";
        Quiz obj;
        ArrayList<Quiz> Quizlist=new ArrayList<>();

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){

                obj=new Quiz();
                obj.setId(rs.getInt("id"));
                obj.setTitle(rs.getString("title"));
                Quizlist.add(obj);


            }
        }

        catch(Exception e){
            e.getMessage();
        }

        return Quizlist;
    }



    public static ArrayList<Quiz> getList(){


        ArrayList<Quiz> QuizList=new ArrayList<>();
        Quiz obj;
        try {
            String query="SELECT * FROM Quiz";
           Statement st=DBConnector.getInstance().createStatement();

            ResultSet rs=st.executeQuery(query);

            while(rs.next()){

                obj=new Quiz();
                obj.setId(rs.getInt("id"));
                obj.setTitle(rs.getString("title"));
                obj.setQuiz(rs.getString("Quiz"));
                QuizList.add(obj);

            }
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return QuizList;

    }

    public static boolean delete(int id ){

        String query="DELETE FROM Quiz WHERE id= ?";


        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            return pr.executeUpdate()!=-1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }


    public static boolean Update(int id,String title,String quiz){

        String query="UPDATE content SET id=?,title=?, quiz=? WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            pr.setString(2, title);
            pr.setString(3, quiz);


            return pr.executeUpdate()!=-1;

        }

        catch(Exception e){

            e.getMessage();

        }

        return true;


    }


}
