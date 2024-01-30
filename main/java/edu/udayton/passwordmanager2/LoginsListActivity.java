package edu.udayton.passwordmanager2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoginsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    DBConnect mydb;
    CustomAdapter customAdapter;
    ArrayList<String> userId, userName, passwords, URLs;
    ArrayList<userlogins> userloginsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins_list);
        Button addLoginBtn = (Button) findViewById(R.id.addItemsId);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginpage = new Intent(LoginsListActivity.this, loginDetailsActivity.class);
                startActivity(loginpage);
            }
        };
        addLoginBtn.setOnClickListener(onClickListener);
        DBConnect db = new DBConnect();
        customAdapter = new CustomAdapter(LoginsListActivity.this, this, db.displayData());
        recyclerView = findViewById(R.id.userLoginsList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LoginsListActivity.this));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode for update : " + requestCode);
        if (requestCode == 1) {
            recreate();
        }
    }
}