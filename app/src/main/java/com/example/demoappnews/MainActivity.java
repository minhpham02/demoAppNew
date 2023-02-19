package com.example.demoappnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demoappnews.Models.NewApiRespone;
import com.example.demoappnews.Models.NewHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ProgressDialog dialog;
    Button btnBusiness, btnEntertainment, btnGeneral, btnHealth, btnScience, btnSports, btnTechnology;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_news);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("waiting a minute " + query);
                dialog.show();
                RequestManager requestManager = new RequestManager(MainActivity.this);
                requestManager.getNewHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Waiting...");
        dialog.show();

        mapping();
    }

    private void mapping() {
        btnBusiness = findViewById(R.id.btnBusiness);
        btnEntertainment = findViewById(R.id.btnEntertainment);
        btnGeneral = findViewById(R.id.btnGeneral);
        btnHealth = findViewById(R.id.btnHealth);
        btnScience = findViewById(R.id.btnScience);
        btnSports = findViewById(R.id.btnSports);
        btnTechnology = findViewById(R.id.btnTechnology);
        btnBusiness.setOnClickListener(this);
        btnEntertainment.setOnClickListener(this);
        btnGeneral.setOnClickListener(this);
        btnHealth.setOnClickListener(this);
        btnScience.setOnClickListener(this);
        btnSports.setOnClickListener(this);
        btnTechnology.setOnClickListener(this);

        RequestManager requestManager = new RequestManager(MainActivity.this);
        requestManager.getNewHeadlines(listener, "general", null);
    }

    private final OnFetchDataListener<NewApiRespone> listener = new OnFetchDataListener<NewApiRespone>() {
        @Override
        public void onFetchData(List<NewHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this, "No Data Found!!!", Toast.LENGTH_SHORT).show();
            }
            else{
                showNews(list);
                dialog.dismiss();    
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An Error Occured!!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewHeadlines> list) {
        recyclerView = findViewById(R.id.recyclerMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        customAdapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(customAdapter);

    }


    @Override
    public void OnNewsClicked(NewHeadlines healdings) {
        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data", healdings));
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewHeadlines(listener, category, null);
    }
}