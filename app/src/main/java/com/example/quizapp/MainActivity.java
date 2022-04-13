package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizapp.views.activities.Landing_Page_Activity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "API FETCH";
    EditText emailText,passwdText;
Button regBtn;
SharedPreferences sharedPreferences;
private static final String Shared_Pref_Name = "quiz_pref";
private static final String Key_Password = "password";
private static final String Key_Email = "email";
String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = findViewById(R.id.editTextEmail);
        passwdText = findViewById(R.id.editTextPassword);
        regBtn = findViewById(R.id.regBtn);
        sharedPreferences = getSharedPreferences(Shared_Pref_Name,MODE_PRIVATE);
        Bundle bundle = new Bundle();
        bundle.putString("edittext", "String.valueOf(response.body())");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Key_Email,emailText.getText().toString());
                editor.putString(Key_Password,passwdText.getText().toString());
                editor.apply();
                String email = emailText.getText().toString();
                Log.d(email, "onClick: !!!!!!!!!!!!!!");
                String password =passwdText.getText().toString();
                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Provide Email and Password", Toast.LENGTH_SHORT).show();
                }else {
                    if (email.matches(emailPattern)){
                        Intent intent = new Intent(MainActivity.this, Landing_Page_Activity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }
                   
                }
            }
        });
    }


}