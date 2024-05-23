package core;

import entity.User;
import entity.Role;

import java.time.LocalDate;
import java.util.Date;

public class ComboItem {
    private int key;
    private String value;
    private int hotelId;
    private LocalDate start;
    private LocalDate finish;
    private String textSeason;

    public ComboItem(int key, String value){
        this.key = key;
        this.value = value;
    }
    public ComboItem(int key, int hotelId, LocalDate start, LocalDate finish, String textSeason){
        this.key = key;
        this.hotelId = hotelId;
        this.start = start;
        this.finish = finish;
        this.textSeason = textSeason;
    }
    public ComboItem(int key, int hotelId, String value){
        this.key = key;
        this.hotelId = hotelId;
        this.value = value;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTextSeason() {
        return textSeason;
    }

    public void setTextSeason(String textSeason) {
        this.textSeason = textSeason;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
