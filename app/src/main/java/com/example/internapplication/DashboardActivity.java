package com.example.internapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class DashboardActivity extends AppCompatActivity {
    CardView cardProfile, cardRegistration, cardLocation, cardDashboard;
    MaterialButton logoutBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sharedPreferences = getSharedPreferences("userdetails", 0);
        editor = sharedPreferences.edit();

        cardDashboard = (CardView) findViewById(R.id.dashboardId);
        cardDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn = (MaterialButton) findViewById(R.id.logOutbtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("userLoggedIn?");
                editor.apply();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}