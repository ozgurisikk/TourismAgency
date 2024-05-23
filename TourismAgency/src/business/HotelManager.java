package business;

import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }
    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> hotelRowList = new ArrayList<>();
        for (Hotel hotel : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = hotel.getHotel_id();
            rowObject[i++] = hotel.getHotel_name();
            rowObject[i++] = hotel.getHotel_address();
            rowObject[i++] = hotel.getHotel_city();
            rowObject[i++] = hotel.getHotel_mail();
            rowObject[i++] = hotel.getHotel_phone();
            rowObject[i++] = hotel.getHotel_star();
            rowObject[i++] = hotel.isHotel_car_park();
            rowObject[i++] = hotel.isHotel_wifi();
            rowObject[i++] = hotel.isHotel_pool();
            rowObject[i++] = hotel.isHotel_fitness();
            rowObject[i++] = hotel.isHotel_spa();
            rowObject[i++] = hotel.isHotel_room_service();
            rowObject[i++] = hotel.isHotel_concierge();

            hotelRowList.add(rowObject);
        }
        return hotelRowList;
    }
    public Hotel getById(int id){
        return this.hotelDao.getById(id);
    }
    public boolean save(Hotel hotel){
        return this.hotelDao.save(hotel);
    }
    public boolean update(Hotel hotel){
        return this.hotelDao.update(hotel);
    }
    public boolean delete(int id){
        return this.hotelDao.delete(id);
    }
}
