package com.example.keepinshape.fragment;

import androidx.annotation.NonNull;

import com.example.keepinshape.model.Exercise;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExerciseDB {


    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private static ArrayList<Exercise> exercises=new ArrayList<>();
    public static ArrayList<Exercise> exerciseGenerator(){


        database.getReference("users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
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
        return exercises;
    }


}
