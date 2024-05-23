package business;

import dao.SeasonDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private SeasonDao seasonDao;

    public SeasonManager(){
        this.seasonDao = new SeasonDao();
    }
    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> seasonRowList = new ArrayList<>();
        for (Season season : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = season.getId();
            rowObject[i++] = season.getHotelId();
            rowObject[i++] = season.getStartDate();
            rowObject[i++] = season.getFinishDate();

            seasonRowList.add(rowObject);
        }
        return seasonRowList;
    }
    public Season getById(int id){
        return this.seasonDao.getById(id);
    }
    public boolean update(Season season){
        return this.seasonDao.update(season);
    }
    public boolean save(Season season){
        return this.seasonDao.save(season);
    }
    public boolean delete(int id){
        return this.seasonDao.delete(id);
    }

}
