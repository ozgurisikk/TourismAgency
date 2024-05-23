package dao;

import core.Db;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;

    public RoomDao(){
        this.con = Db.getInstance();
    }
    public ArrayList<Room> findAll(){
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room WHERE NOT stock < 1 ORDER BY id ASC";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                roomList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return roomList;
    }
    public Room match(ResultSet rs){
        Room obj = new Room();
        try {
            obj.setId(rs.getInt("id"));
            obj.setHotelId(rs.getInt("hotel_id"));
            obj.setPensionId(rs.getInt("pension_id"));
            obj.setSeasonId(rs.getInt("season_id"));
            obj.setRoomType(Room.RoomType.valueOf(rs.getString("room_type")));
            obj.setStock(rs.getInt("stock"));
            obj.setAdultPrice(rs.getInt("adult_price"));
            obj.setChildPrice(rs.getInt("child_price"));
            obj.setBedCount(rs.getInt("bed_count"));
            obj.setSquareMeter(rs.getInt("square_meter"));
            obj.setTelevision(rs.getBoolean("television"));
            obj.setMinibar(rs.getBoolean("minibar"));
            obj.setGameConsole(rs.getBoolean("game_console"));
            obj.setCashBox(rs.getBoolean("cash_safe"));
            obj.setProjector(rs.getBoolean("projector"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ? ";
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
    public boolean save(Room room){
        String query = "INSERT INTO public.room " +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "room_type," +
                "stock," +
                "adult_price," +
                "child_price," +
                "bed_count," +
                "square_meter," +
                "television," +
                "minibar," +
                "game_console," +
                "cash_safe," +
                "projector" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getPensionId());
            pr.setInt(3, room.getSeasonId());
            pr.setString(4, room.getRoomType().toString());
            pr.setInt(5, room.getStock());
            pr.setInt(6, room. getAdultPrice());
            pr.setInt(7, room.getChildPrice());
            pr.setInt(8, room.getBedCount());
            pr.setInt(9, room.getSquareMeter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isCashBox());
            pr.setBoolean(14, room.isProjector());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Room room){
        String query = "UPDATE public.room SET hotel_id = ? , pension_id = ? , season_id = ? , room_type = ? , stock = ? , adult_price = ? ," +
                "child_price = ? , bed_count = ? , square_meter = ? , television = ? , minibar = ? , game_console = ? , cash_safe = ? , projector = ? " +
                "WHERE id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getPensionId());
            pr.setInt(3, room.getSeasonId());
            pr.setString(4, room.getRoomType().toString());
            pr.setInt(5, room.getStock());
            pr.setInt(6, room. getAdultPrice());
            pr.setInt(7, room.getChildPrice());
            pr.setInt(8, room.getBedCount());
            pr.setInt(9, room.getSquareMeter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isCashBox());
            pr.setBoolean(14, room.isProjector());
            pr.setInt(15, room.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.room WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    // Search the bookings with the filtered choices
    public ArrayList<Room> searchForBooking(String hotelName, String hotelCity, String strt_date, String fnsh_date, String adultCount, String childCount){
        int adultCountInt = Integer.parseInt(adultCount);
        int childCountInt = Integer.parseInt(childCount);
        int totalGuestCount = adultCountInt + childCountInt;

        String query = "SELECT * FROM public.room as r INNER JOIN public.hotel as h";
        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();

        joinWhere.add("h.hotel_id = r.hotel_id INNER JOIN public.season AS s ON s.season_hotel_id = h.hotel_id");


        strt_date = LocalDate.parse(strt_date, DateTimeFormatter.ofPattern("dd/MM/yyy")).toString(); // disardan gelen tarih formatini patternini degisiyoruz
        fnsh_date = LocalDate.parse(fnsh_date, DateTimeFormatter.ofPattern("dd/MM/yyy")).toString(); // disardan gelen tarih formatini patternini degisiyoruz

        if (!hotelName.isEmpty()){
            where.add("h.hotel_name = '" + hotelName +"'");
        }
        if (!hotelCity.isEmpty()){
            where.add("h.hotel_city = '" + hotelCity +"'");
        }
        if (!adultCount.isEmpty() && !childCount.isEmpty() ){
            where.add("(r.bed_count >= " + (totalGuestCount) + ")");
        }
        where.add("('" + strt_date + "' BETWEEN s.season_strt AND s.season_fnsh)");
        where.add("('" + fnsh_date + "' BETWEEN s.season_strt AND s.season_fnsh)");
        where.add("r.stock > 0");

        String whereStr = String.join(" AND " , where);
        String joinStr = String.join(" AND " , joinWhere);

        if (joinStr.length() > 0){
            query += " ON " + joinStr;
        }
        if (whereStr.length() > 0){
            query += " WHERE " + whereStr;
        }

        return selectByQuery(query);
    }
    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                rooms.add(this.match(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println(query);
        return rooms;
    }
}
