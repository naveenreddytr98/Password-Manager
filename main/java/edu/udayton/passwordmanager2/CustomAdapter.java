package edu.udayton.passwordmanager2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;

    Activity activity;
    private ArrayList<userlogins> userloginsArrayList;

    CustomAdapter( Activity activity, Context context,
                  ArrayList<userlogins> userloginsArrayList) {
        this.activity = activity;
        this.context = context;
        this.userloginsArrayList = userloginsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        List<String> userNameList = userloginsArrayList.parallelStream().map(num -> num.getUserName()).collect(Collectors.toList());
        List<String> userIdsList = userloginsArrayList.parallelStream().map(num -> num.getUserId()).collect(Collectors.toList());
        List<String> userPassList = userloginsArrayList.parallelStream().map(num -> num.getPasswords()).collect(Collectors.toList());
        List<String> userUrl =  userloginsArrayList.parallelStream().map(num -> num.getUrl()).collect(Collectors.toList());
        holder.userName.setText(String.valueOf(userNameList.get(position)));
        holder.userId.setText(String.valueOf(userIdsList.get(position)));
        holder.password.setText(String.valueOf(userPassList.get(position)));
        holder.url.setText(String.valueOf(userUrl.get(position)));
        holder.listlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("userNameTxt",String.valueOf(userNameList.get(position)));
                intent.putExtra("userIdTxt",String.valueOf(userIdsList.get(position)));
                intent.putExtra("userPassTxt",String.valueOf(userPassList.get(position)));
                intent.putExtra("userUrlTxt",String.valueOf(userUrl.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userloginsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTxt, userPassTxt, userName, userId, password, url, userUrlTxt;
        LinearLayout listlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTxt = itemView.findViewById(R.id.userNameEdit);
            userPassTxt = itemView.findViewById(R.id.passowordEdit);
            userUrlTxt =  itemView.findViewById(R.id.url);
            userName = itemView.findViewById(R.id.userNameTxt);
            userId = itemView.findViewById(R.id.userIdTxt);
            password = itemView.findViewById(R.id.userPassTxt);
            url =  itemView.findViewById(R.id.url);
            listlayout = itemView.findViewById(R.id.listLayout);

        }
    }
}
