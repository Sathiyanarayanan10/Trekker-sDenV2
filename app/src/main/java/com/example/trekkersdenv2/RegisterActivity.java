package com.example.trekkersdenv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText name;
    private Button register;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        auth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String txt_email=email.getText().toString().trim();
                String txt_password=password.getText().toString().trim();
                String txt_name=name.getText().toString().trim();

                if(TextUtils.isEmpty(txt_email )|| TextUtils.isEmpty(txt_password)){
                    Toast.makeText(RegisterActivity.this,"Empty Credentials",Toast.LENGTH_SHORT).show();
                }else if(txt_password.length() <6){
                    Toast.makeText(RegisterActivity.this,"Empty Credentials",Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(txt_email,txt_password,txt_name);
                }
            }
        });
    }

    private void registerUser(String email, String password,String txt_name) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>(){

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("Users").push().child("Name").setValue(txt_name);
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    finish();
                }
                else {Toast.makeText(RegisterActivity.this,"Failed to register",Toast.LENGTH_SHORT).show();}
            }
        });
    }
}