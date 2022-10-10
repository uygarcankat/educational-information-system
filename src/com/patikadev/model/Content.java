package com.patikadev.model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {

    private int id;
    private String title;
    private String explanation;
    private String link;
    private String course;

    public Content(int id,String title, String explanation, String link, String course) {
        this.id = id;
        this.title = title;
        this.explanation = explanation;
        this.link = link;
        this.course = course;
    }

    public Content() {

    }


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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public static ArrayList<Content> getList(String course){

        ArrayList<Content>contList=new ArrayList<>();
        Content obj;
        try {
            String query = "SELECT * FROM content WHERE Course=?";
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, course);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){

                obj=new Content(rs.getInt("id"),rs.getString("title"),
                        rs.getString("Explanation"),rs.getString("link"),
                        rs.getString("Course"));

               contList.add(obj);
            }
        }

        catch(Exception e){
            e.getMessage();
        }

        return contList;



    }



    public static ArrayList<Content> getListCont(){

        ArrayList<Content>contList=new ArrayList<>();
        Content obj;
        try {
            String query = "SELECT * FROM content" ;
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){

                obj=new Content(rs.getInt("id"),rs.getString("title"),
                        rs.getString("Explanation"),rs.getString("link"),
                        rs.getString("Course"));

                contList.add(obj);
            }
        }

        catch(Exception e){
            e.getMessage();
        }

        return contList;



    }

     public static boolean add(String TITLE, String EXPLANATION, String LINK, String COURSE){

        String query ="INSERT INTO content (title,Explanation,link,Course)  VALUES (?,?,?,?)";


        try{
            PreparedStatement pr=DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,TITLE);
            pr.setString(2,EXPLANATION);
            pr.setString(3,LINK);
            pr.setString(4,COURSE);
            int response=pr.executeUpdate();
            if(response==-1){
                Helper.showMsg("error");
            }

            return response !=-1;

        }
        catch(Exception e){
            e.getMessage();
        }


        return true;
     }


     public static boolean delete(int id){

        String query="DELETE FROM content WHERE id =?";

        try{
        PreparedStatement pr= DBConnector.getInstance().prepareStatement(query);
        pr.setInt(1,id);

        return pr.executeUpdate()!=-1;
        }

        catch(Exception e){

            e.getMessage();
        }

        return true;

     }
    public static String searchQuery(String title,String course){

        String query="SELECT * FROM user WHERE title LIKE '%{{title}}%' AND course LIKE '%{{course}}%'";
        query=query.replace("{{title}}",title);
        query=query.replace("{{course}}",course);


        return query;


    }


    public static ArrayList<Content>searchlist(String query){

        ArrayList<Content>searchlist=new ArrayList<>();
        Content obj;

        try{
       Statement st= DBConnector.getInstance().createStatement();
       ResultSet rs =st.executeQuery(query);
       while(rs.next()){

           obj=new Content(rs.getInt("id"),rs.getString("title"),
                   rs.getString("Explanation"),rs.getString("link"),rs.getString("Course"));

           searchlist.add(obj);


       }
        }

        catch(Exception e){
            e.getMessage();
        }

        return searchlist;
    }


    public static boolean Update(int id,String title,String Explanation,String link,String course){

        String query="UPDATE content SET title=?,Explanation=?, link=?,Course=? WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            pr.setString(2, Explanation);
            pr.setString(3, link);
            pr.setString(4, course);
            pr.setInt(5, id);

            return pr.executeUpdate()!=-1;

        }

        catch(Exception e){

            e.getMessage();

        }

        return true;


    }


}
