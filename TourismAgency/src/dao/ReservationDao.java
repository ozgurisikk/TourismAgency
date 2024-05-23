package dao;

import core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {

    private final Connection con;
    public ReservationDao(){
        this.con = Db.getInstance();
    }

    public ArrayList<Reservation> findAll(){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation ORDER BY id ASC";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                reservationList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reservationList;
    }
    public Reservation match(ResultSet rs){
        Reservation obj = new Reservation();
        try {
            obj.setId(rs.getInt("id"));
            obj.setRoomId(rs.getInt("room_id"));
            obj.setCheckInDate(LocalDate.parse(rs.getString("cin_date")));
            obj.setCheckOutDate(LocalDate.parse(rs.getString("cout_date")));
            obj.setTotalPrice(rs.getInt("price"));
            obj.setGuestCount(rs.getInt("guest_count"));
            obj.setGuestName(rs.getString("name"));
            obj.setGuestId(rs.getString("guest_id"));
            obj.setGuestMail(rs.getString("mail"));
            obj.setGuestMP(rs.getString("phone"));
            obj.setHotelId(rs.getInt("hotel_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation " +
                "(" +
                "room_id," +
                "cin_date," +
                "cout_date," +
                "price," +
                "guest_count," +
                "name," +
                "guest_id," +
                "mail," +
                "phone," +
                "hotel_id" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getRoomId());
            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));
            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));
            pr.setInt(4, reservation.getTotalPrice());
            pr.setInt(5, reservation.getGuestCount());
            pr.setString(6, reservation.getGuestName());
            pr.setString(7, reservation.getGuestId());
            pr.setString(8, reservation.getGuestMail());
            pr.setString(9, reservation.getGuestMP());
            pr.setInt(10, reservation.getHotelId());

            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservation SET room_id = ? , cin_date = ? , cout_date = ? , price = ? , guest_count = ? , name = ? ," +
                "guest_id = ? , mail = ? , phone = ? , hotel_id = ?" +
                "WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getRoomId());
            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));
            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));
            pr.setInt(4, reservation.getTotalPrice());
            pr.setInt(5, reservation.getGuestCount());
            pr.setString(6, reservation.getGuestName());
            pr.setString(7, reservation.getGuestId());
            pr.setString(8, reservation.getGuestMail());
            pr.setString(9, reservation.getGuestMP());
            pr.setInt(10, reservation.getHotelId());
            pr.setInt(11, reservation.getId());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean decreaseStock(int roomId){
        String query = "UPDATE public.room SET stock = stock-1 WHERE id = ?" ;
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, roomId);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean increaseStock(int roomId){
        String query = "UPDATE public.room SET stock = stock+1 WHERE id = ?" ;
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, roomId);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                obj = this.match(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
