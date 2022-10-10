package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;
import com.patikadev.model.Patika;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdatePatikaGui extends JFrame {
    private JPanel wrapper;
    private JTextField fld_patika_name;
    private JButton btn_update;
    private Patika patika;

    public UpdatePatikaGui(Patika patika) {

        this.patika=patika;
        add(wrapper);
        setSize(300,150);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        fld_patika_name.setText(patika.getName());


        btn_update.addActionListener(e -> {

            if(Helper.isFieldEmpty(fld_patika_name)){

                Helper.showMsg("fill");
            }

            else{
                if(Patika.update(patika.getId(),fld_patika_name.getText())){

                    Helper.showMsg("done");


                }

                dispose();

            }

        });

    }

}
