package com.example.keepinshape.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Parcelable {
    private String uid = "";
    private String name;
    private String email;
    private ArrayList<Exercise> exercises=new ArrayList<Exercise>();


    public User() { }

    public User(String uid,String name, String email,ArrayList<Exercise>  exercises) {
        this.uid=uid;
        this.name = name;
        this.email = email;
        this.exercises=exercises;


    }

    protected User(Parcel in) {
        uid = in.readString();
        name = in.readString();
        email = in.readString();
        exercises = in.createTypedArrayList(Exercise.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise e) {
        exercises.add(e);
    }
    public void removeExercise(ArrayList<Exercise>  exercises ,Exercise e){
        exercises.remove(e);

    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeTypedList(exercises);
    }
}
