package com.example.androidproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.Auth.SignIn;
import com.example.androidproject.LoadingBar.LoadingBar;
import com.example.androidproject.R;
import com.example.androidproject.Singleton.SignInSingleton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private TextView mtxtHyperlinkToRegistration;
    private TextInputEditText mtxtEmail, mtxtPassword;
    private Button mbtnLogin;
    public int loginid1;

    //to store user data and not ask to login again
    private SharedPreferences sharedPreferences;

    //Firebase Database
    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project1-4a559-default-rtdb.firebaseio.com/");;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final LoadingBar loadingBar = new LoadingBar(Login.this);

            mtxtHyperlinkToRegistration = findViewById(R.id.txtHyperlinkToRegistration);
            mtxtEmail = findViewById(R.id.txtEmail);
            mtxtPassword = findViewById(R.id.txtPassword);
            mbtnLogin = findViewById(R.id.btnLogin);

            sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

            /*mbtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadingBar.startLoadingDialog();
                    SignIn signIn = new SignIn();
                    signIn.signInWithEmailAndPassword(mtxtEmail.getText().toString(), mtxtPassword.getText().toString(), Login.this, sharedPreferences);
                    loadingBar.dismissDialog();
                    //finish();
                }
            });*/

        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve email and password
                String email = mtxtEmail.getText().toString().trim();
                String password = mtxtPassword.getText().toString().trim();

                // Perform validation checks
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    if (email.isEmpty()) {
                        Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    } else if (!isValidEmail(email)) {
                        Toast.makeText(Login.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    } else if (password.isEmpty()) {
                        Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    } else {
                        // Validation passed, proceed with sign-in
                        loadingBar.startLoadingDialog();
                        SignIn signIn = new SignIn();
                        signIn.signInWithEmailAndPassword(email, password, Login.this, sharedPreferences);
                        loadingBar.dismissDialog();
                        //finish();
                    }
                }
            }
        });


        mtxtHyperlinkToRegistration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Registration.class);
                    startActivity(intent);
                }
            });
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        return email.matches(emailRegex);
    }

}