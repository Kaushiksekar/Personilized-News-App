package com.infotoall.newsapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;

public class FacebookLoginActivity extends Activity {

    FragmentManager fm=getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
    }

    public void openDialog(View view){
        WelcomeDialog welcomeDialog=new WelcomeDialog();
        welcomeDialog.show(fm,"Welcome Dialog");
    }
}
