package com.example.untactshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.untactshop.Activity.Order;
import com.example.untactshop.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orders;
    private Context context;

    public OrderAdapter(ArrayList<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list, parent, false);
        OrderViewHolder holder = new OrderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        Glide.with(holder.itemView)
                .load(orders.get(position).getImageUrl())
                .into(holder.item_image);
        holder.item_name.setText(orders.get(position).getItemName());
        holder.shop_name.setText(orders.get(position).getShopName());
        holder.item_price.setText(orders.get(position).getItemPrice());
    }

    @Override
    public int getItemCount() {
        return (orders != null ? orders.size() : 0);
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView shop_name;
        TextView item_name;
        TextView item_price;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item_image = itemView.findViewById(R.id.item_image);
            this.item_name = itemView.findViewById(R.id.item_name);
            this.shop_name = itemView.findViewById(R.id.shop_name);
            this.item_price = itemView.findViewById(R.id.item_price);
        }
    }
}
