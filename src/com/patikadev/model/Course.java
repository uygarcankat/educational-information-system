package com.patikadev.model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.View.LoginGui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int user_id;
    private int patika_id;
    private String name;
    private String lang;
    private Patika patika;
    private User educator;



    public Course(int id, int user_id, int patika_id, String name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.lang = lang;
        this.patika=Patika.getFetch(patika_id);
        this.educator=User.getFetch(user_id);
    }


    public Course( String name) {

        this.name = name;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }


    public static ArrayList<Course> getList(){
        ArrayList<Course>course_list=new ArrayList<>();

        String query="SELECT * FROM course";
        Course obj;



        try {
            Statement st=DBConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){

                obj=new Course(rs.getInt("id"),rs.getInt("user_id"),
                        rs.getInt("patika_id"),rs.getString("name"),rs.getString("lang"));

                course_list.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course_list;


    }

    public static boolean add(int user_id,int patika_id,String name,String lang){

        String query="INSERT INTO course(user_id,patika_id,name,lang) VALUES (?,?,?,?)" ;


        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,user_id);
            pr.setInt(2,patika_id);
            pr.setString(3,name);
            pr.setString(4,lang);
            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    public static ArrayList<Course> getListByUser(int user_id){
        ArrayList<Course>course_list=new ArrayList<>();

        String query="SELECT * FROM course WHERE user_id= " + user_id;
        Course obj;



        try {
            Statement st=DBConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){

                obj=new Course(rs.getInt("id"),rs.getInt("user_id"),
                        rs.getInt("patika_id"),rs.getString("name"),rs.getString("lang"));

                course_list.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course_list;


    }

    public static boolean delete(int id ){

        String query="DELETE FROM course WHERE id= ?";


        try {
            PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            return pr.executeUpdate()!=-1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    public static ArrayList<Course> getListByPatikaId(int patika_id){
        ArrayList<Course>course_list=new ArrayList<>();

        String query="SELECT * FROM course WHERE patika_id= " + patika_id;
        Course obj;



        try {
            Statement st=DBConnector.getInstance().createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){

                obj=new Course(rs.getInt("id"),rs.getInt("user_id"),
                        rs.getInt("patika_id"),rs.getString("name"),rs.getString("lang"));

                course_list.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course_list;


    }


    public static boolean update(int id, int user_id, int patika_id, String name, String lang){

        String query="UPDATE course SET user_id=?, patika_id=?,name=?,lang=? WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, user_id);
            pr.setInt(2, patika_id);
            pr.setString(3, name);
            pr.setString(4, lang);

            return pr.executeUpdate()!=-1;
        }
        catch(Exception e){
            e.getMessage();
        }

        return true;
    }

}
