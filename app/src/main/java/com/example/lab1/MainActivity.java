package com.example.lab1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        String email = "phanthuy2000@gmail.com";
        String password = "123456";

        dangkyFirebase(email, password);

        Button btnLogin = findViewById(R.id.btn_login);

        EditText edtEmail = findViewById(R.id.edt_email);
        EditText edtPassword = findViewById(R.id.edt_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sEmail = edtEmail.getText().toString();
                String sPassword = edtPassword.getText().toString();

                if (sEmail.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email khong duoc de trong", Toast.LENGTH_SHORT).show();
                } else if (sPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Password khong duoc de trong", Toast.LENGTH_SHORT).show();
                } else {
                    dangnhapFirebase(sEmail, sPassword);
                }

            }
        });
    }

    final String TAG = "MainActivity";

    private void dangkyFirebase (String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "Dang ky thanh cong: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed. " + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void dangnhapFirebase (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "Dang nhap thanh cong: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Dang nhap loi: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}