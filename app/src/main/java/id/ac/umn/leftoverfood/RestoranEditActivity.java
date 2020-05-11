package id.ac.umn.leftoverfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RestoranEditActivity extends AppCompatActivity {
    Button btnEditLocationR;
    Button btnUpdateDataR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran_edit);

        btnEditLocationR = findViewById(R.id.btnEditLocationR);
        btnUpdateDataR = findViewById(R.id.btnUpdateDataR);

        btnEditLocationR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pindah ke view map
            }
        });

        btnUpdateDataR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //masukin data ke database
                finish();
            }
        });
    }
}
