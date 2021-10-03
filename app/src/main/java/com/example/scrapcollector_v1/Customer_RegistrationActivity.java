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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Customer_RegistrationActivity extends AppCompatActivity {
    private TextView lnkLogin;
    private EditText txtFullName,txtEmail,txtPhnNumber,txtPwd,txtcnfPwd;
    private Button btnRegister;
    private DatabaseReference myUsersDatabase;
    private FirebaseAuth mAuth;
    private String userId;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        getSupportActionBar().hide();


        txtFullName = findViewById(R.id.txtFullName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhnNumber = findViewById(R.id.txtPhnNumber);
        txtPwd = findViewById(R.id.txtPwd);
        txtcnfPwd = findViewById(R.id.txtcnfPwd);

            pd=new ProgressDialog(this);
            pd.setTitle("Creating Account");
            pd.setMessage("Just a moment...");
            mAuth=FirebaseAuth.getInstance();
            btnRegister=findViewById(R.id.btnRegister);
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pd.show();
                    String Email=txtEmail.getText().toString().trim();
                    String Pwd=txtPwd.getText().toString().trim();
                    String cnfPwd=txtcnfPwd.getText().toString().trim();
                    String FullName = txtFullName.getText().toString().trim();
                    String PhoneNumber = txtPhnNumber.getText().toString().trim();
                    if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Pwd) &&!TextUtils.isEmpty(cnfPwd))
                    {
                        if (Pwd.equals(cnfPwd)) {
                            //toast("Data ready for upload....");
                            mAuth.createUserWithEmailAndPassword(Email,Pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful())
                                    {
                                        toast("Account created Successfully, Login to verify");
                                        userId=mAuth.getCurrentUser().getUid();
                                        myUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Customers").child(userId);
                                        myUsersDatabase.child("fullName").setValue(FullName);
                                        myUsersDatabase.child("phone").setValue(PhoneNumber);
                                        myUsersDatabase.child("email").setValue(Email);

                                        pd.dismiss();
                                        mAuth.signOut();
                                        startActivity(new Intent(Customer_RegistrationActivity.this,Customer_LoginActivity.class));
                                        finish();
                                    }else {
                                        pd.dismiss();
                                        toast(task.getException().getMessage());
                                    }
                                }
                            });



                        }else {
                            pd.dismiss();

                            toast("Passwords dont match....");
                        }
                    }else {
                        pd.dismiss();

                        toast("You left a blank !");
                    }
                }
            });
        }

        public void signin(View view) {
            startActivity(new Intent(Customer_RegistrationActivity.this,Customer_LoginActivity.class));
            finish();
        }
        private void toast(String s) {
            Toast.makeText(this, "Message: "+s, Toast.LENGTH_SHORT).show();
        }


    }