package dao;

import core.Db;
import entity.Hotel;
import org.omg.CORBA.TRANSACTION_MODE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    private final Connection con;

    public HotelDao(){
        this.con = Db.getInstance();
    }
    public ArrayList<Hotel> findAll(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel ORDER BY hotel_id ASC";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                hotelList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hotelList;
    }
    public Hotel match(ResultSet rs){
        Hotel obj = new Hotel();
        try {
            obj.setHotel_id(rs.getInt("hotel_id"));
            obj.setHotel_name(rs.getString("hotel_name"));
            obj.setHotel_address(rs.getString("hotel_address"));
            obj.setHotel_city(rs.getString("hotel_city"));
            obj.setHotel_mail(rs.getString("hotel_mail"));
            obj.setHotel_phone(rs.getString("hotel_phone"));
            obj.setHotel_star(rs.getString("hotel_star"));
            obj.setHotel_car_park(rs.getBoolean("hotel_car_park"));
            obj.setHotel_wifi(rs.getBoolean("hotel_wifi"));
            obj.setHotel_pool(rs.getBoolean("hotel_pool"));
            obj.setHotel_fitness(rs.getBoolean("hotel_fitness"));
            obj.setHotel_spa(rs.getBoolean("hotel_spa"));
            obj.setHotel_room_service(rs.getBoolean("hotel_room_service"));
            obj.setHotel_concierge(rs.getBoolean("hotel_concierge"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public boolean save(Hotel hotel){
        String query = "INSERT INTO public.hotel " +
                "(" +
                "hotel_name," +
                "hotel_address," +
                "hotel_city," +
                "hotel_mail," +
                "hotel_phone," +
                "hotel_star," +
                "hotel_car_park," +
                "hotel_wifi," +
                "hotel_pool," +
                "hotel_fitness," +
                "hotel_spa," +
                "hotel_room_service," +
                "hotel_concierge" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_address());
            pr.setString(3, hotel.getHotel_city());
            pr.setString(4, hotel.getHotel_mail());
            pr.setString(5, hotel.getHotel_phone());
            pr.setString(6, hotel.getHotel_star());
            pr.setBoolean(7, hotel.isHotel_car_park());
            pr.setBoolean(8, hotel.isHotel_wifi());
            pr.setBoolean(9, hotel.isHotel_pool());
            pr.setBoolean(10, hotel.isHotel_fitness());
            pr.setBoolean(11, hotel.isHotel_spa());
            pr.setBoolean(12, hotel.isHotel_room_service());
            pr.setBoolean(13, hotel.isHotel_concierge());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Hotel hotel){
        String query = "UPDATE public.hotel SET hotel_name = ? , hotel_address = ? , hotel_city = ? , hotel_mail = ? , hotel_phone = ? , hotel_star = ? ," +
                "hotel_car_park = ? , hotel_wifi = ? , hotel_pool = ? , hotel_fitness = ? , hotel_spa = ? , hotel_room_service = ? , hotel_concierge = ? " +
                "WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_address());
            pr.setString(3, hotel.getHotel_city());
            pr.setString(4, hotel.getHotel_mail());
            pr.setString(5, hotel.getHotel_phone());
            pr.setString(6, hotel.getHotel_star());
            pr.setBoolean(7, hotel.isHotel_car_park());
            pr.setBoolean(8, hotel.isHotel_wifi());
            pr.setBoolean(9, hotel.isHotel_pool());
            pr.setBoolean(10, hotel.isHotel_fitness());
            pr.setBoolean(11, hotel.isHotel_spa());
            pr.setBoolean(12, hotel.isHotel_room_service());
            pr.setBoolean(13, hotel.isHotel_concierge());
            pr.setInt(14, hotel.getHotel_id());

            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ? ";
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
