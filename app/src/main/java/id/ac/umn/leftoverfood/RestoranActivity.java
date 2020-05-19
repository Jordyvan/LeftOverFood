package id.ac.umn.leftoverfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class RestoranActivity extends AppCompatActivity {
    private Button btnLogoutR , btnTambahMenu, btnEditDataR;
    private TextView TVWelcomeR;
    private DatabaseReference database;
    private ArrayList<Menu> ListMenu;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran);

        rvView = (RecyclerView) findViewById(R.id.RVMenu);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        TVWelcomeR = findViewById(R.id.TVWelcomeR);
        TVWelcomeR.setText("Welcome, "+ User.currentUsername);

        btnLogoutR = findViewById(R.id.btnLogoutR);
        btnTambahMenu = findViewById(R.id.btnTambahMenu);
        btnEditDataR = findViewById(R.id.btnEditDataR);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("Makanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListMenu = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    if(Objects.equals(noteDataSnapshot.child("idresto").getValue(String.class), User.currentUsername)){
                        Menu menu = noteDataSnapshot.getValue(Menu.class);
                        menu.setFotoUrl(noteDataSnapshot.child("url").getValue(String.class));
                        ListMenu.add(menu);
                    }
                }
                adapter = new AdapterMenuRecyclerView(ListMenu, RestoranActivity.this);
                rvView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnLogoutR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("username", "");
                ed.putString("pass", "");
                ed.putInt("role", 0);
                ed.apply();

                finish();
            }
        });

        btnTambahMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestoranActivity.this, inputMakananActivity.class);
                startActivity(intent);
            }
        });

        btnEditDataR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestoranActivity.this, RestoranEditActivity.class);
                startActivity(intent);
            }
        });
    }

}
