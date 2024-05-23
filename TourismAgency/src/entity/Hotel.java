package entity;

import core.ComboItem;

public class Hotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_address;
    private String hotel_city;
    private String hotel_mail;
    private String hotel_phone;
    private String hotel_star;
    private boolean hotel_car_park;
    private boolean hotel_wifi;
    private boolean hotel_pool;
    private boolean hotel_fitness;
    private boolean hotel_spa;
    private boolean hotel_room_service;
    private boolean hotel_concierge;

    public Hotel(String hotel_name, String hotel_address, String hotel_city, String hotel_mail, String hotel_phone, String hotel_star, boolean hotel_car_park,
                 boolean hotel_wifi, boolean hotel_pool, boolean hotel_fitness, boolean hotel_spa, boolean hotel_room_service, boolean hotel_concierge) {
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.hotel_city = hotel_city;
        this.hotel_mail = hotel_mail;
        this.hotel_phone = hotel_phone;
        this.hotel_star = hotel_star;
        this.hotel_car_park = hotel_car_park;
        this.hotel_wifi = hotel_wifi;
        this.hotel_pool = hotel_pool;
        this.hotel_fitness = hotel_fitness;
        this.hotel_spa = hotel_spa;
        this.hotel_room_service = hotel_room_service;
        this.hotel_concierge = hotel_concierge;
    }

    public boolean isHotel_concierge() {
        return hotel_concierge;
    }

    public void setHotel_concierge(boolean hotel_concierge) {
        this.hotel_concierge = hotel_concierge;
    }

    public Hotel(){

    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_mail() {
        return hotel_mail;
    }

    public void setHotel_mail(String hotel_mail) {
        this.hotel_mail = hotel_mail;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(String hotel_star) {
        this.hotel_star = hotel_star;
    }

    public boolean isHotel_car_park() {
        return hotel_car_park;
    }

    public void setHotel_car_park(boolean hotel_car_park) {
        this.hotel_car_park = hotel_car_park;
    }

    public boolean isHotel_wifi() {
        return hotel_wifi;
    }

    public void setHotel_wifi(boolean hotel_wifi) {
        this.hotel_wifi = hotel_wifi;
    }

    public boolean isHotel_pool() {
        return hotel_pool;
    }

    public void setHotel_pool(boolean hotel_pool) {
        this.hotel_pool = hotel_pool;
    }

    public boolean isHotel_fitness() {
        return hotel_fitness;
    }

    public void setHotel_fitness(boolean hotel_fitness) {
        this.hotel_fitness = hotel_fitness;
    }

    public boolean isHotel_spa() {
        return hotel_spa;
    }

    public void setHotel_spa(boolean hotel_spa) {
        this.hotel_spa = hotel_spa;
    }

    public boolean isHotel_room_service() {
        return hotel_room_service;
    }

    public void setHotel_room_service(boolean hotel_room_service) {
        this.hotel_room_service = hotel_room_service;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getHotel_id(), this.getHotel_name() + " / Hotel ID = " + this.getHotel_id());
    }
}
