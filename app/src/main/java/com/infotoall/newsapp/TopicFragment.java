package com.infotoall.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TopicFragment extends ListFragment {

    public String likedTopics[]={"Computer Science","Programming","Medicine","Fashion","Philosophy","History"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(
                inflater.getContext(),android.R.layout.simple_list_item_1,likedTopics
        );
        setListAdapter(arrayAdapter);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent=new Intent(getActivity(),IndividualActivity.class);
        intent.putExtra("topic",likedTopics[position]);
//        Log.d("Topic : ",likedTopics[position]);
        startActivity(intent);
    }
}
