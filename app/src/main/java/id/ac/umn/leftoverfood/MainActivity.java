package id.ac.umn.leftoverfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText username , pass;
    Button lgn;
    boolean match = false;
    String username1;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        lgn = findViewById(R.id.lgnBtn);


        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.userET);
                pass = findViewById(R.id.passET);
                if(username.getText().toString().equals("admin") && pass.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Login Success" ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    intent.putExtra("role",1);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Username and Password Don't Match" ,Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
