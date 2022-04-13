package com.example.quizapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.MainActivity;
import com.example.quizapp.R;

public class ProfileActivity extends AppCompatActivity {
    TextView nameText, emailText;
    Button logoutBtn;
    SharedPreferences sharedPreferences;
    private static final String Shared_Pref_Name = "quiz_pref";
    private static final String Key_Email = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameText = findViewById(R.id.Name);
        emailText = findViewById(R.id.Email);
        logoutBtn = findViewById(R.id.logoutBtn);
        sharedPreferences = getSharedPreferences(Shared_Pref_Name, MODE_PRIVATE);
        String email = sharedPreferences.getString(Key_Email,emailText.getText().toString());
        emailText.setText(email);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                Toast.makeText(ProfileActivity.this, "Log Out Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    public void backToLandingPage(View view) {
        Intent intent = new Intent(ProfileActivity.this, Landing_Page_Activity.class);
        startActivity(intent);
    }
}