package com.example.keepinshape;

import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class BaseActivity extends AppCompatActivity {

    public  void setImageById(int id, ImageView view) {
        // set image with glide
        Glide
                .with(view.getContext())
                .load(id)
                .into(view);
    }
}
