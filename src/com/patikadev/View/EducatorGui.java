package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.model.Course;
import com.patikadev.model.Quiz;
import com.patikadev.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EducatorGui extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_title;
    private JPanel pnl_gnrl;
    private JScrollPane scrl_edu;
    private JTable tbl_edu;
    private JLabel lbl_edu;
    private JLabel lbl_quiz;
    private JTextField fld_quiz;
    private JLabel Content;
    private JComboBox cmb_content;
    private JButton btn_quiz_add;
    private JPopupMenu contentMenu;
    private DefaultTableModel mdl_edc_list;
    Object[]row_educator;
    String uname;


    public EducatorGui(String uname){
        this.uname=uname;
        add(wrapper);
        setSize(1000,500);
        int x= Helper.screenCenterPoint("x",getSize());
        int y= Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);



        contentMenu=new JPopupMenu();
        JMenuItem content=new JMenuItem("Course Content");
        contentMenu.add(content);


        content.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course= (String) tbl_edu.getValueAt(tbl_edu.getSelectedRow(),0);

                ContentGui contentGui =new ContentGui(course);




            }
        });


        mdl_edc_list=new DefaultTableModel(){
            @Override

            public boolean isCellEditable(int row, int column) {


                return false;
            }
        };

        Object[] edu_column={"Eğitimler","Patika"};
        mdl_edc_list.setColumnIdentifiers(edu_column);
        row_educator=new Object[edu_column.length];
        loadModelEdu();
        loadPatikaCombo();
        tbl_edu.setModel(mdl_edc_list);
        tbl_edu.setComponentPopupMenu(contentMenu);
        lbl_edu.setText("Hoşgeldiniz " + User.getFetch(this.uname).getName());
        tbl_edu.getTableHeader().setReorderingAllowed(false);


        tbl_edu.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Point point =e.getPoint();
                int selected_row= tbl_edu.rowAtPoint(point);
                tbl_edu.setRowSelectionInterval(selected_row,selected_row);

            }

        });





        btn_quiz_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(Helper.isFieldEmpty(fld_quiz)){

                    String quiz=fld_quiz.getText();
                    String content=cmb_content.getSelectedItem().toString();
                    Quiz.add(quiz,content);
                    loadModelEdu();
                    loadPatikaCombo();
                    Helper.showMsg("done");
                }

                else{
                    Helper.showMsg("fill");
                }
            }
        });

    }




    public void loadModelEdu(){

        int id= User.getFetch(this.uname).getId();
        int i;
        for(Course obj:Course.getList()){
            if(obj.getUser_id()==id) {

                i=0;
                row_educator[i++] = obj.getName();
                row_educator[i++] = obj.getLang();
                mdl_edc_list.addRow(row_educator);
            }
        }
    }

    private void loadPatikaCombo() {

        cmb_content.removeAllItems();

        for(Quiz obj:Quiz.getListComboBox()){

            cmb_content.addItem(new Item(obj.getId(),obj.getTitle()));
        }

    }




    public static void main(String[] args) {

        Helper.setLayout();
        String uname="uygarr";
        EducatorGui ed=new EducatorGui(uname);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
