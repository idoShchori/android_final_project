package com.example.keepinshape;

import com.example.keepinshape.model.Exercise;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class AlreadyBuiltExercises {

    private Calendar cal = Calendar.getInstance();
    private Exercise exercise=new Exercise();

    public AlreadyBuiltExercises() {
    }
    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


    public Exercise getLRSExercise() {
        exercise.setType("Long Range Sniper");
        exercise.setDate(date);
        exercise.setWeapon("hs");
        exercise.setAmmoQuan("15");
        exercise.setDistance("1300m");
        exercise.setHits("0");
        exercise.setTimeInSec("0");
        exercise.setKey(UUID.randomUUID().toString());
        return exercise;
    }
    public Exercise getMRSExercise() {
        exercise.setType("Mediumm Range Sniper");
        exercise.setDate(date);
        exercise.setWeapon("mrad");
        exercise.setAmmoQuan("20");
        exercise.setDistance("700m");
        exercise.setHits("0");
        exercise.setTimeInSec("0");
        exercise.setKey(UUID.randomUUID().toString());
        return exercise;
    }
    public Exercise getCTRExercise() {
        exercise.setType("Counter Terror Range Sniper");
        exercise.setDate(date);
        exercise.setWeapon("m24");
        exercise.setAmmoQuan("20");
        exercise.setDistance("200m");
        exercise.setHits("0");
        exercise.setTimeInSec("0");
        exercise.setKey(UUID.randomUUID().toString());
        return exercise;
    }
    public Exercise getM4Exercise() {
        exercise.setType("Medium Counter Terror");
        exercise.setDate(date);
        exercise.setWeapon("m4");
        exercise.setAmmoQuan("60");
        exercise.setDistance("100m");
        exercise.setHits("0");
        exercise.setTimeInSec("0");
        exercise.setKey(UUID.randomUUID().toString());
        return exercise;
    }
    public Exercise getGlockExercise() {
        exercise.setType("Close Counter Terror");
        exercise.setDate(date);
        exercise.setWeapon("glock");
        exercise.setAmmoQuan("60");
        exercise.setDistance("30m");
        exercise.setHits("0");
        exercise.setTimeInSec("0");
        exercise.setKey(UUID.randomUUID().toString());
        return exercise;
    }
    public Exercise getBarorExercise() {
        exercise.setType("Bar-Or");
        exercise.setDate(date);
        exercise.setWeapon("m4");
        exercise.setAmmoQuan("0");
        exercise.setDistance("3000m");
        exercise.setHits("0");
        exercise.setTimeInSec("0");
        exercise.setKey(UUID.randomUUID().toString());
        return exercise;
    }
    public Exercise getFullyLoadedExercise() {
        exercise.setType("Fully Loaded Satchel");
        exercise.setDate(date);
        exercise.setWeapon("m4");
        exercise.setAmmoQuan("0");
        exercise.setDistance("3000m");
        exercise.setHits("0");
        exercise.setTimeInSec("0");
        exercise.setKey(UUID.randomUUID().toString());
        return exercise;
    }
    public Exercise getBreachAndEntriyExercise() {
        exercise.setType("Breach And Entry");
        exercise.setDate(date);
        exercise.setWeapon("m4");
        exercise.setAmmoQuan("0");
        exercise.setDistance("0");
        exercise.setHits("0");
        exercise.setTimeInSec("0");
        exercise.setKey(UUID.randomUUID().toString());
        return exercise;
    }


}
