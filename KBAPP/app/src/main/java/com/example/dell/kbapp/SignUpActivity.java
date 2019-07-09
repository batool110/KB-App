package com.example.dell.kbapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

public class SignUpActivity extends AppCompatActivity {

    private EditText UserName, UserPassword, UserEmail;
    private Button RegButton;
    private TextView UserLogin;
    private FirebaseAuth firebaseAuth;
    private EditText UserAge;

    String name,age,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupUIviews();

        firebaseAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validate())
                {
                    //DataBase will be added
                    String User_Email = UserEmail.getText().toString().trim();
                    String User_Password = UserPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(User_Email,User_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //private void sendEmailVerification();
                                SendUserData();
                                Toast.makeText(SignUpActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SignUpActivity.this , SignInActivity.class));
                            }
                            else {
                                Toast.makeText(SignUpActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });


        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
            }
        });

    }

    private void setupUIviews()
    {
        UserName = findViewById(R.id.etUserName);
        UserEmail = findViewById(R.id.etUserEmail);
        UserPassword = findViewById(R.id.etUserPassword);
        UserLogin = findViewById(R.id.tvSignIn);
        RegButton = findViewById(R.id.btnRegister);
        UserAge = findViewById(R.id.etUserAge);
        // UserProfilePic = findViewById(R.id.ivUserProfilePic);
    }

    private Boolean Validate()
    {
        Boolean result = false;

        name = UserName.getText().toString();
        password = UserPassword.getText().toString();
        email = UserEmail.getText().toString();
        age = UserAge.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty())
        {
            Toast.makeText(this,"plz!!....Enter all the details ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }

        return result;

    }

  /*  private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        SendUserData();
                        Toast.makeText(SignUp.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(SignUp.this, MainActivity.class));
                    }else{
                        Toast.makeText(SignUp.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }*/

    private void SendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

        UserProfile userProfile = new UserProfile(name, age, email);
        myRef.setValue(userProfile);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
