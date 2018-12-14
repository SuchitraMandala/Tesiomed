package com.edu.tensiomed.tensiomed;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonLogout;
    private TextView username;
    private FirebaseAuth firebaseAuth;
    private Button Link1,Link2,Link3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        username = (TextView) findViewById(R.id.username);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        Link1 = (Button) findViewById(R.id.link1);
        Link2 = (Button) findViewById(R.id.link2);
        Link3 = (Button) findViewById(R.id.link3);


        //displaying logged in user name
        username.setText("Welcome "+user.getEmail());
        //adding listener to button
        buttonLogout.setOnClickListener(this);
        Link1.setOnClickListener(this);
        Link2.setOnClickListener(this);
        Link3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));

        }
            if(v == Link1)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=XybByn6j0B8"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
                startActivity(intent)  ;
            }
        if(v == Link2)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=v38wuJDiDQM"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.google.android.youtube");
            startActivity(intent)  ;
        }
        if(v == Link3)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=qHevo4btSbY"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.google.android.youtube");
            startActivity(intent)  ;
        }
    }

}
