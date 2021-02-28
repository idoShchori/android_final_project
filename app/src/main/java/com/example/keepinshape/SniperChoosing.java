package com.example.keepinshape;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SniperChoosing extends AppCompatActivity {

    private Button sniper_BTN_longrange;
    private Button sniper_BTN_mediumrange;
    private Button sniper_BTN_ctsniper;
    private MaterialButton workout_BTN_back;
    private AlreadyBuiltExercises abe=new AlreadyBuiltExercises();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final ArrayList<Exercise> exercises = new ArrayList<>();
    private final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private  Exercise ex = new Exercise();






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sniper_choosing);
        findView();
        addExistedEx();


        sniper_BTN_longrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SniperChoosing.this, PracticeActivity.class);
                startActivity(intent);
                ex=abe.getLRSExercise();
                exercises.add(ex);
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);


            }
        });
        sniper_BTN_mediumrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SniperChoosing.this, PracticeActivity.class);
                startActivity(intent);
                ex=abe.getMRSExercise();
                exercises.add(ex);
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);

            }
        });
        sniper_BTN_ctsniper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SniperChoosing.this, PracticeActivity.class);
                startActivity(intent);
                ex=abe.getCTRExercise();
                exercises.add(ex);
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);

            }
        });
        workout_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SniperChoosing.this, PracticeActivity.class);
                startActivity(intent);
            }
        });



    }


    private void findView() {
        sniper_BTN_longrange=findViewById(R.id.sniper_BTN_longrange);
        sniper_BTN_mediumrange=findViewById(R.id.sniper_BTN_mediumrange);
        sniper_BTN_ctsniper=findViewById(R.id.sniper_BTN_ctsniper);
        workout_BTN_back=findViewById(R.id.workout_BTN_back);


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

