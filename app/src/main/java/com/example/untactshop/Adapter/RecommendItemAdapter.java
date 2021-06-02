package com.example.untactshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.untactshop.Activity.Show_Item;
import com.example.untactshop.ItemInfo;
import com.example.untactshop.R;

import java.util.ArrayList;

public class RecommendItemAdapter extends RecyclerView.Adapter<RecommendItemAdapter.ViewHolder> {

    private ArrayList<ItemInfo> item;
    private Context context;
    private int position;

    public RecommendItemAdapter(ArrayList<ItemInfo> item, Context context) {
        this.context = context;
        this.item = item;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendItemAdapter.ViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(item.get(position).getPhotoUrl())
                .into(holder.item_img);
        holder.item_name.setText(item.get(position).getTitle());
        holder.item_price.setText(item.get(position).getPrice());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return (item != null ? item.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_img;
        TextView item_name;
        TextView item_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item_img = itemView.findViewById(R.id.imageView);
            this.item_name = itemView.findViewById(R.id.item_name);
            this.item_price = itemView.findViewById(R.id.item_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getBindingAdapterPosition();
                    Intent intent = new Intent(view.getContext() , Show_Item.class);
                    intent.putExtra("item", item.get(position));
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

