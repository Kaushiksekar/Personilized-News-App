package com.infotoall.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity {

    public static String intentMessage="";
    private Toolbar toolbarSettingsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbarSettingsActivity=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbarSettingsActivity);

        if(intentMessage.equals("useUp")){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else {
            getSupportActionBar().setIcon(R.drawable.add_box_black_icon);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this,NewsPad.class));
                new FacebookLoginActivity().finish();
                intentMessage="";
                new NewsPad().finish();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
