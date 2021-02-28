package com.example.keepinshape;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keepinshape.model.Exercise;
import com.example.keepinshape.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

public class MakingExActivity extends AppCompatActivity {
    private EditText ex_EDT_type;
    private EditText ex_EDT_ammoAmount;
    private EditText ex_EDT_date;
    private EditText ex_EDT_distance;
    private Button publish_BTN_ex;
    private String weapon;
    private int i = 0;
    private FirebaseAuth mAuth;
    private final Exercise ex = new Exercise();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference reff = database.getReference("users");
    private final ArrayList<Exercise> exercises = new ArrayList<>();
    private final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_ex);
        findViews();
        initViews();
        weapon = getIntent().getStringExtra("weapon");

        database.getReference("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.child("exercises").getChildren()) {
                    //parse the data to Exercise
                    Exercise data = snap.getValue(Exercise.class);
                    exercises.add(data);
                }
                publish_BTN_ex.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void findViews() {
        ex_EDT_type = findViewById(R.id.ex_EDT_type);
        ex_EDT_ammoAmount = findViewById(R.id.ex_EDT_ammoAmount);
        ex_EDT_date = findViewById(R.id.ex_EDT_date);
        ex_EDT_distance = findViewById(R.id.ex_EDT_distance);
        publish_BTN_ex = findViewById(R.id.publish_BTN_ex);
    }

    private void initViews() {
        publish_BTN_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateExercises();
                Intent intent = new Intent(MakingExActivity.this, MenuActivity.class);
                startActivity(intent);
            }
            //}
        });

        Toast.makeText(MakingExActivity.this, "Exercise inserted!", Toast.LENGTH_LONG);
    }


    private void generateExercises() {
        int op = 1;

        Toast.makeText(MakingExActivity.this, "Fill all parameters", Toast.LENGTH_SHORT);
        ex.setType(ex_EDT_type.getText().toString().trim());
        ex.setWeapon(weapon);
        ex.setAmmoQuan(ex_EDT_ammoAmount.getText().toString().trim());
        ex.setDistance(ex_EDT_distance.getText().toString().trim());


            if (ex_EDT_date.getText().toString().trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
                ex.setDate(ex_EDT_date.getText().toString().trim());

            } else {
                Toast.makeText(MakingExActivity.this, "illegal date ,date set to today", Toast.LENGTH_LONG).show();
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                ex.setDate(date);

            }




        ex.setKey(UUID.randomUUID().toString());
        ex.setTimeInSec("0");
        ex.setHits("0");
        exercises.add(ex);
        Toast.makeText(MakingExActivity.this, "Exercise saved!", Toast.LENGTH_LONG).show();


        database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);

    }




}




