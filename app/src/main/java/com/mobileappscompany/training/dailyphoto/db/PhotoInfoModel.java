package com.mobileappscompany.training.dailyphoto.db;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Author mpamplona
 * created on 3/4/2016
 *
 * Photos to be persisted in DB
 *
 */
public class PhotoInfoModel extends SugarRecord {

    private String path;
    private Date createDate;
    private int rate;
    private String place;

    public PhotoInfoModel() {

    }

    public PhotoInfoModel(String path, Date createDate, int rate, String place){
        this.setPath(path);
        this.setCreateDate(createDate);
        this.setRate(rate);
        this.setPlace(place);
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
