package com.infotoall.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class IndividualActivity extends AppCompatActivity {

    private Toolbar toolbarSettingsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);

        toolbarSettingsActivity=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbarSettingsActivity);

        Bundle bundle=getIntent().getExtras();
        String topic=bundle.getString("topic");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(topic);

        TextView textView=(TextView)findViewById(R.id.topicTitle);
        textView.setText(topic);
    }

}
