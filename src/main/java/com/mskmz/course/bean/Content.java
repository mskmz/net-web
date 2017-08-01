package com.mskmz.course.bean;

import com.mskmz.course.utils.Utils;

import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by mskmz on 2017/7/30.
 */
public class Content {
    int id;
    double price ;
    String title ;
    String image ;
    String summary;
    String detail;
    String buyPrice;
    boolean isSell;
    boolean isBuy;
    int buyNum;
    int saleNum;

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", summary='" + summary + '\'' +
                ", detail='" + detail + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", isSell=" + isSell +
                ", isBuy=" + isBuy +
                ", buyNum=" + buyNum +
                ", saleNum=" + saleNum +
                '}';
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        if(buyNum>0){
            isBuy=true;
        }else{
            isBuy=false;
        }
        this.buyNum = buyNum;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        if(saleNum>0){
            isSell=true;
        }else{
            isSell=false;
        }
        this.saleNum = saleNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(Blob image) {
        try {
            this.image = new String(image.getBytes((long)1, (int)image.length()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(image==null){
            this.image="";
        }
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(Blob detail) {
        try {
            this.detail = new String(detail.getBytes((long)1, (int)detail.length()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(detail==null){
            this.detail="";
        }
    }
}
