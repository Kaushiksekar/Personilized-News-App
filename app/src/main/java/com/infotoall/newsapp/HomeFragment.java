package com.infotoall.newsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager mFragmentManager = getChildFragmentManager();
        Fragment mFragment = mFragmentManager.findFragmentById(R.id.cardViewFragmentContainerNewsPad);
        if (mFragment == null) {
            mFragment = new CardFragment();
            mFragmentManager.beginTransaction().add(R.id.cardViewFragmentContainerNewsPad, mFragment).commit();
        }
    }
}