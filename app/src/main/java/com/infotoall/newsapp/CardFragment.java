package com.infotoall.newsapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by kaushiksekar on 15/09/16.
 */
public class CardFragment extends Fragment {
    ArrayList<CardModel> cardModelArrayList = new ArrayList<>();
    RecyclerView mRecyclerView;
    String cardViewTitles[]={"Chichen Itza","Christ the Redeemer","Great Wall of China","Machu Picchu","Petra","Taj Mahal","Colosseum"};
    int  cardViewImages[] = {R.drawable.chichen_itza,R.drawable.christ_the_redeemer,R.drawable.great_wall_of_china,R.drawable.machu_picchu,R.drawable.petra,R.drawable.taj_mahal,R.drawable.colosseum};
    String cardViewDetails[]={"Chichén Itzá is a world-famous complex of Mayan ruins on Mexico's Yucatán Peninsula. A massive step pyramid known as El Castillo dominates the 6.5-sq.-km.",
    "Christ the Redeemer is an Art Deco statue of Jesus Christ in Rio de Janeiro, Brazil, created by French sculptor Paul Landowski and built by the Brazilian engineer Heitor da Silva Costa.",
     "The Great Wall of China is a series of fortifications made of stone, brick, tamped earth, wood, and other materials, generally built along an east-to-west line across the historical northern borders.",
    "Machu Picchu is an Incan citadel set high in the Andes Mountains in Peru, above the Urubamba River valley. Built in the 15th century and later abandoned, it’s renowned for its sophisticated dry-stone walls.",
    "Petra is a famous archaeological site in Jordan's southwestern desert. Dating to around 300 B.C., it was the capital of the Nabatean Kingdom. Accessed via a narrow canyon called Al Siq, it contains tombs and temples carved into pink sandstone cliffs.",
    "The Taj Mahal is an ivory-white marble mausoleum on the south bank of the Yamuna river in the Indian city of Agra. It was commissioned in 1632 by the Mughal emperor, Shah Jahan.",
    "The Colosseum or Coliseum, also known as the Flavian Amphitheatre, is an oval amphitheatre in the centre of the city of Rome, Italy. Built of concrete and sand, it is the largest amphitheatre ever built."
    };
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_card,container,false);
        mRecyclerView=(RecyclerView) view.findViewById(R.id.cardViewRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager myLinearLayout=new LinearLayoutManager(getActivity());
        myLinearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        if(cardModelArrayList.size()>0 & mRecyclerView!=null){
            mRecyclerView.setAdapter(new MyCardAdapter(cardModelArrayList));
        }
        mRecyclerView.setLayoutManager(myLinearLayout);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class MyCardAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private ArrayList<CardModel> list;
        public MyCardAdapter(ArrayList<CardModel> Data){
            list=Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items,parent,false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.titleTextView.setText(list.get(position).getCardName());
            holder.coverImageView.setImageResource(list.get(position).getImageResourceID());
            holder.coverImageView.setTag(list.get(position).getImageResourceID());
            holder.likeImageView.setTag(R.drawable.ic_like);
            holder.detailsTextView.setText(list.get(position).getCardDetails());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTextView;
        public TextView detailsTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public MyViewHolder(View v) {
            super(v);
            titleTextView=(TextView) v.findViewById(R.id.feedTitle);
            coverImageView=(ImageView)v.findViewById(R.id.feedImageView);
            likeImageView=(ImageView)v.findViewById(R.id.likeImageView);
            shareImageView=(ImageView)v.findViewById(R.id.shareImageView);
            detailsTextView=(TextView)v.findViewById(R.id.feedDetails);

            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id=(int) likeImageView.getTag();
                    if(id==R.drawable.ic_like){
                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);
                        Toast.makeText(getActivity(),titleTextView.getText()+" added to favourites",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(getActivity(),titleTextView.getText()+" removed from favourites",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));


                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,detailsTextView.getText().toString());
                    shareIntent.setType("image/jpeg");
                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));

                }
            });
        }
    }

    public void initializeList(){
        cardModelArrayList.clear();
        for(int i=0;i<7;i++){
            CardModel item=new CardModel();
            item.setCardName(cardViewTitles[i]);
            item.setImageResourceID(cardViewImages[i]);
            item.setCardDetails(cardViewDetails[i]);
            item.setIsFavorite(0);
            item.setIsTurned(0);
            cardModelArrayList.add(item);
        }
    }
}
