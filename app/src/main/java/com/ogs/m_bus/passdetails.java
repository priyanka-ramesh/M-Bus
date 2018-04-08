package com.ogs.m_bus;

/**
 * Created by admin on 4/2/2018.
 */

public class passdetails {
    private String from;
    private String name;
    private String to;
    private String time;
    private String price;
    public passdetails() {
    }
    public passdetails(String From,String To, String Time,String Name,String Price) {
        from=From;
        to=To;
        time=Time;
        name=Name;
    }
    public String getName() {
        return name;
    }
    public String getFrom() {
        return from;
    }
    public String getPrice() {
        return price;
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

    public String getTime() {
        return time;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
