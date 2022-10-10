package com.patikadev.View;

import com.patikadev.Helper.Helper;
import com.patikadev.model.Content;
import com.patikadev.model.Course;
import com.patikadev.model.Patika;
import com.patikadev.model.registration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseGui extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_course;
    private JTabbedPane lbl_registration;
    private JPanel pnl_registration;
    private JTable tbl_course_list;
    private JScrollPane scrl_reg_course_list;
    private JTable tbl_reg_course_list;
    private JPanel pnl_course_list;
    private JPanel wtop;
    private JTextField fld_course_name;
    private JLabel lbl_course_name;
    private JTextField fld_user_name;
    private JLabel lbl_user_uname;
    private JScrollPane scrl_course;
    private JButton btn_course_add;
    private DefaultTableModel mdl_course_list;
    private Object[]rowlist;
    private DefaultTableModel mdl_reg_list;
    private Object[] row_list_reg;
    private Patika patika;



    public CourseGui(Patika patika){

        this.patika=patika;
        add(wrapper);
        setSize(1000,500);
        int x= Helper.screenCenterPoint("x",getSize());
        int y= Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        mdl_course_list=new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==0){
                    return  false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[]column_list={"Ders Listesi"};
        rowlist=new Object[column_list.length];
        mdl_course_list.setColumnIdentifiers(column_list);
        loadModelCourseTable();
        tbl_course_list.setModel(mdl_course_list);



        Object[]column_reg_list={"id","öğrenci adı","ders adı"};
        row_list_reg=new Object[column_reg_list.length];
        mdl_reg_list.setColumnIdentifiers(column_reg_list);
        loadModelRegTable();
        tbl_reg_course_list.setModel(mdl_reg_list);




        JPopupMenu content=new JPopupMenu();
        JMenuItem contentMenu=new JMenuItem();
        content.add(contentMenu);




        contentMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String course=tbl_reg_course_list.getValueAt(tbl_reg_course_list.getSelectedRow(),2).toString();

                ContentGui content=new ContentGui(course);


            }
        });


        btn_course_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userName=fld_user_name.getText();
                String courseName=fld_course_name.getText();

                if(Helper.isFieldEmpty(fld_course_name)|| Helper.isFieldEmpty(fld_user_name)){

                    Helper.showMsg("fill");
                }

                else{

                    if(registration.add(userName,courseName)){

                        Helper.showMsg("done");
                    }

                }

            }
        });
    }

    public void loadModelCourseTable(){

        DefaultTableModel clearModel= (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);

        ArrayList<String>courselist=new ArrayList<>();

        int i;

        for(Course course: Course.getListByPatikaId(patika.getId())){

            i=0;

           rowlist[i++]=course.getName();
           mdl_course_list.addRow(rowlist);

        }
    }

    public void loadModelRegTable(){

        DefaultTableModel clearModel= (DefaultTableModel) tbl_reg_course_list.getModel();
        clearModel.setRowCount(0);


        int i;

        for(registration reg: registration.getList()){

            i=0;

            row_list_reg[i++]=reg.getId();
            row_list_reg[i++]=reg.getName();
            row_list_reg[i++]=reg.getCourse();
            mdl_reg_list.addRow(row_list_reg);

        }
    }








}
