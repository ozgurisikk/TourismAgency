package view;

import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationView extends Layout{
    private JPanel container;
    private JPanel pnl_hotel_info;
    private JPanel pnl_hotel_left;
    private JPanel pnl_hotel_right;
    private JLabel lbl_hotel_info;
    private JRadioButton rdb_wifi;
    private JRadioButton rdb_pool;
    private JRadioButton rdb_conc;
    private JRadioButton rdb_fitness;
    private JRadioButton rdb_carpark;
    private JRadioButton rdb_room_srv;
    private JRadioButton rdb_spa;
    private JTextField fld_hotel_name;
    private JTextField fld_address;
    private JTextField fld_mpno;
    private JLabel lbl_htl_specs;
    private JLabel lbl_htl_name;
    private JLabel lbl_htl_adrs;
    private JLabel lbl_hotel_mpno;
    private JLabel lbl_room_inf;
    private JPanel pnl_room_info;
    private JTextField fld_room_type;
    private JTextField fld_square;
    private JTextField fld_bed;
    private JTextField fld_c_in;
    private JRadioButton rdb_safe;
    private JRadioButton rdb_tv;
    private JRadioButton rdb_minibar;
    private JRadioButton rdb_console;
    private JLabel lbl_room_type;
    private JTextField fld_c_out;
    private JLabel lbl_bed;
    private JLabel lbl_pension;
    private JLabel lbl_square;
    private JLabel lbl_c_in;
    private JLabel lbl_c_out;
    private JTextField fld_price;
    private JLabel lbl_price;
    private JPanel pnl_cust_info;
    private JTextField fld_count_adult;
    private JTextField fld_count_child;
    private JTextField fld_cus_name;
    private JTextField fld_cus_mpno;
    private JTextField fld_cus_mail;
    private JButton btn_save;
    private JLabel lbl_title;
    private JLabel lbl_count_adult;
    private JTextField fld_cus_id;
    private JLabel lbl_count_child;
    private JLabel lbl_cus_name;
    private JLabel lbl_cus_mpno;
    private JLabel lbl_cus_mail;
    private JLabel lbl_cus_id;
    private JTextField fld_pans_type;
    private Room room;
    private Hotel hotel;
    private RoomManager roomManager;
    private String strt;
    private String fnsh;
    private int adult;
    private int child;
    private Reservation res;
    private ReservationManager reservationManager;

    public ReservationView(Reservation res, Room room, Hotel hotel, boolean isEnabled, String strt, String fnsh, int adultCount, int childCount, int totalPrice){
        this.add(container);
        this.room = room;
        this.roomManager = new RoomManager();
        this.guiInitialize(600,900);
        this.hotel = hotel;
        this.strt = strt;
        this.fnsh = fnsh;
        this.adult = adultCount;
        this.child = childCount;
        this.res = res;
        this.reservationManager = new ReservationManager();

        isFieldEnabled(isEnabled);

        //filling areas
        this.fld_hotel_name.setText(this.hotel.getHotel_name());
        this.fld_address.setText(this.hotel.getHotel_address());
        this.fld_mpno.setText(this.hotel.getHotel_phone());
        this.rdb_carpark.setSelected(this.hotel.isHotel_car_park());
        this.rdb_wifi.setSelected(this.hotel.isHotel_wifi());
        this.rdb_pool.setSelected(this.hotel.isHotel_pool());
        this.rdb_fitness.setSelected(this.hotel.isHotel_fitness());
        this.rdb_spa.setSelected(this.hotel.isHotel_spa());
        this.rdb_room_srv.setSelected(this.hotel.isHotel_room_service());
        this.rdb_conc.setSelected(this.hotel.isHotel_concierge());
        this.fld_room_type.setText(String.valueOf((Room.RoomType) this.room.getRoomType()));
        this.fld_square.setText(String.valueOf(this.room.getSquareMeter()));
        this.fld_bed.setText(String.valueOf(this.room.getBedCount()));
        this.fld_pans_type.setText(String.valueOf(this.room.getPensionId()));
        this.fld_c_in.setText(String.valueOf(this.strt));
        this.fld_c_out.setText(String.valueOf(this.fnsh));
        this.rdb_tv.setSelected(this.room.isTelevision());
        this.rdb_minibar.setSelected(this.room.isMinibar());
        this.rdb_console.setSelected(this.room.isGameConsole());
        this.rdb_safe.setSelected(this.room.isCashBox());
        this.fld_price.setText(String.valueOf(totalPrice));
        this.fld_count_adult.setText(String.valueOf(this.adult));
        this.fld_count_child.setText(String.valueOf(this.child));

        //reservation save
        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_cus_name, this.fld_cus_mpno, this.fld_cus_id, this.fld_cus_id})){
                Helper.showMsg("fill");
            }else {
                boolean result;
                this.res.setCheckInDate(LocalDate.parse(this.strt, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.res.setCheckOutDate(LocalDate.parse(this.fnsh, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.res.setRoomId(this.room.getId());
                this.res.setTotalPrice(Integer.parseInt(this.fld_price.getText()));
                this.res.setGuestCount(adultCount + childCount);
                this.res.setGuestName(this.fld_cus_name.getText());
                this.res.setGuestId(this.fld_cus_id.getText());
                this.res.setGuestMail(this.fld_cus_mail.getText());
                this.res.setGuestMP(this.fld_cus_mpno.getText());

                if (this.res.getId() == 0){
                    result = this.reservationManager.save(this.res);
                    this.reservationManager.decreaseStock(this.room.getId());
                }else{
                    result = this.reservationManager.update(this.res);
                }
                if (result){
                    Helper.showMsg("done");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }

            }
        });
    }
    //this method will be used for update
    public void isFieldEnabled(boolean check){
        if (check){
            this.fld_hotel_name.setEnabled(true);
            this.fld_hotel_name.setEditable(true);
            this.fld_address.setEnabled(true);
            this.fld_address.setEditable(true);
            this.fld_mpno.setEnabled(true);
            this.fld_mpno.setEditable(true);
            this.fld_room_type.setEnabled(true);
            this.fld_room_type.setEditable(true);
            this.fld_price.setEnabled(true);
            this.fld_price.setEditable(true);
            this.fld_square.setEnabled(true);
            this.fld_square.setEditable(true);
            this.fld_c_in.setEnabled(true);
            this.fld_c_in.setEditable(true);
            this.fld_c_out.setEnabled(true);
            this.fld_c_out.setEditable(true);
            this.fld_count_adult.setEnabled(true);
            this.fld_count_adult.setEditable(true);
            this.fld_count_child.setEnabled(true);
            this.fld_count_child.setEditable(true);
            this.fld_bed.setEnabled(true);
            this.fld_bed.setEditable(true);
            this.rdb_carpark.setEnabled(true);
            this.rdb_wifi.setEnabled(true);
            this.rdb_pool.setEnabled(true);
            this.rdb_fitness.setEnabled(true);
            this.rdb_spa.setEnabled(true);
            this.rdb_room_srv.setEnabled(true);
            this.rdb_conc.setEnabled(true);
            this.rdb_tv.setEnabled(true);
            this.rdb_minibar.setEnabled(true);
            this.rdb_console.setEnabled(true);
            this.rdb_safe.setEnabled(true);
            this.fld_pans_type.setEnabled(true);
            this.fld_pans_type.setEditable(true);
        }
    }
}
