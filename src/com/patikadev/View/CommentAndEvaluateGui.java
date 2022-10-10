package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.model.Comment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommentAndEvaluateGui extends JFrame{
    private JPanel wrapper;
    private JList lst_comment;
    private JTextArea txt_com_customer;
    private JLabel lbl_comment_customer;
    private JButton btn_add_com;
    private DefaultListModel mdl_comment;
    private int id;


    public CommentAndEvaluateGui(int id){
        this.id=id;
        add(wrapper);
        setSize(1000,500);
        int x= Helper.screenCenterPoint("x",getSize());
        int y= Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadModelComment();
        setVisible(true);


        mdl_comment=new DefaultListModel();
        lst_comment.setModel(mdl_comment);


        btn_add_com.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldEmpty(txt_com_customer)){

                    Helper.showMsg("fill");

                }
                else{
                    String comment= txt_com_customer.getText();
                    if(Comment.add(comment)){

                        Helper.showMsg("done");

                    }
                }
            }
        });
    }

    public void loadModelComment(){

      mdl_comment.removeAllElements();

      for(String obj:Comment.getList(id)){

          mdl_comment.addElement(obj);

      }
    }


    public static void main(String[] args) {
        CommentAndEvaluateGui c=new CommentAndEvaluateGui(4);
    }
}



