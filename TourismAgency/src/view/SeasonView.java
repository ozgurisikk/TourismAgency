package view;

import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView extends Layout {
    private JPanel container;
    private JLabel lbl_ssn_title;
    private JTextField fld_ssn_id;
    private JTextField fld_ssn_hotel_id;
    private JButton btn_save;
    private JLabel lbl_ssn_id;
    private JLabel lbl_ssn_hotel_id;
    private JLabel lbl_ssn_strt;
    private JLabel lbl_ssn_end;
    private JFormattedTextField ffld_ssn_start;
    private JFormattedTextField ffld_ssn_fnsh;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private Season season;
    private SeasonManager seasonManager;


    public SeasonView(Season season){
        this.add(container);
        this.guiInitialize(300,500);
        this.season = season;
        this.seasonManager = new SeasonManager();
        this.fld_ssn_id.setText(String.valueOf(this.season.getId()));
        this.fld_ssn_hotel_id.setText(String.valueOf(this.season.getHotelId()));

        //Season save or update section
        this.btn_save.addActionListener(e -> {
            JTextField[] checkFieldList = {this.ffld_ssn_start, this.ffld_ssn_fnsh};
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");
            }else {
                boolean result;
                this.season.setStartDate(LocalDate.parse(ffld_ssn_start.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.season.setFinishDate(LocalDate.parse(ffld_ssn_fnsh.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.season.setHotelId(this.season.getHotelId());

                if (this.season.getId() != 0){
                    result = this.seasonManager.update(this.season);
                }else {
                    result = this.seasonManager.save(this.season);
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
    //formatting textfield
    private void createUIComponents() throws ParseException {
        this.ffld_ssn_start = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.ffld_ssn_start.setText("01/01/2024");
        this.ffld_ssn_fnsh = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.ffld_ssn_fnsh.setText("01/06/2024");
    }
}
