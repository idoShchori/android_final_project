package com.example.keepinshape;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.keepinshape.fragment.MyExActivity;
import com.example.keepinshape.model.User;

public class MenuActivity extends BaseActivity {

    private ImageView menu_IMBTN_myEx;
    private ImageView menu_IMBTN_practice;
    private ImageView menu_IMBTN_follow;
    private Button menu_BTN_logOut;


    private  static User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        currUser = (User)getIntent().getSerializableExtra(getString(R.string.user_id));
        findView();
        initViews();
        menu_IMBTN_myEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MyExActivity.class);
                startActivity(intent);

            }
        });

        menu_IMBTN_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PracticeActivity.class);
                startActivity(intent);

            }
        });

        menu_IMBTN_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, WeaponChoosing.class);
                startActivity(intent);
            }
        });
        menu_BTN_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    private void logOut() {
        openWelcome();
        finish();
    }

    private void openWelcome() {
        Intent intent = new Intent(MenuActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    private void findView() {
        menu_IMBTN_myEx = findViewById(R.id.menu_IMBTN_myEx);
        menu_IMBTN_practice = findViewById(R.id.menu_IMBTN_practice);
        menu_IMBTN_follow= findViewById(R.id.menu_IMBTN_follow);
        menu_BTN_logOut = findViewById(R.id.menu_BTN_logOut);

    }
    private void initViews() {
        setImageById(R.drawable.calendar_photo,menu_IMBTN_myEx);
        setImageById(R.drawable.reticle_logo,menu_IMBTN_practice);
        setImageById(R.drawable.follow_photo,menu_IMBTN_follow);

    }



}