package com.example.keepinshape;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class PracticeActivity extends BaseActivity  {


    private ImageButton soldier_IMBTN_exercise;
    private ImageButton ct_IMBTN_exercise;
    private ImageButton sniper_IMBTN_exercise;
    private Button workout_BTN_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        findView();
        initViews();

        soldier_IMBTN_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticeActivity.this, PtChoosing.class);
                startActivity(intent);
                finish();
            }
        });

        ct_IMBTN_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticeActivity.this, CtChoosing.class);
                startActivity(intent);
                finish();
            }
        });
        sniper_IMBTN_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticeActivity.this, SniperChoosing.class);
                startActivity(intent);
                finish();
            }
        });

        workout_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticeActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void initViews() {
        setImageById(R.drawable.sniper_photo,sniper_IMBTN_exercise);
        setImageById(R.drawable.soldier_photo,soldier_IMBTN_exercise);
        setImageById(R.drawable.ct_photo,ct_IMBTN_exercise);
    }





    private void findView() {
        workout_BTN_back = findViewById(R.id.workout_BTN_back);
        soldier_IMBTN_exercise = findViewById(R.id.soldier_IMBTN_exercise);
        ct_IMBTN_exercise = findViewById(R.id.ct_IMBTN_exercise);
        sniper_IMBTN_exercise = findViewById(R.id.sniper_IMBTN_exercise);
    }
}
