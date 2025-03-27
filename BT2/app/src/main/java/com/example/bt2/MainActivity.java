package com.example.bt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> UserList = new ArrayList<>();
    private User CurrentUser;
    private EditText userNameEtxt, passwordEtxt;
    private Button loginBtn;
    private ListView UsersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        UserList.add(new User( "23521063", "user1", "1234"));
        UserList.add(new User( "22345666", "user2", "1234"));

        EditText usernameTv, passwordTv;
        usernameTv = (EditText) findViewById(R.id.UsernameTv);
        passwordTv = (EditText) findViewById(R.id.PassWordTv);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = usernameTv.getText().toString().trim();
                String passwordStr = passwordTv.getText().toString().trim();
                if(isUser(usernameStr, passwordStr))
                {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    i.putExtra("ID", CurrentUser.UserId);
                    startActivity(i);
                }
                else
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private boolean isUser(String username, String password) {
        for (User i: UserList) {
            if(i.UserName.equals(username) && i.Password.equals(password)) {
                CurrentUser = i;
                return true;
            }
        }
        return false;
    }
}