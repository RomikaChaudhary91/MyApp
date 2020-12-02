package com.example.internapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login_action(View view) {
        TextView textView = (TextView) findViewById(R.id.loginLinkId);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
//                intent = new Intent(getApplicationContext(), LoginActivity.class)y.class);
//        startActivity(intent);
////        Intent intent kuunn ccllaassss  hhoo
////        switch (view.getId()) {
////            case R.id.loginLinkId:;
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + view.getId());
//        }
//        startActivity(intent);
        }
    }
