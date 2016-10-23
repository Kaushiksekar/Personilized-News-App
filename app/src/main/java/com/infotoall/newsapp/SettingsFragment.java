package com.infotoall.newsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class SettingsFragment extends Fragment {

    View inflatedView;
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView=inflater.inflate(R.layout.fragment_settings, container, false);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int stageWidth=displayMetrics.widthPixels;

        gridView=(GridView)inflatedView.findViewById(R.id.settingsGridLayout);
        gridView.setAdapter(new ImageAdapter(getActivity(),stageWidth));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

//        settingsFragmentButton=(Button)inflatedView.findViewById(R.id.settingPageButton);
//        settingsFragmentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(),NewsPad.class);
//                startActivity(intent);
//            }
//        });
        return inflatedView;
    }

}
