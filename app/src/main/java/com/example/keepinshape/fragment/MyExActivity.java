package com.example.keepinshape.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.keepinshape.R;
import com.example.keepinshape.fragment.ExerciseAdapter;
import com.example.keepinshape.fragment.ExerciseDB;
import com.example.keepinshape.fragment.Fragment_Exercise;
import com.example.keepinshape.model.Exercise;

import java.util.ArrayList;

public class MyExActivity extends AppCompatActivity {
    public RecyclerView fragment_exe_LST;

    private FragmentManager fragment_manager;
    private Fragment_Exercise fragment_exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ex);
        loadFragment();
//        fragment_exe_LST=findViewById(R.id.fragment_exe_LST);

//    final ArrayList<Exercise> exercises = ExerciseDB.exerciseGenerator();
//
//        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(this, exercises);
//            exerciseAdapter.setClickListener(new ExerciseAdapter.MyItemClickListener() {
//        @Override
//        public void onItemClick(View view, int position) {
//            Toast.makeText(view.getContext(), exercises.get(position).getType(), Toast.LENGTH_SHORT).show();
//        }
//
//    });
//
//            fragment_exe_LST.setLayoutManager(new LinearLayoutManager(this));
//            fragment_exe_LST.setAdapter(exerciseAdapter);


}
    private void loadFragment() {
        fragment_manager=getFragmentManager();
        fragment_exercise=new Fragment_Exercise();
        FragmentTransaction fragmentTransaction = fragment_manager.beginTransaction();
        // replace the FrameLayout with new Fragment

        fragmentTransaction.replace(R.id.myex_LAY_frame, fragment_exercise);
        fragmentTransaction.commit(); // save the changes

    }

    }

