package com.ogs.m_bus;

/**
 * Created by admin on 3/30/2018.
 */
public class uinfo {
    private String from;
    private String name;
    private String to;
    private String seats;
    private String time;
    private String price;

    public uinfo() {
    }
    public uinfo(String From,String To, String Seats,String Name,String Time,String Price) {
        from=From;
        to=To;
        seats=Seats;
        name=Name;
        time=Time;
        price=Price;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getTime() {
        return time;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
public void setName(String name){this.name=name;}
    public void setTo(String to) {
        this.to = to;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
