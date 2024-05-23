package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;

public class RoomView extends Layout {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_title2;
    private JComboBox cmb_otel;
    private JComboBox cmb_pension;
    private JComboBox cmb_room_type;
    private JTextField fld_stock;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_bed;
    private JTextField fld_square;
    private JRadioButton rdb_tv;
    private JRadioButton rdb_minibar;
    private JRadioButton rdb_console;
    private JRadioButton rdb_safe;
    private JRadioButton rdb_proj;
    private JLabel lbl_otel;
    private JLabel lbl_pension;
    private JLabel lbl_season;
    private JLabel lbl_room_type;
    private JLabel lbl_stock;
    private JLabel lbl_adult_price;
    private JLabel lbl_child_price;
    private JLabel lbl_bed;
    private JLabel lbl_square;
    private JLabel lbl_tv;
    private JLabel lbl_minibar;
    private JLabel lbl_console;
    private JLabel lbl_safe;
    private JLabel lbl_proj;
    private JButton btn_save;
    private JPanel pnl_left;
    private JPanel pnl_right;
    private JComboBox cmb_season;
    private JTextField fld_season;
    private Room room;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private int key;

    public RoomView(Room room) {
        this.add(container);
        this.room = room;
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.guiInitialize(600, 600);


        //adding objects as combo
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_otel.addItem(hotel.getComboItem());
        }
        for (Season season : this.seasonManager.findAll()) {
            this.cmb_season.addItem(season.getComboItem());
        }
        for (Pension pension : this.pensionManager.findAll()) {
            this.cmb_pension.addItem(pension.getComboItem());
        }
        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.RoomType.values()));
        //checking whether if specs are null or not
        if (this.room.getId() != 0){
            this.fld_stock.setText(String.valueOf(this.room.getStock()));
            this.fld_adult_price.setText(String.valueOf(this.room.getAdultPrice()));
            this.fld_child_price.setText(String.valueOf(this.room.getChildPrice()));
            this.fld_bed.setText(String.valueOf(this.room.getBedCount()));
            this.fld_square.setText(String.valueOf(this.room.getSquareMeter()));
            this.rdb_tv.setSelected(this.room.isTelevision());
            this.rdb_minibar.setSelected(this.room.isMinibar());
            this.rdb_console.setSelected(this.room.isGameConsole());
            this.rdb_safe.setSelected(this.room.isCashBox());
            this.rdb_proj.setSelected(this.room.isProjector());
        }

            // saving or updating the object
        btn_save.addActionListener(e -> {
            if ((Helper.isFieldListEmpty(new JTextField[]{this.fld_square, this.fld_bed, this.fld_stock, this.fld_adult_price, this.fld_child_price}))) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                ComboItem selectedHotel = (ComboItem) this.cmb_otel.getSelectedItem();
                ComboItem selectedPension = (ComboItem) this.cmb_pension.getSelectedItem();
                ComboItem selectedSeason = (ComboItem) this.cmb_season.getSelectedItem();
                this.room.setHotelId(selectedHotel.getKey());
                this.room.setPensionId(selectedPension.getKey());
                this.room.setSeasonId(selectedSeason.getKey());
                this.room.setRoomType((Room.RoomType) this.cmb_room_type.getSelectedItem());
                this.room.setStock(Integer.parseInt(this.fld_stock.getText()));
                this.room.setAdultPrice(Integer.parseInt(this.fld_adult_price.getText()));
                this.room.setChildPrice(Integer.parseInt(this.fld_child_price.getText()));
                this.room.setBedCount(Integer.parseInt(this.fld_bed.getText()));
                this.room.setSquareMeter(Integer.parseInt(this.fld_square.getText()));
                this.room.setTelevision(this.rdb_tv.isSelected());
                this.room.setMinibar(this.rdb_minibar.isSelected());
                this.room.setGameConsole(this.rdb_console.isSelected());
                this.room.setCashBox(this.rdb_safe.isSelected());
                this.room.setProjector(this.rdb_proj.isSelected());

                if (this.room.getId() == 0) {
                    result = this.roomManager.save(this.room);
                } else {
                    result = this.roomManager.update(this.room);
                }
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }


}
