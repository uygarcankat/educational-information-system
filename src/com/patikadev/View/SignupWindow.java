package com.patikadev.View;

import com.patikadev.Helper.Helper;
import com.patikadev.model.User;

import javax.swing.*;
import java.awt.event.*;

public class SignupWindow extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_signUp;
    private JTextField fld_UserName;
    private JLabel lbl_userName;
    private JLabel lbl_pass;
    private JTextField fld_pass;
    private JButton btn_registration;
    private JTextField fld_name;
    private JTextField textField2;
    private JLabel lbl_name;
    private JLabel fld_type;


    public SignupWindow(){

        add(wrapper);
        setSize(1000,500);
        int x= Helper.screenCenterPoint("x",getSize());
        int y= Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


        btn_registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String uname=fld_UserName.getText();
                String pass=fld_pass.getText();
                String name=fld_name.getText();
                String type=fld_type.getText();
                if(fld_type.getText().equals("student")) {

                    if (User.add(name, uname, pass, type)) {
                        Helper.showMsg("done");
                    }

                }

                else if(fld_type.getText().equals("educator")){


                    Helper.showMsg("Eğitmenleri sadece operatörler ekleyebilir");

                    addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                            if(Helper.signOp()){
                                SignUpOPAndEdu op=new SignUpOPAndEdu("educator");
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {


                        }
                    });





                }

                else if(fld_type.getText().equals("operator")){

                    Helper.showMsg("Operatorler sadece yazılımcı tarafından eklenebilir");

                    addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                            if(Helper.signOp()){
                                SignUpOPAndEdu op=new SignUpOPAndEdu("developer");
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {



                        }
                    });


                }

            }






        });
    }
}
