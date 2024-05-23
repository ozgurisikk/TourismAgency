package view;

import business.PensionManager;
import core.Helper;
import entity.Pension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PensionView extends Layout {
    private JPanel container;
    private JPanel lbl_header;
    private JLabel lbl_title;
    private JPanel pnl_bottom;
    private JLabel lbl_pens_type;
    private JButton btn_save;
    private JTextField fld_hotel_id;
    private JLabel lbl_hotel_id;
    private JTextField fld_pans_id;
    private JLabel lbl_pens_id;
    private JTextField fld_pension_type;
    private JRadioButton rdb_type1;
    private JRadioButton rdb_type2;
    private JRadioButton rdb_type3;
    private JRadioButton rdb_type4;
    private JRadioButton rdb_type5;
    private JRadioButton rdb_type6;
    private JRadioButton rdb_type7;
    private PensionManager pensionManager;
    private Pension pension;
    public PensionView(Pension pension){
        this.add(container);
        this.guiInitialize(300,500);
        this.pensionManager = new PensionManager();
        this.pension = pension;
        typeSelector();

        this.fld_hotel_id.setText(String.valueOf(pension.getHotelId()));
        this.fld_pans_id.setText(String.valueOf(pension.getId()));

        //save or update
        this.btn_save.addActionListener(e -> {
            if (this.fld_pension_type.getText().isEmpty()){
                Helper.showMsg("fill");
            }else {
                boolean result;
                this.pension.setPensionType(this.fld_pension_type.getText());
                this.pension.setHotelId(Integer.parseInt(this.fld_hotel_id.getText()));
                if (this.pension.getId() != 0 ){
                    result = this.pensionManager.update(this.pension);
                }else {
                    result = this.pensionManager.save(this.pension);
                }
                if (result){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }

        });

    }
    //auto type chooser
    public void typeSelector() {
        rdb_type1.addActionListener(e -> {
            this.fld_pension_type.setText("Sadece Yatak");
            this.rdb_type2.setEnabled(false);
            this.rdb_type3.setEnabled(false);
            this.rdb_type4.setEnabled(false);
            this.rdb_type5.setEnabled(false);
            this.rdb_type6.setEnabled(false);
            this.rdb_type7.setEnabled(false);
        });
        rdb_type2.addActionListener(e -> {
            this.fld_pension_type.setText("Oda Kahvaltı");
            this.rdb_type1.setEnabled(false);
            this.rdb_type3.setEnabled(false);
            this.rdb_type4.setEnabled(false);
            this.rdb_type5.setEnabled(false);
            this.rdb_type6.setEnabled(false);
            this.rdb_type7.setEnabled(false);
        });
        rdb_type3.addActionListener(e -> {
            this.fld_pension_type.setText("Yarım Pansiyon");
            this.rdb_type2.setEnabled(false);
            this.rdb_type1.setEnabled(false);
            this.rdb_type4.setEnabled(false);
            this.rdb_type5.setEnabled(false);
            this.rdb_type6.setEnabled(false);
            this.rdb_type7.setEnabled(false);
        });
        rdb_type4.addActionListener(e -> {
            this.fld_pension_type.setText("Tam Pansiyon");
            this.rdb_type2.setEnabled(false);
            this.rdb_type3.setEnabled(false);
            this.rdb_type1.setEnabled(false);
            this.rdb_type5.setEnabled(false);
            this.rdb_type6.setEnabled(false);
            this.rdb_type7.setEnabled(false);
        });
        rdb_type5.addActionListener(e -> {
            this.fld_pension_type.setText("Alkol Hariç Full Credit");
            this.rdb_type2.setEnabled(false);
            this.rdb_type3.setEnabled(false);
            this.rdb_type4.setEnabled(false);
            this.rdb_type1.setEnabled(false);
            this.rdb_type6.setEnabled(false);
            this.rdb_type7.setEnabled(false);
        });
        rdb_type6.addActionListener(e -> {
            this.fld_pension_type.setText("Her Şey Dahil");
            this.rdb_type2.setEnabled(false);
            this.rdb_type3.setEnabled(false);
            this.rdb_type4.setEnabled(false);
            this.rdb_type5.setEnabled(false);
            this.rdb_type1.setEnabled(false);
            this.rdb_type7.setEnabled(false);
        });
        rdb_type7.addActionListener(e -> {
            this.fld_pension_type.setText("Ultra Her Şey Dahil");
            this.rdb_type2.setEnabled(false);
            this.rdb_type3.setEnabled(false);
            this.rdb_type4.setEnabled(false);
            this.rdb_type5.setEnabled(false);
            this.rdb_type6.setEnabled(false);
            this.rdb_type1.setEnabled(false);
        });
    }
}
