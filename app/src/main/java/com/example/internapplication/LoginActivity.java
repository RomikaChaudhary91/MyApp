package com.example.internapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internapplication.API.ApiService;
import com.example.internapplication.API.ApiUrl;
import com.example.internapplication.Model.User;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText emailTxt, passwordtxt;
    MaterialButton loginbtn;
    CheckBox remMe;
    TextView clickMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("userdetails", 0);
        editor = sharedPreferences.edit();
        if(sharedPreferences.contains("userLoggedIn?")){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
        emailTxt = (EditText) findViewById(R.id.emailEditText);
        passwordtxt = (EditText) findViewById(R.id.passwordEditText);
        loginbtn = (MaterialButton) findViewById(R.id.loginBtn);

        remMe = (CheckBox) findViewById(R.id.remMe);
        remMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editor.putString("email", emailTxt.getText().toString());
                    editor.putString("password", passwordtxt.getText().toString());
                    editor.putBoolean("userLoggedIn?", true);
                    editor.apply();
                }else {
                    editor.remove("userLoggedIn?");
                    editor.commit();
                }
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Logging in");
                progressDialog.show();

                String emailVal = emailTxt.getText().toString();
                String passwordVal = passwordtxt.getText().toString();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.baseurl).addConverterFactory(GsonConverterFactory.create()).build();

                ApiService apiService = retrofit.create(ApiService.class);
                Call<User> call = apiService.userLogin(emailVal, passwordVal);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        progressDialog.dismiss();

                        if(response.body()==null){
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
