package com.patikadev.model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class registration {

    private int id;
    private String name;
    private String course;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }



    public static ArrayList<registration> getList(){

        String query="SELECT * FROM registration";
        registration reg;
        ArrayList<registration>regList=new ArrayList<>();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                reg=new registration();
                reg.setId(rs.getInt("id"));
                reg.setName(rs.getString("name"));
                reg.setCourse(rs.getString("course"));
                regList.add(reg);
            }



        }
        catch(Exception e){
            e.getMessage();
        }

        return regList;

    }

    public static boolean add (String name,String course){

        String query="INSERT INTO registration (name,course) VALUES (?,?)";
        try{
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,course);

            return pr.executeUpdate()!=-1;}

        catch(Exception e){
            e.getMessage();
        }

        return true;
    }
}
