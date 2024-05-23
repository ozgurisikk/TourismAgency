package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private Connection con;

    public PensionDao() {
        this.con = Db.getInstance();
    }
    public ArrayList<Pension> findAll(){
        ArrayList<Pension> pensionList = new ArrayList<>();
        String sql = "SELECT * FROM public.pension INNER JOIN public.hotel ON public.pension.pension_hotel_id = public.hotel.hotel_id ORDER BY pension_hotel_id ASC";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                pensionList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pensionList;
    }
    public Pension match(ResultSet rs){
        Pension obj = new Pension();
        try {
            obj.setId(rs.getInt("pension_id"));
            obj.setHotelId(rs.getInt("pension_hotel_id"));
            obj.setPensionType(rs.getString("pension_type"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public boolean save(Pension pension){
        String query = "INSERT INTO public.pension (pension_hotel_id, pension_type) VALUES (?, ?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,pension.getHotelId());
            pr.setString(2, pension.getPensionType());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.pension WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Pension pension){
        String query = "UPDATE public.pension SET pension_type = ? WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, pension.getPensionType());
            pr.setInt(2, pension.getId());

            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ? ";
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
