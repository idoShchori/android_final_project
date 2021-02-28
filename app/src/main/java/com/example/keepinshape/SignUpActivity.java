package com.example.keepinshape;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keepinshape.model.Exercise;
import com.example.keepinshape.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText singup_EDT_username;
    private EditText singup_EDT_userEmail;
    private EditText singup_EDT_password;
    // private Spinner singup_SNR_Usertype;
    private Button singup_BTN_singin;
    private Button singup_BTN_back;

    String userId;
    private DatabaseReference dbr;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        findViews();
        dbr = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

       /* ArrayAdapter<CharSequence> userAdapter = ArrayAdapter.createFromResource(this, R.array.users_arrays, android.R.layout.simple_spinner_item);
        userAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        singin_SNR_Usertype.setAdapter(userAdapter);
        singin_SNR_Usertype.setOnItemSelectedListener(this);
        */
        singup_BTN_singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });

        singup_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    // Insert new user details
    private void RegisterUser() {
        // Get users data
        final String userName = singup_EDT_username.getText().toString().trim();
        final String userEmail = singup_EDT_userEmail.getText().toString().trim();
        String password = singup_EDT_password.getText().toString().trim();
        final ArrayList<Exercise> exercises=new ArrayList<Exercise>();



        // Check if there are errors and return if there is
        if (checkErrors(userName,
                userEmail,
                password
        )) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            User user = new User(
                                    mAuth.getCurrentUser().getUid(),
                                    userName,
                                    userEmail,
                                    exercises
                            );
                            dbr.child("users")
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(user);

                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Log.i("TAG", "Create user with email encounter a problem: ");
                        }
                    }

                });
    }

    // Check if there are errors and return true if there is
    private boolean checkErrors(String name, String email,
                                String password) {

        // Check if there is no empty value
        if (checkEmpties(name,email,password)) {
            return true;
        }

        // Validate password length
        if (password.length() < 6) {
            singup_EDT_password.setError("Password Must include at least 6 characters. ");
            return true;
        } else {
            return false;
        }
    }

    private boolean checkEmpties(String name,
                                 String password, String email) {
        boolean to_return = false;


        if (checkEmptyTxt(name, singup_EDT_username, "Name ")) {
            to_return = true;
        }
        if (checkEmptyTxt(password, singup_EDT_password, "Password ")) {
            to_return = true;
        }

        if (checkEmptyTxt(email, singup_EDT_userEmail, "Email ")) {
            to_return = true;
        }

        return to_return;
    }

    // Check if edit text is empty
    private boolean checkEmptyTxt(String str, EditText editText, String missing) {
        if (TextUtils.isEmpty(str)) {
            editText.setError(missing + "is Required. ");
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        FirebaseAuth.getInstance().signOut();
        super.onDestroy();
    }

    private void findViews() {
        singup_EDT_username = findViewById(R.id.singup_EDT_username);
        singup_EDT_userEmail = findViewById(R.id.singup_EDT_userEmail);
        singup_EDT_password = findViewById(R.id.singup_EDT_password);
        singup_BTN_singin = findViewById(R.id.singup_BTN_singin);
        singup_BTN_back = findViewById(R.id.singin_BTN_back);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

}
