package business;

import core.Helper;
import dao.UserDao;
import entity.Role;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }
    public User findByLogin(String username, String password){
        return this.userDao.findByLogin(username,password);
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList){
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user : userList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }
    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }

    public ArrayList<User> searchForTable(Role role){
        String select = "SELECT * FROM public.user WHERE user_role = '" + role.toString() +"'";
        return this.userDao.selectByQuery(select);
    }
    public User getById (int id){
        return this.userDao.getById(id);
    }
    public boolean delete(int id){
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID nolu çalışan yok");
            return false;
        }
        return this.userDao.delete(id);
    }

    public boolean save(User user){
        if (user.getRole() == null && user.getPassword() == null && user.getUsername() == null){
            Helper.showMsg("error");
        }
        return this.userDao.save(user);
    }
    public boolean update(User user){
        if (user.getRole() == null && user.getPassword() == null && user.getUsername() == null){
            Helper.showMsg("notFound");
        }
        return this.userDao.update(user);
    }
}
