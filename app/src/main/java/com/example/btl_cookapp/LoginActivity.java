package com.example.btl_cookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
EditText username, password;
Button btnlogin, btnback;
DBHelperlogin DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Login");

        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        btnlogin=findViewById(R.id.btndangnhap1);
        btnback=findViewById(R.id.btnback);
        DB=new DBHelperlogin(LoginActivity.this,"Login.db", null,1);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpassword=DB.checkusernamepassword(user,pass);
                    if(checkuserpassword==true){
                        Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),TrangChuActivity.class);
                        intent.putExtra("username",user);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}