package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

public class HotelView extends Layout{
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_title;
    private JTextField fld_name;
    private JRadioButton rdo_carpark;
    private JRadioButton rdo_wifi;
    private JTextField fld_mail;
    private JTextField fld_mpno;
    private JTextField fld_address;
    private JTextField fld_star;
    private JRadioButton rdo_pool;
    private JRadioButton rdo_fitness;
    private JRadioButton rdo_concierge;
    private JRadioButton rdo_spa;
    private JRadioButton rdo_room_service;
    private JButton btn_save;
    private JLabel lbl_name;
    private JLabel lbl_mail;
    private JLabel lbl_mpno;
    private JLabel lbl_address;
    private JLabel lbl_star;
    private JTextField fld_city;
    private JLabel lbl_city;
    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelView(Hotel hotel){
        this.add(container);
        this.guiInitialize(400,500);
        this.hotel = hotel;
        this.hotelManager = new HotelManager();


        if (this.hotel.getHotel_id() != 0){
            this.fld_name.setText(this.hotel.getHotel_name());
            this.fld_address.setText(this.hotel.getHotel_address());
            this.fld_mail.setText(this.hotel.getHotel_mail());
            this.fld_mpno.setText(this.hotel.getHotel_phone());
            this.fld_star.setText(this.hotel.getHotel_star());
            this.fld_city.setText(this.hotel.getHotel_city());
            this.rdo_fitness.setSelected(this.hotel.isHotel_fitness());
            this.rdo_carpark.setSelected(this.hotel.isHotel_car_park());
            this.rdo_concierge.setSelected(this.hotel.isHotel_concierge());
            this.rdo_pool.setSelected(this.hotel.isHotel_pool());
            this.rdo_wifi.setSelected(this.hotel.isHotel_wifi());
            this.rdo_room_service.setSelected(this.hotel.isHotel_room_service());
            this.rdo_spa.setSelected(this.hotel.isHotel_spa());

        }

        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[] {this.fld_name,this.fld_address,this.fld_mail,this.fld_mpno, this.fld_star, this.fld_city})){
                Helper.showMsg("fill");
            }else {
                boolean result;
                this.hotel.setHotel_name(this.fld_name.getText());
                this.hotel.setHotel_address(this.fld_address.getText());
                this.hotel.setHotel_mail(this.fld_mail.getText());
                this.hotel.setHotel_phone(this.fld_mpno.getText());
                this.hotel.setHotel_star(this.fld_star.getText());
                this.hotel.setHotel_city(this.fld_city.getText());
                this.hotel.setHotel_fitness(this.rdo_fitness.isSelected());
                this.hotel.setHotel_car_park(this.rdo_carpark.isSelected());
                this.hotel.setHotel_concierge(this.rdo_concierge.isSelected());
                this.hotel.setHotel_pool(this.rdo_pool.isSelected());
                this.hotel.setHotel_wifi(this.rdo_wifi.isSelected());
                this.hotel.setHotel_room_service(this.rdo_room_service.isSelected());
                this.hotel.setHotel_spa(this.rdo_spa.isSelected());
                if (this.hotel.getHotel_id() == 0 ){
                    result = this.hotelManager.save(this.hotel);
                }else{
                    result = this.hotelManager.update(this.hotel);
                }if (result){
                    Helper.showMsg("done");
                    Helper.showMsg("createSeason");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }
            }
        });
    }
}
