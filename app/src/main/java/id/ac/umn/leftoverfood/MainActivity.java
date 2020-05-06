package id.ac.umn.leftoverfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText usernameET , passET;
    Button lgn;
    boolean match = false;
    String username, pass;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        lgn = findViewById(R.id.lgnBtn);


        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameET = findViewById(R.id.userET);
                passET = findViewById(R.id.passET);

                username = usernameET.getText().toString();
                pass = passET.getText().toString();

                mAuth.signInWithEmailAndPassword(username,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this, "Username and Password Don't Match" ,Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }




}
