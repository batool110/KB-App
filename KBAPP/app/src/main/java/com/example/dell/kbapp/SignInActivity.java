package com.example.dell.kbapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView info;
    private Button login;
    private int counter = 5;
    private Button Register;
    private FirebaseAuth FirebaseAuth;
    private android.app.ProgressDialog ProgressDialog;
    private TextView forgotPassword;

    String name,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        info = findViewById(R.id.tvInfo);
        login=findViewById(R.id.btnLogin);
        Register=findViewById(R.id.btnRegister);
        forgotPassword=findViewById(R.id.tvforgotpassword);


        info.setText("You have 5 attempts");

        FirebaseAuth = FirebaseAuth.getInstance();
        ProgressDialog = new ProgressDialog(this);

        FirebaseUser User = FirebaseAuth.getCurrentUser();

        /*if (User!=null){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Tostmsg()){
                validate(Name.getText().toString(),Password.getText().toString());}
            }

        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Int = new Intent(SignInActivity.this , SignUpActivity.class);
                startActivity(Int);

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,ResetPassword.class));
            }
        });

    }


    private void validate(String userName, String userPassword) {

        ProgressDialog.setMessage("Processing");
        ProgressDialog.show();



        FirebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    ProgressDialog.dismiss();
                    Toast.makeText(SignInActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this,MainActivity.class));
                }

                else {
                    Toast.makeText(SignInActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    counter--;
                    ProgressDialog.dismiss();
                    info.setText("Attempts remaining: " + counter);

                    if (counter == 0) {
                        login.setEnabled(false);
                    }
                }

            }
        });

        /*if ((userName.equals("Batool")) && (userPassword.equals("000"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        else {
            counter--;
            info.setText("Attemps remaining " + counter);

            if (counter == 0) {
                login.setEnabled(false);
            }
        }*/
    }

    private Boolean Tostmsg()
    {
        Boolean result = false;

        name = Name.getText().toString();
        password = Password.getText().toString();


        if(name.isEmpty() || password.isEmpty())
        {

            Toast.makeText(this,"plz!!....Enter all the details ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }

        return result;

    }
}
