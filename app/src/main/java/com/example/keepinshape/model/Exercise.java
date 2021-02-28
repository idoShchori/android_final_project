package com.example.keepinshape.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Exercise implements Parcelable {
    private String key = "";
    private String type;
    private String weapon;
    private String distance;
    private String ammoQuan;
    private String date;
    private String hits;
    private String timeInSec;

    public Exercise(){ }

    public Exercise(String type, String weapon, String distance, String ammoQuan, String date, String hits, String timeInSec) {
        this.type = type;
        this.weapon = weapon;
        this.distance = distance;
        this.ammoQuan = ammoQuan;
        this.date = date;
        this.hits = "0";
        this.timeInSec = "0";
    }

    protected Exercise(Parcel in) {
        key = in.readString();
        type = in.readString();
        weapon = in.readString();
        distance = in.readString();
        ammoQuan = in.readString();
        date = in.readString();
        hits = in.readString();
        timeInSec = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getTimeInSec() {
        return timeInSec;
    }

    public void setTimeInSec(String timeInSec) {
        this.timeInSec = timeInSec;
    }


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAmmoQuan() {
        return ammoQuan;
    }

    public void setAmmoQuan(String ammoQuan) {
        this.ammoQuan = ammoQuan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(type);
        dest.writeString(weapon);
        dest.writeString(distance);
        dest.writeString(ammoQuan);
        dest.writeString(date);
        dest.writeString(hits);
        dest.writeString(timeInSec);
    }
}
