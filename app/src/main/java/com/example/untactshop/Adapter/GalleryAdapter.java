package com.example.untactshop.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.untactshop.R;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>
{

    public ArrayList<String> md;
    private Activity activity;
    public static class GalleryViewHolder extends RecyclerView.ViewHolder{
        public CardView cardview;
        public GalleryViewHolder(CardView v){
            super(v);
            cardview = v;
        }
    }
    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery,parent,false);
        return new GalleryViewHolder(cardView);
    }

    public GalleryAdapter(Activity activity, ArrayList<String> mDataset) {
        md = mDataset;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder holder, int position) {
        CardView cardView = holder.cardview;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultintent = new Intent();
                resultintent.putExtra("picpath", md.get(md.size()-1-holder.getBindingAdapterPosition()));

                activity.setResult(Activity.RESULT_OK,resultintent);
                activity.finish();
            }
        });
        ImageView imageView = cardView.findViewById(R.id.imageView);
        Log.d("dd",md.get(position));
        Log.d("size", "dd"+md.size()+"posititon"+position);
        //md.get(md.size());
        Glide.with(activity).load(md.get(md.size()-1-position)).centerCrop().override(400).into(imageView);
    }

    @Override
    public int getItemCount() {
        return md.size();
    }


}
