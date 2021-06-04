package com.example.untactshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.untactshop.Activity.Shop;
import com.example.untactshop.Activity.ShopChatActivity;
import com.example.untactshop.ChatData;
import com.example.untactshop.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> {

    private ArrayList<Shop> chat_list;
    private Context context;
    int position;
    private String message;
    private String Time;
    private FirebaseDatabase database;
    private DatabaseReference reference;


    public ChatListAdapter(ArrayList<Shop> chat_list, Context context) {
        this.chat_list = chat_list;
        this.context = context;
    }

    public Object getItem(int i) {
        return chat_list.get(i);
    }

    @NonNull
    @Override
    public ChatListAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d("chat", "view create");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list, parent, false);
        ChatListAdapter.ChatViewHolder holder = new ChatListAdapter.ChatViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.ChatViewHolder holder, int position) {
        Shop chat1 = chat_list.get(position);
        holder.shop_name.setText(chat1.getShopname());

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        reference.child("Chat").child(chat1.getShopname()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.d("chat_KEY", "chat" + dataSnapshot.getKey());
                    Log.d("chat_VALUE", "chat" + dataSnapshot.getValue());

                    ChatData chat = dataSnapshot.getValue(ChatData.class);
                    message = chat.getMsg();
                    Time = chat.getTime();
                }

                Log.d("chat_msg", message);
                Log.d("chat_time", Time);
                holder.msg.setText(message);
                holder.time.setText(Time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return (chat_list != null ? chat_list.size() : 0);
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView shop_name;
        TextView msg;
        TextView time;


        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.img);
            this.shop_name = itemView.findViewById(R.id.shop_name);
            this.msg = itemView.findViewById(R.id.msg);
            this.time = itemView.findViewById(R.id.time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getBindingAdapterPosition();
                    Intent intent = new Intent(view.getContext(), ShopChatActivity.class);
                    intent.putExtra("name", chat_list.get(position).getShopname());
                    Log.d("intent/name", chat_list.get(position).getShopname());
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
