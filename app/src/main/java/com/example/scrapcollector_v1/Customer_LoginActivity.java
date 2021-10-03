package com.example.scrapcollector_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Customer_LoginActivity extends AppCompatActivity {
    private TextView lnkRegister;
    private EditText etAdmNo,etPass;
    private Button btnLogin;
    private FirebaseAuth mFirebaseAuth;

    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        getSupportActionBar().hide();

        lnkRegister = (TextView) findViewById(R.id.lnkRegister);
        lnkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScrapCollectorRegisterActivity();
            }
        });
        etAdmNo=findViewById(R.id.txtEmail);
        etPass=findViewById(R.id.txtPwd);
        btnLogin=findViewById(R.id.btnLogin);
        mFirebaseAuth=FirebaseAuth.getInstance();
        pd=new ProgressDialog(this);
        pd.setTitle("Logging in");
        pd.setMessage("Just a moment...");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                String adm=etAdmNo.getText().toString().trim();
                String pass=etPass.getText().toString().trim();
                if (!TextUtils.isEmpty(adm) && !TextUtils.isEmpty(pass))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(adm,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                pd.dismiss();
                                startActivity(new Intent(Customer_LoginActivity.this,Welcome.class));
                                toast("successfully logged in!");
                                finish();


                            }else{
                                pd.dismiss();

                                toast(task.getException().getMessage());
                            }
                        }
                    });
                }else {
                    pd.dismiss();

                    toast("You left a blank !");
                }
            }
        });
    }



    private void toast(String s) {
        Toast.makeText(this, "Message: "+s, Toast.LENGTH_SHORT).show();
    }

    public void signup(View view) {
        startActivity(new Intent(Customer_LoginActivity.this,Welcome.class));
        finish();
    }



    public void openScrapCollectorRegisterActivity(){
        Intent screg_intent = new Intent(this, Customer_RegistrationActivity.class);
        startActivity(screg_intent);
    }
}