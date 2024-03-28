package com.example.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPassword;
    private Button buttonSignIn;

    private void initViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextName.getText().toString().trim();
                String userPassword = editTextPassword.getText().toString().trim();

                if(userName.isEmpty()||userPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,getString(R.string.error_fields_empty), Toast.LENGTH_SHORT).show();
                }else{
                    launchNextScreen(userName);
                }
            }
        });
    }

    private void launchNextScreen(String userName){
        Intent intent = OrderActivity.newIntent(this,userName);
        startActivity(intent);
    }
}