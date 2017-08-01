package com.mskmz.course.bean;

import java.sql.Date;

/**
 * Created by mskmz on 2017/7/30.
 */
public class Trx {
    int id;
    String contentid;
    String personid;
    String price;
    long time;
    String title;
    String image;
    String buyPrice;
    int buyNum;
    long buyTime;
    double total;

    @Override
    public String toString() {
        return "Trx{" +
                "id=" + id +
                ", contentid='" + contentid + '\'' +
                ", personid='" + personid + '\'' +
                ", price='" + price + '\'' +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", buyNum=" + buyNum +
                ", buyTime=" + buyTime +
                ", total=" + total +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
