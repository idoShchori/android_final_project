package com.example.keepinshape.fragment;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.keepinshape.R;
import com.example.keepinshape.model.Exercise;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {

    private List<Exercise> exercises;
    private LayoutInflater mInflater;
    private com.example.keepinshape.fragment.ExerciseAdapter.MyItemClickListener mClickListener;
    private int position;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private Exercise ex = new Exercise();
    private Context context;


    ExerciseAdapter(Context context, List<Exercise> _exercises) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.exercises = _exercises;
    }

    @Override
    public com.example.keepinshape.fragment.ExerciseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_exercise, parent, false);
        return new com.example.keepinshape.fragment.ExerciseAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final com.example.keepinshape.fragment.ExerciseAdapter.MyViewHolder holder, final int _position) {
        Exercise exercise = exercises.get(_position);
        holder.exe_LBL_type.setText("Type: " + exercise.getType());
        holder.exe_LBL_range.setText("Distance: " + exercise.getDistance() + "m");
        holder.exe_LBL_date.setText("Date: " + exercise.getDate());
        holder.exe_LBL_ammo.setText("Ammo Quantity: " + exercise.getAmmoQuan());
        holder.exe_LBL_hits.setText("Hits: " + exercise.getHits());
        holder.exe_LBL_time.setText("Time: " + exercise.getTimeInSec());

      //  String currWeapon = "R.drawable."+(exercise.getWeapon() + "_photo");
        String uri = "@drawable/"+exercise.getWeapon()+"_photo";
        int imageResource =  context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable logoDrawable = ResourcesCompat.getDrawable(context.getResources(),imageResource,null);

        Glide.with(mInflater.getContext())
                .load(logoDrawable)
                .centerInside()
                .into(holder.exe_weapon_IMG);




        holder.exercise_BTN_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex = exercises.get(_position);
                mClickListener.onItemRemove(v, _position);
                removeEx();

            }
        });

    }


    @Override
    public int getItemCount() {
        return exercises.size();
    }


    public void setClickListener(com.example.keepinshape.fragment.ExerciseAdapter.MyItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface MyItemClickListener {
        void onItemClick(View view, int position);

        void onItemRemove(View view, int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView exe_LBL_type;
        TextView exe_LBL_hits;
        TextView exe_LBL_range;
        ImageView exe_weapon_IMG;
        TextView exe_LBL_ammo;
        TextView exe_LBL_date;
        TextView exe_LBL_time;
        MaterialButton exercise_BTN_delete;

        MyViewHolder(View itemView) {
            super(itemView);
            exe_LBL_type = itemView.findViewById(R.id.exe_LBL_type);
            exe_LBL_hits = itemView.findViewById(R.id.exe_LBL_hits);
            exe_LBL_range = itemView.findViewById(R.id.exe_LBL_range);
            exe_weapon_IMG = itemView.findViewById(R.id.exe_weapon_IMG);
            exe_LBL_ammo = itemView.findViewById(R.id.exe_LBL_ammo);
            exe_LBL_time = itemView.findViewById(R.id.exe_LBL_time);
            exercise_BTN_delete = itemView.findViewById(R.id.exercise_BTN_delete);
            exe_LBL_date = itemView.findViewById(R.id.exe_LBL_date);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(view, getAdapterPosition());

                    }
                }
            });

        }
    }


    public void removeEx() {
        database.getReference("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Exercise> exercises = new ArrayList<>();

                for (DataSnapshot snap : snapshot.child("exercises").getChildren()) {
                    //parse the data to Exercise
                    Exercise data = snap.getValue(Exercise.class);
                    exercises.add(data);
                }

                database.getReference("users").child(firebaseUser.getUid()).child("exercises").removeValue();

                //remove the selected exercise by key
                for (int i = 0; i < exercises.size(); i++) {
                    if (exercises.get(i).getKey().equals(ex.getKey())) {
                        exercises.remove(i);
                        break;
                    }
                }
                database.getReference("users").child(firebaseUser.getUid()).child("exercises").setValue(exercises);

                ExerciseAdapter.this.exercises = exercises;
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}

