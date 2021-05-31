package com.example.untactshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.untactshop.ItemInfo;
import com.example.untactshop.R;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private ArrayList<ItemInfo> item;
    private Context context;

    public ItemAdapter(ArrayList<ItemInfo> item, Context context){
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new HomeViewHolder();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_item_card, null);
            viewHolder.item_img = convertView.findViewById(R.id.imageView);
            viewHolder.item_name = convertView.findViewById(R.id.item_name);
            viewHolder.item_price = convertView.findViewById(R.id.item_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HomeViewHolder) convertView.getTag();
        }

        Glide.with(convertView)
                .load(item.get(position).getPhotoUrl())
                .into(viewHolder.item_img);
        viewHolder.item_name.setText(item.get(position).getTitle());
        viewHolder.item_price.setText(item.get(position).getPrice());

        return convertView;
    }

    private class HomeViewHolder {
        ImageView item_img;
        TextView item_name;
        TextView item_price;
        TextView shop_name;
    }


}
