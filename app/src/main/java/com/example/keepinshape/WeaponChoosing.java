package com.example.keepinshape;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WeaponChoosing extends BaseActivity {

    private ImageButton m24_BTN;
    private ImageButton m4_BTN;
    private ImageButton mrad_BTN;
    private ImageButton glock_BTN;
    private ImageButton hs_BTN;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_weapon_choosing);

        findView();
        initViews();


        m24_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeaponChoosing.this, MakingExActivity.class);
                intent.putExtra("weapon","m24");
                startActivity(intent);
                finish();
            }
        });
        m4_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeaponChoosing.this, MakingExActivity.class);
                intent.putExtra("weapon","m4");
                startActivity(intent);
                finish();
            }
        });
        mrad_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeaponChoosing.this, MakingExActivity.class);
                intent.putExtra("weapon","mrad");
                startActivity(intent);
                finish();
            }
        });
        glock_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeaponChoosing.this, MakingExActivity.class);
                intent.putExtra("weapon","glock");
                startActivity(intent);
                finish();
            }
        });
        hs_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeaponChoosing.this, MakingExActivity.class);
                intent.putExtra("weapon","hs");
                startActivity(intent);
                finish();
            }
        });
    }

    private void findView() {
        m24_BTN=findViewById(R.id.m24_BTN);
        m4_BTN=findViewById(R.id.m4_BTN);
        mrad_BTN=findViewById(R.id.mrad_BTN);
        glock_BTN=findViewById(R.id.glock_BTN);
        hs_BTN=findViewById(R.id.hs_BTN);

    }
    private void initViews(){
        setImageById(R.drawable.m24_photo,m24_BTN);
        setImageById(R.drawable.m4_photo,m4_BTN);
        setImageById(R.drawable.mrad_photo,mrad_BTN);
        setImageById(R.drawable.glock_photo,glock_BTN);
        setImageById(R.drawable.hs_photo,hs_BTN);


    }

}

