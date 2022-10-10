package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpOPAndEdu extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_sign;
    private JTextField fld_uname;
    private JTextField fld_pass;
    private JLabel lbl_username;
    private JLabel lbl_pass;
    private JButton btn_signupeduop;
    private String type;




    public SignUpOPAndEdu(String type){

        this.type=type;
        add(wrapper);
        setSize(1000,500);
        int x= Helper.screenCenterPoint("x",getSize());
        int y= Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        String uname=fld_uname.getText();
        String pass=fld_pass.getText();




        btn_signupeduop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(User.getFetch(uname,pass,type)!=null){

                    FinalRegistration reg=new FinalRegistration();


                }



            }
        });
    }


}
