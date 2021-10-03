package com.example.scrapcollector_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScrapCollector_RegistrationActivity extends AppCompatActivity {
    private TextView lnkLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_collector_registration);
        getSupportActionBar().hide();
        lnkLogin = (TextView) findViewById(R.id.lnkLogin);
        lnkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScrapCollectorLoginActivity();
            }
        });

    }

    public void openScrapCollectorLoginActivity(){
        Intent sclogin_intent = new Intent(this, ScrapCollector_LoginActivity.class);
        startActivity(sclogin_intent);
    }
}