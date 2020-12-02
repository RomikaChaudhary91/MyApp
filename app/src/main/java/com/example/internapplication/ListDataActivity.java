package com.example.internapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.internapplication.API.ApiService;
import com.example.internapplication.API.ApiUrl;
import com.example.internapplication.Model.List;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListDataActivity extends AppCompatActivity {

    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        rv = findViewById(R.id.rv_viewId);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.baseurl).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List> call = apiService.listofuser();
        call.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {


                Log.d("romData", new Gson().toJson(response.body().getData()));

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListDataActivity.this, RecyclerView.VERTICAL, false);
                rv.setLayoutManager(linearLayoutManager);
                ListAdapter dataAdapter = new ListAdapter(ListDataActivity.this, response.body().getData());
                rv.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }
}