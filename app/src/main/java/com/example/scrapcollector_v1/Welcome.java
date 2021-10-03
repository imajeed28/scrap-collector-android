package com.example.scrapcollector_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {
    private Button btnCustomer;
    private Button btnScrapCollector;
    private Button btnAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        btnCustomer = (Button) findViewById(R.id.btnCustomer);
        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerLoginActivity();
            }
        });

        btnScrapCollector = (Button) findViewById(R.id.btnScrapCollector);
        btnScrapCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScrapCollectorLoginActivity();
            }
        });

        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdminLoginActivity();
            }
        });

    }
    public void openCustomerLoginActivity(){
        Intent cust_intent = new Intent(this, Customer_LoginActivity.class);
        startActivity(cust_intent);
    }

    public void openScrapCollectorLoginActivity(){
        Intent scrapcollector_intent = new Intent(this, ScrapCollector_LoginActivity.class);
        startActivity(scrapcollector_intent);
    }

    public void openAdminLoginActivity(){
        Intent admin_intent = new Intent(this, Admin_LoginActivity.class);
        startActivity(admin_intent);
    }
}