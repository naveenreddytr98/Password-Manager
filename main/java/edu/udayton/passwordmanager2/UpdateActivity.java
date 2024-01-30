package edu.udayton.passwordmanager2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateActivity extends AppCompatActivity {

    EditText userNameInput, userPassInput, urlInput;
    TextView userIdInput;
    Button updateBtn, webClickBtn;
    String userName, userId, userPass, url;

    private final String user = "postgres";
    private final String pass = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        userNameInput = findViewById(R.id.userNameEdit1);
        userPassInput = findViewById(R.id.passowordEdit1);
        userIdInput = findViewById(R.id.idTVtext1);
        updateBtn = findViewById(R.id.updateBtnId);
        urlInput = findViewById(R.id.URLEdit1);
        getIntentData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(String.valueOf(userNameInput.getText()), String.valueOf(userPassInput.getText()), String.valueOf(urlInput.getText()),Integer.valueOf(String.valueOf(userIdInput.getText())));
            }
        });
        webClickBtn = findViewById(R.id.webClick);
        webClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+String.valueOf(urlInput.getText())));
                startActivity(intent);
            }
        });

    }

    void updateData(String userName, String password, String url, int userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                System.out.println("trying to connect");
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://10.0.2.2:5432/postgres", user, pass);
                    System.out.println("Connected to the PostgreSQL server successfully.");
                    String SQL = "update \"PasswordManager\".userlogins set username = '"+userName+"', passwords='"+password+"' where userid = "+userId+" ";
                    System.out.println(SQL);
                    try (Connection conn1 = conn;
                         Statement stmt = conn1.createStatement();
                         ResultSet rs = stmt.executeQuery(SQL)) {
                        System.out.println("updated statement check in DB");
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void getIntentData() {
        if (getIntent().hasExtra("userNameTxt")) {
            //getting
            userName = getIntent().getStringExtra("userNameTxt");
            userId = getIntent().getStringExtra("userIdTxt");
            userPass = getIntent().getStringExtra("userPassTxt");
            url = getIntent().getStringExtra("userUrlTxt");

            //setting
            userNameInput.setText(userName);
            userPassInput.setText(userPass);
            userIdInput.setText(userId);
            urlInput.setText(url);
        } else {
            System.out.println("No data");
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

}