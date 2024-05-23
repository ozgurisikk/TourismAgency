package business;

import dao.RoomDao;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager(){
        this.roomDao = new RoomDao();
    }
    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList){
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for (Room room : roomList){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = room.getId();
            rowObject[i++] = room.getHotelId();
            rowObject[i++] = room.getPensionId();
            rowObject[i++] = room.getSeasonId();
            rowObject[i++] = room.getRoomType();
            rowObject[i++] = room.getStock();
            rowObject[i++] = room.getAdultPrice();
            rowObject[i++] = room.getChildPrice();
            rowObject[i++] = room.getBedCount();
            rowObject[i++] = room.getSquareMeter();
            rowObject[i++] = room.isTelevision();
            rowObject[i++] = room.isMinibar();
            rowObject[i++] = room.isGameConsole();
            rowObject[i++] = room.isCashBox();
            rowObject[i++] = room.isProjector();

            roomRowList.add(rowObject);
        }
        return roomRowList;
    }
    public Room getById(int id){
        return this.roomDao.getById(id);
    }

    public boolean save(Room room){
        return this.roomDao.save(room);
    }
    public boolean update(Room room){
        return this.roomDao.update(room);
    }
    public boolean delete(int id){
        return this.roomDao.delete(id);
    }

    public ArrayList<Room> searchForBooking(String hotelName, String hotelCity, String strt_date, String fnsh_date, String adultCount, String childCount){
        return this.roomDao.searchForBooking(hotelName, hotelCity, strt_date, fnsh_date, adultCount, childCount);
    }
}
