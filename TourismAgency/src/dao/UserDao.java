package dao;

import core.Db;
import entity.Role;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;

    public UserDao(){
        this.con = Db.getInstance();
    }

    public ArrayList<User> findAll(){
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }
    public ArrayList<User> findEmployee(){
        ArrayList<User> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM public.user WHERE user_role = 'EMPLOYEE'";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                employeeList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeeList;
    }

    public User findByLogin(String username, String password){ // kullanici adi sifre yollayip boyle bir kullanici olup olmadigini sorguluyoruz
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_pass = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    //gets user by id
    public User getById(int id) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ? ";
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
//matching object to database
    public User match(ResultSet rs){
        User obj = new User();
        try {
            obj.setId(rs.getInt("user_id"));
            obj.setUsername(rs.getString("user_name"));
            obj.setPassword(rs.getString("user_pass"));
            String roleStr = rs.getString("user_role");
            obj.setRole(Role.fromString(roleStr));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    //initiates query
    public ArrayList<User> selectByQuery(String query){
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return userList;
    }
    //deletes user
    public boolean delete(int id){
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    //save user
    public boolean save(User user){
        String query = "INSERT INTO public.user (user_name, user_pass, user_role) VALUES (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    //update user
    public boolean update(User user){
        String query = "UPDATE public.user SET user_name = ? , user_pass = ? , user_role = ? WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());
            pr.setInt(4, user.getId());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
