package id.ac.umn.leftoverfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText usernameET , passET;
    private Button lgn;
    private TextView RegisterTV;
    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private Boolean loginIn;
    private int role;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        RegisterTV = findViewById(R.id.registerTV);
        lgn = findViewById(R.id.lgnBtn);

        // Initialize Shared Preferences and Auto Login
        sp = getSharedPreferences("Login", MODE_PRIVATE);
        userLogin(sp.getString("username", ""),
                sp.getString("pass", ""));
    }

    void userLogin(final String username, final String pass){
        if(!username.isEmpty()) {
            if(!pass.isEmpty()) {
                database.child("user").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                            if((noteDataSnapshot.child("username").getValue(String.class).equals(username)) &&
                               (noteDataSnapshot.child("pass").getValue(String.class).equals(pass))){
                                //^^cek repeat username dan password dari firebase
                                loginIn = true;
                                role = noteDataSnapshot.child("role").getValue(Integer.class);
                            }

                        }
                        if(loginIn){
                            role = sp.getInt("role", 2);

                            String roleText = role == 1 ? "Restoran" : "Customer" + ", " + Integer.toString(role);
                            Toast.makeText(MainActivity.this, "Logged in as " + username + " (" + roleText + ")", Toast.LENGTH_SHORT).show();

                            if(role == 1){
                                Intent intent = new Intent(MainActivity.this, Restoran.class);
                                startActivity(intent);
                            }else {
                                Intent intent = new Intent(MainActivity.this, Customer.class);
                                startActivity(intent);
                            }
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                    }
                });
            }
        }else {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

}
