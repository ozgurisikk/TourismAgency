package business;

import dao.PensionDao;
import entity.Hotel;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    private PensionDao pensionDao;

    public PensionManager(){
        this.pensionDao = new PensionDao();
    }
    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> pensionRowList = new ArrayList<>();
        for (Pension pension : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = pension.getId();
            rowObject[i++] = pension.getHotelId();
            rowObject[i++] = pension.getPensionType();

            pensionRowList.add(rowObject);
        }
        return pensionRowList;
    }
    public boolean save (Pension pension){
        return this.pensionDao.save(pension);
    }
    public boolean delete(int id){
        return this.pensionDao.delete(id);
    }
    public boolean update(Pension pension){
        return this.pensionDao.update(pension);
    }
    public Pension getById(int id){
        return this.pensionDao.getById(id);
    }
}
