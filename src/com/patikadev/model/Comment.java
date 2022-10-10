package com.patikadev.model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Comment {
    private int id;
    private String comment;


    public Comment(int id) {
        this.id = id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public static ArrayList<String>getList(int id){

        String query="SELECT comment from Comment WHERE id=?";
        ArrayList<String>commentList=new ArrayList<>();
        Comment comment;
        try{
        PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
        pr.setInt(1,id);
        ResultSet rs=pr.executeQuery();
        while(rs.next()){

            String name=rs.getString("comment");
            commentList.add(name);

          }
        }


        catch(Exception e){

            e.getMessage();
        }

        return commentList;


    }


    public static boolean add(String comment){

        String query="INSERT INTO Comment (comment) VALUES (?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, comment);
            return pr.executeUpdate()!=-1;
        }

        catch(Exception e){
            e.getMessage();
        }

        return true;

    }
}
