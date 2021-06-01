package com.example.untactshop.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.untactshop.ChatData;
import com.example.untactshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatData> Dataset;
    private String Email;
    private String Chat_Email;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private DocumentReference docRef;
    private int left;
    private int right;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_nickname;
        public TextView TextView_msg;
        public TextView TextView_time;
        public View rootView;

        public MyViewHolder(View v) {
            super(v);
            TextView_nickname = v.findViewById(R.id.TextView_nickname);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            TextView_time = v.findViewById(R.id.TextView_time);
            rootView = v;
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(List<ChatData> Dataset, Context context, String Email) {
        //{"1","2"}
        this.Dataset = Dataset;
        this.Email = Email;
    }

    // Create new views (invoked vy the layout manager)
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        if (left == 1) {
            Log.d("case", "왼쪽에 출력");
            RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.other_msgbox, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        } else {
            Log.d("case", "오른쪽에 출력");
            RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_msgbox, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ChatData chat = Dataset.get(position);

        holder.TextView_nickname.setText(chat.getNickname());
        holder.TextView_msg.setText(chat.getMsg());
        holder.TextView_time.setText(chat.getTime()); //DTO
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        //삼항 연산자
        return Dataset == null ? 0 : Dataset.size();
    }

    public ChatData getChat(int position) {
        return Dataset != null ? Dataset.get(position) : null;
    }

    public void addChat(ChatData chat) {

        Log.d("DEBUG", "addChat 메소드 호출");

        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        docRef = db.collection("users").document(user.getUid());
        Email = user.getEmail();
        Log.d("Email", Email);

        Chat_Email = chat.getIdentify();
        Log.d("Chat_Email", Chat_Email);

        if (Email.contains("admin")) { //관리자 계정
            if (Chat_Email.equals("admin")) { //관리자 메세지
                Log.d("case", "1, right");
                right = 1;
            } else { //사용자 메세지
                Log.d("case", "2, left");
                left = 1;
            }
        } else { //사용자 계정
            if (Chat_Email.equals("admin")) { //관리자 메세지
                Log.d("case", "3, left");
                left = 1;
            } else { //사용자 메세지
                Log.d("case", "4, right");
                right = 1;
            }
        }

        Dataset.add(chat);
        notifyItemInserted(Dataset.size() - 1); //갱신
//        notifyDataSetChanged();
    }
}