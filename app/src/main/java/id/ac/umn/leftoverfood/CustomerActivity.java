package id.ac.umn.leftoverfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerActivity extends AppCompatActivity {
    Button btnLogoutC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);



        btnLogoutC = findViewById(R.id.btnLogoutC);
        btnLogoutC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("username", "");
                ed.putString("pass", "");
                ed.putInt("role", 0);
                ed.apply();

                Intent intent = new Intent(CustomerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
