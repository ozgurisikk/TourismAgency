package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void showMsg(String str){
        String msg;
        String title;
        switch (str){
            case "fill":
                msg = "Tum alanlari doldurunuz";
                title = "Hata!";
                break;
            case "done":
                msg = "Islem basarili";
                title = "Basarili";
                break;
            case "notFound":
                msg = "Kullanici bulunamadi ";
                title = "Bulunamadi";
                break;
            case "error":
                msg = "Hatali islem yapildi";
                title = "Hata";
                break;
            case "psswrd":
                msg = "Sifre yanlis";
                title = "Hata";
                break;
            case "seasonMatch":
                msg = "Sectiginiz Hotel ID ile Sezon ID uyusmuyor!";
                title = "Hata";
                break;
            case "pensionMatch":
                msg = "Sectiginiz Hotel ID ile Pansiyon ID uyusmuyor!";
                title = "Hata";
                break;
            case "guestCount":
                msg = "Arama yapmadan önce yeişkin ve çocuk misafir sayılarını belirtmelisiniz!";
                title = "Hata";
                break;
            case "positiveCount":
                msg = "Negatif sayı değeri girilemez!";
                title = "Hata";
                break;
            case "createSeason":
                msg = "Oluşturulan hotele dair sezon eklemeyi unutmayın.";
                title = "Uyarı";
                break;
            case "fillAll":
                msg = "Tarih ve misafir bilgilerini giriniz!.";
                title = "Uyarı";
                break;
            case "bedCount":
                msg = "Yatak sayisi yetersiz!.";
                title = "Uyarı";
                break;
            case "yan":
                msg = "Room tablosunda misafir sayisini doldurmaniz gerek.";
                title = "Uyarı";
                break;
            default:
                msg = str;
                title = "Mesaj";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);

    }
    public static boolean confirm(String str){
        String msg;
        if (str.equals("sure")){
            msg = "Emin misiniz?";
        }else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Emin misiniz?", JOptionPane.YES_NO_OPTION) == 0;
    }
    public static int getLocationPoint(String type, Dimension size){
        switch (type){
            case "x" :
                return (int) ((Toolkit.getDefaultToolkit().getScreenSize().width - size.getWidth()) / 2);
            case "y":
                return (int)(Toolkit.getDefaultToolkit().getScreenSize().height - size.getHeight()) / 2 ;
            default:
                return 0;
        }
    }

    public static boolean isCmbEmpty(JComboBox box){
        if (box.getSelectedItem() == null){
            return true;
        }
        return false;
    }
    public static boolean isCmbListEmpty(JComboBox[] boxList){
        for (JComboBox box : boxList){
            if (isCmbEmpty(box)){
                return true;
            }
        }
        return false;
    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static boolean isFieldListEmpty(JTextField[] fieldList){
        for (JTextField field : fieldList){
            if (isFieldEmpty(field)){
                return true;
            }
        }
        return false;
    }
    public static void setTheme(){
        for (UIManager. LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }
}
