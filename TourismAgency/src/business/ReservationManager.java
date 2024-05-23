package business;

import core.Db;
import dao.ReservationDao;
import entity.Reservation;
import entity.Room;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ReservationManager {

    private ReservationDao reservationDao;

    public ReservationManager(){
        this.reservationDao = new ReservationDao();
    }

    public ArrayList<Reservation> findAll(){
        return this.reservationDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for (Reservation res : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = res.getId();
            rowObject[i++] = res.getRoomId();
            rowObject[i++] = res.getCheckInDate();
            rowObject[i++] = res.getCheckOutDate();
            rowObject[i++] = res.getTotalPrice();
            rowObject[i++] = res.getGuestCount();
            rowObject[i++] = res.getGuestName();
            rowObject[i++] = res.getGuestId();
            rowObject[i++] = res.getGuestMail();
            rowObject[i++] = res.getGuestMP();
            rowObject[i++] = res.getHotelId();
            roomRowList.add(rowObject);
        }
        return roomRowList;
    }
    //calculates the day count between reservation start and end
    public int calculateDay(String startDate, String endDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date1 = LocalDate.parse(startDate, dtf);
            LocalDate date2 = LocalDate.parse(endDate, dtf);
            return (int) ChronoUnit.DAYS.between(date1, date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean save(Reservation reservation){
        return this.reservationDao.save(reservation);
    }
    public boolean update(Reservation reservation){
        return this.reservationDao.update(reservation);
    }
    public boolean delete(int id){
        return this.reservationDao.delete(id);
    }
    public boolean decreaseStock(int roomId){
        return this.reservationDao.decreaseStock(roomId);
    }
    public boolean increaseStock(int roomId){
        return this.reservationDao.increaseStock(roomId);
    }
    public Reservation getById(int id){
        return this.reservationDao.getById(id);
    }

}
