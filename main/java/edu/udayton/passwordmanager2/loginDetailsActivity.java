package edu.udayton.passwordmanager2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class loginDetailsActivity extends AppCompatActivity {

    EditText userNameInput, password;

    String userId, userName, passwords, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);
        Button saveBtn = (Button) findViewById(R.id.saveBtnId);
        userNameInput = (EditText) findViewById(R.id.userNameEdit);
        password = (EditText) findViewById(R.id.passowordEdit);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passList = new Intent(loginDetailsActivity.this, LoginsListActivity.class);
                startActivity(passList);
                String userNameStr = userNameInput.getText().toString();
                String passwordStr = password.getText().toString();
                DBConnect dbinsert = new DBConnect();
                dbinsert.insertUserData(userNameStr, passwordStr);
            }
        };
        saveBtn.setOnClickListener(onClickListener);
    }
}