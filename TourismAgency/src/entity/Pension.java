package entity;

import core.ComboItem;

public class Pension {
    private int id;
    private int hotelId;
    private String pensionType;

    public Pension(int hotelId, String pensionType) {
        this.hotelId = hotelId;
        this.pensionType = pensionType;
    }
    public Pension(int hotelId){
        this.hotelId = hotelId;
    }
    public Pension(){
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getPensionType() {
        return pensionType;
    }
    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }
    public ComboItem getComboItem(){
        ComboItem pensCombo = new ComboItem(this.getId(), "Hotel ID " + this.getHotelId() + " = " + this.pensionType);
        return pensCombo;
    }
}
