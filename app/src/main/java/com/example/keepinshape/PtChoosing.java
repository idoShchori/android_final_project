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


public class PtChoosing extends AppCompatActivity {

    private Button Baror_BTN;
    private Button soldier_pt_BTN;
    private MaterialButton workout_BTN_back;
    private AlreadyBuiltExercises abe=new AlreadyBuiltExercises();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final ArrayList<Exercise> exercises = new ArrayList<>();
    private final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private  Exercise ex = new Exercise();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt_choosing);
        findView();
        addExistedEx();


        Baror_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PtChoosing.this, PracticeActivity.class);
                startActivity(intent);
                ex=abe.getBarorExercise();
                exercises.add(ex);
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);

            }
        });
        soldier_pt_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PtChoosing.this, PracticeActivity.class);
                startActivity(intent);
                ex=abe.getFullyLoadedExercise();
                exercises.add(ex);
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);


            }
        });
        workout_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PtChoosing.this, PracticeActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }

    private void findView() {
        Baror_BTN=findViewById(R.id.Baror_BTN);
        soldier_pt_BTN=findViewById(R.id.soldier_pt_BTN);
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
