package com.patikadev.View;

import com.patikadev.Helper.Helper;
import com.patikadev.model.Course;
import com.patikadev.model.Patika;
import com.patikadev.model.User;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentGui extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_student;
    private JTabbedPane tbd_patika;
    private JScrollPane scrl_student;
    private JTable tbl_pat_stud;
    private User user;
    private DefaultTableModel mdl_student;
    private Object[]rowList;




    public StudentGui(User user){

        this.user=user;
        add(wrapper);
        setSize(1000,500);
        int x= Helper.screenCenterPoint("x",getSize());
        int y= Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        lbl_student.setText("Öğrenci ekranına Hoşgeldiniz " + user.getName());

      mdl_student=new DefaultTableModel(){
          @Override
          public boolean isCellEditable(int row, int column) {
              if(column==0){
                  return  false;
              }
              return super.isCellEditable(row, column);
          }
      };

      Object[]columnList={"id","Patika Listesi"};
      rowList=new Object[columnList.length];
      mdl_student.setColumnIdentifiers(columnList);
      loadModelList();
      tbl_pat_stud.setModel(mdl_student);


      JPopupMenu courseMenu=new JPopupMenu();
      JMenuItem courseList=new JMenuItem("Ders Listesi");
      courseMenu.add(courseList);

        courseList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String patika=tbl_pat_stud.getValueAt(tbl_pat_stud.getSelectedRow(),1).toString();
                Patika ptk=null;
                for(Patika obj:Patika.getlist()){
                    if(obj.getName().equals(patika)){
                        ptk.setName(obj.getName());
                        ptk.setId(obj.getId());
                        break;
                    }
                };

                CourseGui courseGui=new CourseGui(ptk);

            }
        });




    }




    public void loadModelList(){

        DefaultTableModel clearModel= (DefaultTableModel) tbl_pat_stud.getModel();
        clearModel.setRowCount(0);
        int i;

        for(Patika obj:Patika.getlist()){
            i=0;
            rowList[i++]=obj.getId();
            rowList[i++]=obj.getName();
            mdl_student.addRow(rowList);

        }

    }

    public static void main(String[] args) {
        Helper.setLayout();
        User user=new User();
        user.setName("uygar cankat");
        user.setId(2);
        user.setPass("1");
        user.setUname("u");
        user.setType("educator");

        StudentGui st=new StudentGui(user);
    }

}
