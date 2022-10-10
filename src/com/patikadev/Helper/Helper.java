package com.patikadev.Helper;

import groovyjarjarpicocli.CommandLine;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static int screenCenterPoint(String axis, Dimension size) {

       int point = switch (axis) {
           case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
           case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
           default -> 0;
       };

        return point;

    }

    public static void setLayout(){
        for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){

            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static boolean isFieldEmpty(JTextField field){

      return field.getText().trim().isEmpty();

    }

    public static boolean isFieldEmpty(JTextArea field){

        return field.getText().trim().isEmpty();

    }


    public static void showMsg(String str){

        optionPageTR();
        String msg;
        String title;

        switch(str){

            case "fill":

                msg="Lütfen tüm alanları doldurunuz";
                title="Hata";
                break;

            case "done":
                msg= "İşlem başarılı";
                title= "Sonuç";
                break;

            case "error":
                msg= "Bir hata oluştu !";
                title= "Hata";
                break;

            default:

                msg=str;
                title="Mesaj";
        }

        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }


    public static boolean signMessage(){

        String msg="Kayıt olmak ister misiniz?";



        return JOptionPane.showConfirmDialog(null,msg,"Kullanıcı bulunamadı ! Kayıt Olmak İster misiniz?",JOptionPane.YES_NO_OPTION)==0;


    };


    public static boolean signOp(){

        String msg="Operator müsünüz?";


        return JOptionPane.showConfirmDialog(null,msg,"Kayıt etmek ister misiniz?",JOptionPane.YES_NO_OPTION)==0;


    };


    public static boolean signEdu(){

        String msg="Educator misiniz?";


        return JOptionPane.showConfirmDialog(null,msg,"Kayıt etmek ister misiniz?",JOptionPane.YES_NO_OPTION)==0;


    };

    public static void optionPageTR(){

        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.YesButtonText","evet");
        UIManager.put("OptionPane.NoButtonText","hayır");



    }


    public static boolean confirm(String str){

        String msg;
        switch(str){
            case "sure":

                msg="Bu işlemi gerçekleştirmek istediğinize emin misiniz";
                break;

            default:
                msg=str;
        }


        return JOptionPane.showConfirmDialog(null,msg,"Son kararın mı?",JOptionPane.YES_NO_OPTION)==0;

    }

}
