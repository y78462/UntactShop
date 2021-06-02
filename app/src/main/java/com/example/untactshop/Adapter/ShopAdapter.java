package com.example.untactshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.untactshop.Fragment.ItemFragment;
import com.example.untactshop.R;
import com.example.untactshop.Activity.Shop;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private ArrayList<Shop> shops;
    private Context context;
    int position;

    public ShopAdapter(ArrayList<Shop> shops, Context context) {
        this.shops = shops;
        this.context = context;
    }

    public Object getItem(int i) {
        return shops.get(i);
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_shop_list, parent, false);
        ShopViewHolder holder = new ShopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        holder.shop_name.setText(shops.get(position).getShopname());
        holder.category.setText(shops.get(position).getCategory());
        holder.location.setText(shops.get(position).getLocation());

        String shop_category = shops.get(position).getCategory();
        Log.d("cate" , "s"+shop_category);
        switch (shop_category) {
            case "패션의류":
                holder.circleImageView.setImageDrawable(context.getDrawable(R.drawable.img_fashion));
                break;
            case "쇼핑미용":
                holder.circleImageView.setImageDrawable(context.getDrawable(R.drawable.img_beauty));
                break;
            case "디지털가전":
                holder.circleImageView.setImageDrawable(context.getDrawable(R.drawable.img_digital));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return (shops != null ? shops.size() : 0);
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView shop_name;
        TextView category;
        TextView location;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            this.circleImageView = itemView.findViewById(R.id.shop_img);
            this.shop_name = itemView.findViewById(R.id.shop_name);
            this.category = itemView.findViewById(R.id.category);
            this.location = itemView.findViewById(R.id.location);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getBindingAdapterPosition();
                    Intent intent = new Intent(view.getContext() , ItemFragment.class);
                    intent.putExtra("shop", shops.get(position));
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
