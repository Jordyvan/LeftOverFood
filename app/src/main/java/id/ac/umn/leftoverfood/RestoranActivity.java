package id.ac.umn.leftoverfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestoranActivity extends AppCompatActivity {
    Button btnLogoutR , btnTambahMenu, btnEditDataR;
    TextView TVWelcomeR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran);

        TVWelcomeR = findViewById(R.id.TVWelcomeR);
        TVWelcomeR.setText("Welcome, "+ User.currentUsername);

        btnLogoutR = findViewById(R.id.btnLogoutR);
        btnTambahMenu = findViewById(R.id.btnTambahMenu);
        btnEditDataR = findViewById(R.id.btnEditDataR);

        btnLogoutR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("username", "");
                ed.putString("pass", "");
                ed.putInt("role", 0);
                ed.apply();

                Intent intent = new Intent(RestoranActivity.this, LoginActivity.class);
                startActivity(intent);
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
