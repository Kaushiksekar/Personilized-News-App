package com.infotoall.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ActualSettings extends AppCompatActivity {

    private Toolbar toolbarSettingsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_settings);

        toolbarSettingsActivity=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbarSettingsActivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this,NewsPad.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
