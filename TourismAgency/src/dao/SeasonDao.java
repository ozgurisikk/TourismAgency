package dao;

import core.ComboItem;
import core.Db;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private Connection con;

    public SeasonDao(){
        this.con = Db.getInstance();
    }

    public ArrayList<Season> findAll(){
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.season ORDER BY season_hotel_id ASC";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                seasonList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return seasonList;
    }
    //gets seasion by id
    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_id = ? ";
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
    //matches object from database
    public Season match(ResultSet rs){
        Season obj = new Season();
        try {
            obj.setId(rs.getInt("season_id"));
            obj.setHotelId(rs.getInt("season_hotel_id"));
            obj.setStartDate(LocalDate.parse(rs.getString("season_strt")));
            obj.setFinishDate(LocalDate.parse(rs.getString("season_fnsh")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    //updates season
    public boolean update(Season season){
        String query = "UPDATE public.season SET season_strt = ?, season_fnsh = ? WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setDate(1, Date.valueOf(season.getStartDate()));
            pr.setDate(2, Date.valueOf(season.getFinishDate()));
            pr.setInt(3, season.getId());

            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    //save season
    public boolean save(Season season){
        String query = "INSERT INTO public.season (season_hotel_id, season_strt, season_fnsh) VALUES (?, ?, ?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    //deletes season
    public boolean delete(int id){
        String query = "DELETE FROM public.season WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

}
