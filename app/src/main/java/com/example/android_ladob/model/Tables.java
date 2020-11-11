package com.example.android_ladob.model;

public class Tables {

    private Integer id;
    private String code;
    private Integer seats;

    public Tables(String code, Integer seats) {
        this.code = code;
        this.seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
