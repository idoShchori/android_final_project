package com.example.keepinshape;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keepinshape.model.Exercise;
import com.example.keepinshape.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CtChoosing extends AppCompatActivity {

    private Button ct_BTN_glock;
    private Button ct_BTN_m4;
    private Button ct_BTN_bne;
    private MaterialButton workout_BTN_back;
    private AlreadyBuiltExercises abe=new AlreadyBuiltExercises();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final ArrayList<Exercise> exercises = new ArrayList<>();
    private final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private  Exercise ex = new Exercise();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_choosing);
        findView();
        addExistedEx();


        ct_BTN_glock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CtChoosing.this, PracticeActivity.class);
                startActivity(intent);
                ex=abe.getGlockExercise();
                exercises.add(ex);
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);
            }
        });
        ct_BTN_m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CtChoosing.this, PracticeActivity.class);
                startActivity(intent);
                ex=abe.getM4Exercise();
                exercises.add(ex);
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);
            }
        });
        ct_BTN_bne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CtChoosing.this, PracticeActivity.class);
                startActivity(intent);
                ex=abe.getBreachAndEntriyExercise();
                exercises.add(ex);
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);

            }
        });
        workout_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CtChoosing.this, PracticeActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }

    private void findView() {
        ct_BTN_glock = findViewById(R.id.ct_BTN_glock);
        ct_BTN_m4 = findViewById(R.id.ct_BTN_m4);
        ct_BTN_bne = findViewById(R.id.ct_BTN_bne);
        workout_BTN_back = findViewById(R.id.workout_BTN_back);

    }
    public void addExistedEx(){
        database.getReference("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.child("exercises").getChildren()) {
                    //parse the data to Exercise
                    Exercise data = snap.getValue(Exercise.class);
                    exercises.add(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

