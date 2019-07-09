package com.example.dell.kbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button Logout;
    private Button Programing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        Logout = (Button)findViewById(R.id.btnLogoout);
        Programing = findViewById(R.id.btnC_Program);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Programing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ProgramActivity.class));
            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Logout();
        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logooutMenu:
                Logout();
                //startActivity(new Intent(MainActivity.this,SignInActivity.class));
                break;

            case R.id.ProfileMenu:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));

            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    void Logout(){
        firebaseAuth.signOut();
        finish();

        startActivity(new Intent(MainActivity.this,SignInActivity.class));
    }
}
