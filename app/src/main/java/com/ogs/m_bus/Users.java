package com.ogs.m_bus;

/**
 * Created by admin on 3/23/2018.
 */

public class Users {
    private String email,password,name,phone;
  private   String money;

    public Users() {
    }

    public Users(String email, String password, String name, String phone,String Money) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.money=Money;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMoney() {
        return money;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setMoney(String money) {
        this.money = money;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
