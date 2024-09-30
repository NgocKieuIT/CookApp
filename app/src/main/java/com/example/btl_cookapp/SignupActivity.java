package com.example.btl_cookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
 EditText username, password, repassword;
 Button dangki, dangnhap;
 DBHelperlogin DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username= findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        dangki=findViewById(R.id.btndangki);
        dangnhap=findViewById(R.id.btndangnhap);
        DB=new DBHelperlogin(SignupActivity.this,"Login.db", null,1);
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass= repassword.getText().toString();
                if(user.equals("")|| pass.equals("")||repass.equals(""))
                    Toast.makeText(SignupActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                else
                    if(pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(SignupActivity.this,"Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(getApplicationContext(),TrangChuActivity.class);
                                intent.putExtra("username",user);
                                startActivity(intent);
                            }else {

                                Toast.makeText(SignupActivity.this, "Đăng kí không thành công", Toast.LENGTH_SHORT).show();
                            }
                            }else {
                            Toast.makeText(SignupActivity.this,"Người dùng đã tồn tại! Hãy đăng nhập", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this,"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                    }
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}