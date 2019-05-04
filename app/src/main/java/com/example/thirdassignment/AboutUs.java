package com.example.thirdassignment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutUs extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        actionBar=getSupportActionBar();
        actionBar.setTitle("Online Clothing Shopping App");
        actionBar.setSubtitle("About Us");
    }
}
