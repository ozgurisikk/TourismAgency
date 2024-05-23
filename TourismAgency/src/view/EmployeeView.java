package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private Object[] col_hotel;
    private Object[] col_pension;
    private Object[] col_season;
    private Object[] col_room;
    private Object[] col_reservation;
    private JTabbedPane tbd_pane;
    private JTable tbl_hotel;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private JTable tbl_pension;
    private JTable tbl_season;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel_list;
    private JScrollPane scrl_pension_list;
    private JScrollPane scrl_season;
    private JPanel pnl_room;
    private JTable tbl_room;
    private JTextField fld_srch_hotel;
    private JTextField fld_srch_city;
    private JTextField fld_srch_adult;
    private JTextField fld_srch_child;
    private JButton btn_search;
    private JButton btn_clear;
    private JLabel lbl_srch_hotel;
    private JLabel lbl_srch_city;
    private JLabel lbl_srch_entry;
    private JLabel lbl_srch_leave;
    private JLabel lbl_srch_adult;
    private JLabel lbl_srch_child;
    private JScrollPane scrl_room;
    private JTable tbl_reservation;
    private JPanel pnl_reservation;
    private JScrollPane scrl_reservation;
    private JFormattedTextField fld_srch_entry;
    private JFormattedTextField fld_srch_leave;
    private JPopupMenu hotel_menu;
    private JPopupMenu pension_menu;
    private JPopupMenu room_menu;
    private JPopupMenu reservation_menu;
    private User user;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private JPopupMenu season_menu;



    public EmployeeView(User user) {
        this.add(container);
        this.user = user;
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.guiInitialize(1500,600);

        if (this.user == null){
            dispose();
        }
        this.lbl_welcome.setText("Hosgeldiniz " + user.getUsername().toUpperCase() + ".");

        loadHotelTable();
        loadPensionTable();
        loadSeasonTable();
        loadHotelComponent();

        loadPensionComponent();
        loadSeasonComponent();

        loadRoomComponent();
        loadRoomTable(null);

        loadReservationTable();
        loadReservationComponent();



    }
    public void loadHotelTable(){
        this.col_hotel = new Object[]{"ID", "İsim", "Adres", "Şehir", "Mail", "Telefon", "Yıldız", "Otopark", "Wifi", "Havuz", "Fitness", "Spa", "Oda Servisi", "Konferans Salonu"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length);
        this.createTable(this.tmdl_hotel, this.tbl_hotel,col_hotel,hotelList);
    }
    public void loadPensionTable(){
        this.col_pension = new Object[]{"ID", "Hotel ID", "Pansiyon Tipi"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length);
        this.createTable(this.tmdl_pension, this.tbl_pension,col_pension,pensionList);
    }
    public void loadSeasonTable(){
        this.col_season = new Object[]{"ID", "Hotel ID", "Başlangıç Tarihi", "Bitiş Tarihi"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length);
        this.createTable(this.tmdl_season, this.tbl_season,col_season,seasonList);
    }
    public void loadRoomTable(ArrayList<Object[]> roomList){
        this.col_room = new Object[] {"ID" , "Hotel ID" , "Pansiyon ID" , "Sezon ID" , "Oda Tipi" , "Stok", "Yetişkin Fiyat", "Çocuk Fiyat" , "Yatak Sayısı", "Metrekare", "Televizyon" , "Minibar" , "Oyun Konsolu" , "Kasa", "Projektör"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }
        this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }
    public void loadReservationTable(){
        this.col_reservation = new Object[]{"ID", "Oda ID" , "Giris Tarihi" , "Cikis Tarihi" , "Toplam Tutar" , "Misafir Sayisi" , "Misafir Ismi" , "Musteri Kimlik No" , "Mail" , "Telefon", "Hotel ID"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_reservation.length);
        this.createTable(this.tmdl_reservation, this.tbl_reservation, col_reservation, reservationList);
    }
    public void loadHotelComponent(){
        this.tableRowSelect(this.tbl_hotel);

        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Hotel Ekle").addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });
        this.hotel_menu.add("Hotel Bilgi Guncelle").addActionListener(e -> {
            int selectedHotelId = this.getTableSelectedRow(tbl_hotel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectedHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                }
            });
        });
        this.hotel_menu.add("Hotel Sil").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectedId)){
                    Helper.showMsg("done");
                    loadHotelTable();
                    loadPensionTable();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        this.hotel_menu.add("Pansiyon Tipi Ekle").addActionListener(e -> {
            String selectedHotelId = String.valueOf(this.getTableSelectedRow(tbl_hotel, 0));
            Pension pensionNew = new Pension(Integer.parseInt(selectedHotelId));
            PensionView pensionView = new PensionView(pensionNew);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                }
            });
        });
        this.hotel_menu.add("Sezon Ekle").addActionListener(e -> {
            String selectedHotelId = String.valueOf(this.getTableSelectedRow(tbl_hotel, 0));
            Season seasonNew = new Season(Integer.parseInt(selectedHotelId));
            SeasonView seasonView = new SeasonView(seasonNew);
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                    loadSeasonTable();
                }
            });
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_menu);
    }
    public void loadPensionComponent(){
        this.tableRowSelect(this.tbl_pension);
        this.pension_menu = new JPopupMenu();
        this.pension_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedId = this.getTableSelectedRow(tbl_pension, 0);
                if (this.pensionManager.delete(selectedId)){
                    Helper.showMsg("done");
                    loadHotelTable();
                    loadPensionTable();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        this.pension_menu.add("Pansiyon Guncelle").addActionListener(e -> {
            int selectedPensionId = this.getTableSelectedRow(tbl_pension, 0);
            Pension pension = this.pensionManager.getById(selectedPensionId);
            PensionView pensionView = new PensionView(pension);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                }
            });
        });
        this.tbl_pension.setComponentPopupMenu(pension_menu);
    }
    public void loadSeasonComponent(){
        this.tableRowSelect(this.tbl_season);
        this.season_menu = new JPopupMenu();
        this.season_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectedId)){
                    Helper.showMsg("done");
                    loadHotelTable();
                    loadPensionTable();
                    loadSeasonTable();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
        this.season_menu.add("Sezon Guncelle").addActionListener(e -> {
            int selectedSeasonId = this.getTableSelectedRow(tbl_season, 0);
            Season season = this.seasonManager.getById(selectedSeasonId);
            SeasonView seasonView = new SeasonView(season);
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                    loadSeasonTable();
                }
            });
        });
        this.tbl_season.setComponentPopupMenu(season_menu);
    }
    public void loadRoomComponent(){
        this.tableRowSelect(this.tbl_room);
        this.room_menu = new JPopupMenu();
        this.room_menu.add("Rezervasyon Olustur").addActionListener(e -> {
            int selectedRoomId = this.getTableSelectedRow(tbl_room, 0);
            int selectedHotelId = this.getTableSelectedRow(tbl_room, 1);

            if (!this.fld_srch_adult.getText().isEmpty() && !this.fld_srch_child.getText().isEmpty() && !this.fld_srch_entry.getText().isEmpty() && !this.fld_srch_leave.getText().isEmpty()){
                int bedCount = this.getTableSelectedRow(tbl_room , 8);
                int adultCount = Integer.parseInt(this.fld_srch_adult.getText());
                int childCount = Integer.parseInt(this.fld_srch_child.getText());
                int adultPrice = getTableSelectedRow(tbl_room , 6);
                int childPrice = getTableSelectedRow(tbl_room , 7);
                if (adultCount >= 0 && childCount >= 0){
                    if (!(bedCount < adultCount + childCount)){
                        int days = this.reservationManager.calculateDay(fld_srch_entry.getText(), fld_srch_leave.getText());
                        int totalPrice = (adultCount * days * adultPrice) + (childPrice * days * childCount);
                        ReservationView reservationView = new ReservationView(new Reservation(), this.roomManager.getById(selectedRoomId), this.hotelManager.getById(selectedHotelId), false, this.fld_srch_entry.getText(), this.fld_srch_leave.getText(), Integer.parseInt(this.fld_srch_adult.getText()), Integer.parseInt(this.fld_srch_child.getText()), totalPrice );
                        reservationView.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                loadRoomTable(null);
                                loadReservationTable();
                            }
                        });
                    }else{
                        Helper.showMsg("bedCount");
                    }
                }else {
                    Helper.showMsg("positiveCount");
                }
            }else {
                Helper.showMsg("fillAll");
            }
        });
        this.room_menu.add("Oda Ekle").addActionListener(e -> {
            RoomView roomView = new RoomView(new Room());
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });
        this.room_menu.add("Oda Bilgisi Guncelle").addActionListener(e -> {
            int selectedRoomId = this.getTableSelectedRow(tbl_room, 0);
            RoomView roomView = new RoomView(this.roomManager.getById(selectedRoomId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                    loadRoomTable(null);
                }
            });
        });
        this.room_menu.add("Sil").addActionListener(e -> {

            if (Helper.confirm("sure")){
                int selectedRoomId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.delete(selectedRoomId)){
                    Helper.showMsg("done");
                    loadHotelTable();
                    loadPensionTable();
                    loadRoomTable(null);
                }else {
                    Helper.showMsg("error");
                }
            }

        });
        this.tbl_room.setComponentPopupMenu(room_menu);

        this.btn_search.addActionListener(e -> {
            if (!this.fld_srch_child.getText().isEmpty() && !this.fld_srch_adult.getText().isEmpty()){
                int childCount = Integer.parseInt(this.fld_srch_child.getText());
                int adultCount = Integer.parseInt(this.fld_srch_adult.getText());
                if (childCount >= 0 && adultCount >= 0){
                        ArrayList<Room> roomList = this.roomManager.searchForBooking(
                                this.fld_srch_hotel.getText(),
                                this.fld_srch_city.getText(),
                                this.fld_srch_entry.getText(),
                                this.fld_srch_leave.getText(),
                                this.fld_srch_adult.getText(),
                                this.fld_srch_child.getText()
                        );
                        ArrayList<Object[]> roomRow = this.roomManager.getForTable(this.col_room.length, roomList);
                        loadRoomTable(roomRow);
                }else {
                    Helper.showMsg("positiveCount");
                }
            }else {
                Helper.showMsg("guestCount");
            }

        });
        btn_clear.addActionListener(e -> {
            loadRoomTable(null);
        });
    }

    public void loadReservationComponent(){
        this.tableRowSelect(tbl_reservation);
        this.reservation_menu = new JPopupMenu();
        this.reservation_menu.add("Rezervasyonu Sil").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectedReservationId = this.getTableSelectedRow(tbl_reservation, 0);
                int selectedRoomId = this.getTableSelectedRow(tbl_reservation, 1);
                if (this.reservationManager.delete(selectedReservationId)){
                    Helper.showMsg("done");
                    this.reservationManager.increaseStock(selectedRoomId);
                    loadHotelTable();
                    loadPensionTable();
                    loadRoomTable(null);
                    loadReservationTable();
                }else {
                    Helper.showMsg("error");
                }
            }

        });
        this.tbl_reservation.setComponentPopupMenu(reservation_menu);
    }


//Integer.parseInt(this.fld_srch_child.getText()) > 0
    private void createUIComponents() throws ParseException {
        this.fld_srch_entry = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_srch_leave = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_srch_entry.setText("01/01/2024");
        this.fld_srch_leave.setText("05/01/2024");
    }
}
