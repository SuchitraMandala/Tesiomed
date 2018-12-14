package com.edu.tensiomed.tensiomed;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonLogin;
    private TextView signUp;
    private EditText userName;
    private EditText passWord;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), VideoActivity.class));
        }
        progressDialog = new ProgressDialog(this);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        userName = (EditText) findViewById(R.id.editTextEmail1);
        passWord = (EditText) findViewById(R.id.editTextPassword1);
        signUp = (TextView) findViewById(R.id.signupText);
        buttonLogin.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private void userlogin()
    {
        String un = userName.getText().toString().trim();
        String pwd = passWord.getText().toString().trim();
        if(TextUtils.isEmpty(un)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Logging In Please Wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(un, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if(v==buttonLogin)
            userlogin();
        if(v==signUp) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
