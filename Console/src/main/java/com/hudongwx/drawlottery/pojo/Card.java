package com.hudongwx.drawlottery.pojo;

public class Card {
    private Integer id;

    private String cardNum;

    private String password;

    private Integer corporation;

    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getCorporation() {
        return corporation;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}