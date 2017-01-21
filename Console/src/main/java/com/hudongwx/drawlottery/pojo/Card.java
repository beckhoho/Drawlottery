package com.hudongwx.drawlottery.pojo;

public class Card {
    public static Integer CORPORATION_CMCC = 0;
    public static Integer CORPORATION_CUCC = 1;
    public static Integer CORPORATION_CTCC = 2;

    public static int[] CORS = new int[]{CORPORATION_CMCC, CORPORATION_CUCC, CORPORATION_CTCC};

    public static Integer MONEY_10 = 10;
    //    public static Integer MONEY_20 = 20;
    public static Integer MONEY_30 = 30;
    public static Integer MONEY_50 = 50;
    public static Integer MONEY_100 = 100;
    public static int[] MONEYS = new int[]{MONEY_10, MONEY_30, MONEY_50, MONEY_100};


    private Integer id;

    private String cardNum;

    private String password;

    private Integer corporation;

    private Integer money;

    private Integer state;

    private Long commodityId;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }
}