package com.patikadev.View;

import com.patikadev.Helper.Helper;
import com.patikadev.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalRegistration extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_reg;
    private JTextField fld_uname;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel pnl_uname;
    private JLabel fld_name;
    private JLabel fld_pass;
    private JTextField textField4;
    private JLabel fld_type;
    private JButton btn_reg;

    public FinalRegistration(){

        add(wrapper);
        setSize(1000,500);
        int x= Helper.screenCenterPoint("x",getSize());
        int y= Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        String name=fld_name.getText();
        String uname=fld_uname.getText();
        String pass= fld_pass.getText();
        String type=fld_pass.getText();


        btn_reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(User.add(name,uname,pass,type)){

                    Helper.showMsg("done");
                }
            }
        });
    }
}
