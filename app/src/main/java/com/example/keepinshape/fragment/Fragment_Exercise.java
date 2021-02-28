package com.example.keepinshape.fragment;

    import android.app.Fragment;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import com.example.keepinshape.R;
    import com.example.keepinshape.model.Exercise;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;


    import java.util.ArrayList;

    public class Fragment_Exercise extends Fragment {

        public RecyclerView fragment_exe_LST;
        private ArrayList<Exercise>exercises = new ArrayList<>();
        private ExerciseAdapter exerciseAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.fragment_exer_list,container,false);
            findViews(view);
            ExerciseDB(view);
            updateAdapter(view);


            return view;
        }

        private void updateAdapter(View view) {
             exerciseAdapter = new ExerciseAdapter(view.getContext(), exercises);
            exerciseAdapter.setClickListener(new ExerciseAdapter.MyItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(view.getContext(), exercises.get(position).getType(), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onItemRemove(View view, int position){

                    exercises.remove(position);
                    exerciseAdapter.notifyItemRemoved(position);

                }

            });

            fragment_exe_LST.setLayoutManager(new LinearLayoutManager(view.getContext()));
            fragment_exe_LST.setAdapter(exerciseAdapter);
        }

        public void findViews(View view) {
            fragment_exe_LST=view.findViewById(R.id.fragment_exe_LST);
        }


        private void ExerciseDB(final View view) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            database.getReference("users").child(firebaseUser.getUid()).
            addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange (@NonNull DataSnapshot snapshot){
                    for (DataSnapshot snap : snapshot.child("exercises").getChildren()) {
                        //parse the data to Exercise
                        Exercise data = snap.getValue(Exercise.class);
                        exercises.add(data);
                    }
                    exerciseAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled (@NonNull DatabaseError error){
                }
            });

        }
}
