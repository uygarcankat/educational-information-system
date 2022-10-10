package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.model.Operator;
import com.patikadev.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGui extends JFrame {
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_pass;
    private JButton btn_login;
    private JButton btn_signUp;
    private JLabel lbl_üye_ol;
    private JLabel lbl_yokMu;
    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public LoginGui(){

        add(wrapper);
        setSize(400,400);
        int x= Helper.screenCenterPoint("x",getSize());
        int y= Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





                    if (Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)) {

                        Helper.showMsg("fill");
                    } else {
                        User u = User.getFetch(fld_user_uname.getText(), fld_user_pass.getText());

                        if (u == null) {
                            Helper.showMsg("Kullanıcı bulunamadı!");

                        } else {
                            switch (u.getType()) {

                                case "operator":

                                    OperatorGui opr = new OperatorGui((Operator) u);

                                    break;
                                case "educator":

                                    uname = fld_user_uname.getText();

                                    EducatorGui edc = new EducatorGui(uname);
                                    break;
                                case "student":

                                    uname = fld_user_uname.getText();
                                    User user = null;
                                    for (User obj : User.getlist()) {
                                        if (obj.getUname().equals(uname)) {
                                            user = obj;
                                            break;
                                        }
                                    }
                                    StudentGui std = new StudentGui(user);
                                    break;
                            }

                            dispose();
                        }

                    }

            }
        });


        btn_signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SignupWindow signupWindow=new SignupWindow();


            }
        });

    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGui lg= new LoginGui();

    }




}
