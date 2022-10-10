package com.patikadev.View;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.patikadev.Helper.*;
import com.patikadev.model.*;


import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class OperatorGui extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JLabel lbl_welcome;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JTextField fld_user_name;
    private JTextField fld_uname;
    private JTextField fld_user_pass;
    private JButton btn_user_add;
    private JComboBox cmb_user_type;
    private JButton btn_user_delete;
    private JTextField fld_user_id;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_uname;
    private JComboBox cmb_sh_user_type;
    private JButton bth_user_sh;
    private JPanel pnl_patika_list;
    private JTable tbl_patika_list;
    private JScrollPane scrl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_user_top;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;
    private JTextField fld_deletion_id;
    private JButton btn_course_delete;
    private JButton btn_patika_deletion;
    private JPanel pnl_content;
    private JPanel pnl_quiz;
    private JTable tbl_content;
    private JScrollPane scrl_content;
    private JPanel pnl_deletion;
    private JLabel lbl_content_deletion;
    private JTextField fld_content_deletion;
    private JButton btn_content_deletion;
    private JLabel lbl_quiz_id;
    private JTextField fld_quiz_delete;
    private JButton btn_quiz_deletion;
    private JScrollPane scrl_quiz;
    private JTable tbl_quiz;
    private JLabel lbl_course_id;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private final Operator operator;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;
    private Object[]row_course_list;
    private DefaultTableModel  mdl_content_list;
    private Object[] row_list;
    private DefaultTableModel  mdl_Quiz;
    private Object[] roww_list;

    public OperatorGui(Operator operator){

        this.operator = operator;
            add(wrapper);
            setSize(1000,500);
            int x= Helper.screenCenterPoint("x",getSize());
            int y= Helper.screenCenterPoint("y",getSize());
            setLocation(x,y);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle(Config.PROJECT_TITLE);
            setVisible(true);
            lbl_welcome.setText("Hoşgeldiniz " + operator.getName());

        mdl_user_list=new DefaultTableModel(){
            @Override

            public boolean isCellEditable(int row, int column) {
                if(column==0){
                    return  false;
                }
                return super.isCellEditable(row, column);
            }
        };




        Object[] col_user_list={"ID","Ad Soyad","Kullanıcı Adı","Şifre","Üyelik tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list=new Object[col_user_list.length];
        loadUserModel();
        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);









//------------------PATİKA-----------------

            patikaMenu=new JPopupMenu();
            JMenuItem updateMenu=new JMenuItem("Güncelle");
            JMenuItem deleteMenu=new JMenuItem("Sil");
            patikaMenu.add(updateMenu);
            patikaMenu.add(deleteMenu);


            updateMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int selected_id=Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());

                    UpdatePatikaGui updateGUI=new UpdatePatikaGui(Patika.getFetch(selected_id));
                    updateGUI.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            loadPatikaModel();
                            loadPatikaCombo();
                            loadCourseModel();
                        }
                    });


                }
            });

            deleteMenu.addActionListener(e -> {

                if(Helper.confirm("sure")){
                    int selected_id=Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
                    if(Patika.delete(selected_id)){
                        Helper.showMsg("done");
                        loadPatikaModel();
                        loadPatikaCombo();
                        loadCourseModel();
                    }
                    else{

                        Helper.showMsg("error");

                    }

                }




            });



        DefaultTableModel mdl_table_Quiz=new DefaultTableModel(){

            @Override

            public boolean isCellEditable(int row, int column) {
                if(column==0){
                    return  false;
                }
                return super.isCellEditable(row, column);
            }
        };
        tbl_quiz.getTableHeader().setReorderingAllowed(false);
        Object[] columnList={"id","title","Quiz"};
        mdl_Quiz.setColumnIdentifiers(columnList);
        Object[] rowwList=new Object[columnList.length];
        loadQuizModel();
        tbl_quiz.setModel(mdl_Quiz);


            //******************QUİZ**********************




            mdl_patika_list=new DefaultTableModel();
            Object[] col_patika_list={"id","Patika Adı"};
            mdl_patika_list.setColumnIdentifiers(col_patika_list);
            row_patika_list=new Object[col_patika_list.length];
            loadPatikaModel();
            loadPatikaCombo();
            tbl_patika_list.setModel(mdl_patika_list);
            tbl_patika_list.setComponentPopupMenu(patikaMenu);
            tbl_patika_list.getTableHeader().setReorderingAllowed(false);
            tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);




        Object[]column_list={"id","başlık","Açıklama","Link","Course"};
        mdl_content_list=new DefaultTableModel();
        mdl_content_list.setColumnIdentifiers(column_list);
        row_list=new Object[column_list.length];
        loadModelContent();
        tbl_content.setModel(mdl_content_list);


        tbl_content.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType()==TableModelEvent.UPDATE){

                    int id=Integer.parseInt(tbl_content.getValueAt(tbl_content.getSelectedRow(),0).toString());
                    String title=tbl_content.getValueAt(tbl_content.getSelectedRow(),1).toString();
                    String Explanation=tbl_content.getValueAt(tbl_content.getSelectedRow(),2).toString();
                    String link=tbl_content.getValueAt(tbl_content.getSelectedRow(),3).toString();
                    String course=tbl_content.getValueAt(tbl_content.getSelectedRow(),4).toString();

                    if(Content.Update(id,title,Explanation,link,course)){
                        Helper.showMsg("fill");
                        loadModelContent();
                    }
                }
            }
        });
//------------------PATİKA-----------------






        tbl_patika_list.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Point point =e.getPoint();
                int selected_row= tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row,selected_row);
            }

        });



               //-------------------COURSE----------------------
        mdl_course_list=new DefaultTableModel();
        Object[] col_courseList={"ID","Ders Adı","Programlama Dili","Patika","Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_courseList);
        row_course_list=new Object[col_courseList.length];
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);

        loadPatikaCombo();
        loadEducatorCombo();
        loadCourseModel();





        tbl_course_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType()== TableModelEvent.UPDATE){

                    int id=Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),0).toString());
                    int user_id=Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),1).toString());
                    int patika_id=Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),2).toString());
                    String name=tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),3).toString();
                    String lang=tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(),4).toString();

                    if(Course.update(id,user_id,patika_id,name,lang)){

                        Helper.showMsg("done");
                    }

                    loadCourseModel();
                    loadPatikaModel();
                    loadUserModel();

                }
            }
        });





        //-------------------COUSE----------------------



        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {


            try{
                String select_user_id=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString();
                fld_user_id.setText(select_user_id);
            }

            catch (Exception exception){

                exception.getMessage();
            }


        });



        btn_user_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_user_name)|| Helper.isFieldEmpty(fld_uname) ||Helper.isFieldEmpty(fld_user_pass)){

                Helper.showMsg("fill");
            }

            else{
                String name=fld_user_name.getText();
                String uname=fld_uname.getText();
                String pass=fld_user_pass.getText();
                String type=cmb_user_type.getSelectedItem().toString();
                if(User.add(name,uname,pass,type)){

                    Helper.showMsg("done");
                    loadUserModel();
                    loadEducatorCombo();
                    fld_user_name.setText(null);
                    fld_uname.setText(null);
                    fld_user_pass.setText(null);

                }


            }
        });

        tbl_quiz.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                if(e.getType()==TableModelEvent.UPDATE){

                    int id=Integer.parseInt(tbl_quiz.getValueAt(tbl_quiz.getSelectedRow(),0).toString());
                    String title=tbl_quiz.getValueAt(tbl_quiz.getSelectedRow(),1).toString();
                   String quiz=tbl_quiz.getValueAt(tbl_quiz.getSelectedRow(),2).toString();

                   if(Quiz.Update(id,title,quiz)){

                       Helper.showMsg("done");
                   }
                }
            }
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if(e.getType()==TableModelEvent.UPDATE){

                int user_id= Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                String user_name= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
                String user_uname= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
                String user_pass= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
                String user_type= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();


                if(User.update(user_id, user_name, user_uname, user_pass, user_type)){

                    Helper.showMsg("done");

                }

                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }
        });

        btn_user_delete.addActionListener(e -> {

            if(Helper.isFieldEmpty(fld_user_id)){
                Helper.showMsg("fill");
            }

            else{
                if(Helper.confirm("sure")){
                    int user_id=Integer.parseInt(fld_user_id.getText());
                    if(User.delete(user_id)){
                        Helper.showMsg("done");
                        loadUserModel();
                        loadEducatorCombo();
                        loadCourseModel();
                        fld_user_id.setText(null);
                    }

                    else{
                        Helper.showMsg("error");
                    }
                }

            }
        });
        bth_user_sh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name=fld_sh_user_name.getText();
                String uname=fld_sh_user_uname.getText();
                String type= cmb_sh_user_type.getSelectedItem().toString();
                String query=User.searchQuery(name,uname,type);
                ArrayList<User>searchingUser=new ArrayList<>();
                searchingUser=User.searchUserList(query);
                loadUserModel(searchingUser);


            }
        });
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGui lg =new LoginGui();
            }
        });

        //------------------PATİKA-----------------
        btn_patika_add.addActionListener( e -> {

            if(Helper.isFieldEmpty(fld_patika_name)){

                Helper.showMsg("fill");
            }

            else{
                if(Patika.add(fld_patika_name.getText())){

                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    fld_patika_name.setText(null);
                }

                else{

                    Helper.showMsg("error");

                }
            }
        });


        btn_course_add.addActionListener(e -> {

            Item patikaItem=(Item) cmb_course_patika.getSelectedItem();
            Item userItem=(Item)   cmb_course_user.getSelectedItem();
            if (Helper.isFieldEmpty(fld_course_name)||Helper.isFieldEmpty(fld_course_lang)) {

                Helper.showMsg("fill");

            }

            else{

                if(Course.add(userItem.getKey(),patikaItem.getKey(),fld_course_name.getText(),fld_course_lang.getText())){

                    Helper.showMsg("done");
                    loadCourseModel();
                    fld_course_lang.setText(null);
                    fld_course_name.setText(null);
                }

                else{

                    Helper.showMsg("error");
                }


            }





        });
        btn_patika_deletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name =fld_patika_name.getText();
                Patika.delete(name);

            }
        });
        btn_course_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int id= Integer.parseInt(fld_deletion_id.getText());

                Course.delete(id);




            }
        });
        btn_content_deletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id= Integer.parseInt(fld_content_deletion.getText());
                Content.delete(id);
            }
        });
        btn_quiz_deletion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int id= Integer.parseInt(fld_quiz_delete.getText());

                if(Quiz.delete(id)){

                    Helper.showMsg("done");
                }
            }
        });
    }




    private void loadPatikaCombo() {

        cmb_course_patika.removeAllItems();

        for(Patika obj:Patika.getlist()){

            cmb_course_patika.addItem(new Item(obj.getId(),obj.getName()));
        }

    }

    private void loadEducatorCombo() {

        cmb_course_user.removeAllItems();

        for(User obj:User.getlist()){

           if(obj.getType().equals("educator")){

               cmb_course_user.addItem(new Item(obj.getId(),obj.getName()));
           }
        }


    }



    private void loadCourseModel() {

        DefaultTableModel clearModel=(DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);

        int i;
        for(Course obj: Course.getList()){

            i=0;
            row_course_list[i++]=obj.getId();
            row_course_list[i++]=obj.getName();
            row_course_list[i++]=obj.getLang();
            row_course_list[i++]=obj.getPatika().getName();
            row_course_list[i++]=obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);

        }

    }

    //------------------PATİKA-----------------
    private void loadPatikaModel() {

           DefaultTableModel clearModel= (DefaultTableModel) tbl_patika_list.getModel();
           clearModel.setRowCount(0);
        int i;
           for(Patika obj: Patika.getlist()){
               i=0;
               row_patika_list[i++]=obj.getId();
               row_patika_list[i++]=obj.getName();

               mdl_patika_list.addRow(row_patika_list);

           }

    }
    //------------------PATİKA-----------------

    public void loadUserModel(){

        DefaultTableModel clearModel= (DefaultTableModel) tbl_user_list.getModel();

        clearModel.setRowCount(0);
        int i;
        for(User obj : User.getlist()){

            i=0;
            row_user_list[i++]=obj.getId();
            row_user_list[i++]=obj.getName();
            row_user_list[i++]=obj.getUname();
            row_user_list[i++]=obj.getPass();
            row_user_list[i++]=obj.getType();
            mdl_user_list.addRow(row_user_list);

        }
    }

    public void loadUserModel(ArrayList<User> list){

        DefaultTableModel clearModel= (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for(User obj : list){

            int i=0;
            row_user_list[i++]=obj.getId();
            row_user_list[i++]=obj.getName();
            row_user_list[i++]=obj.getUname();
            row_user_list[i++]=obj.getPass();
            row_user_list[i++]=obj.getType();
            mdl_user_list.addRow(row_user_list);
        }

    }

    public void loadModelContent(){

        DefaultTableModel clearModel=(DefaultTableModel) tbl_content.getModel();
        clearModel.setRowCount(0);

        int i;
        for(Content obj:Content.getListCont()){
            i=0;
            row_list[i++]=obj.getId();
            row_list[i++]=obj.getTitle();
            row_list[i++]=obj.getExplanation();
            row_list[i++]=obj.getLink();
            row_list[i++]=obj.getCourse();
            mdl_content_list.addRow(row_list);


        }
    }


    public void  loadQuizModel(){

        DefaultTableModel clearTable= (DefaultTableModel) tbl_quiz.getModel();
        clearTable.setRowCount(0);



        int i;

        for(Quiz obj:Quiz.getList()){
            i=0;
            roww_list[i++]=obj.getId();
            roww_list[i++]=obj.getTitle();
            roww_list[i++]=obj.getQuiz();
            mdl_Quiz.addRow(roww_list);


        }


    }
    public static void main(String[] args) {

        Helper.setLayout();
        Operator op=new Operator();
        op.setName("Uygar Cankat");
        op.setUname("Uygar Cankat");
        op.setType("operator");
        op.setPass("12345");
        OperatorGui ex = new OperatorGui(op);


    }



}
