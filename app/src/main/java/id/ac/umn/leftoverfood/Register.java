package id.ac.umn.leftoverfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private TextView login;
    private Button Regis;
    private EditText username, pass, CoPass;
    private DatabaseReference database;
    private int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Spinner
        final Spinner spinner = findViewById(R.id.RoleID);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //firebase
        database = FirebaseDatabase.getInstance().getReference();

        //button
        login = findViewById(R.id.TVLogin);
        Regis = findViewById(R.id.btnRegis);

        //input user
        username = findViewById(R.id.ETUserRegis);
        pass = findViewById(R.id.ETPassRegis);
        CoPass = findViewById(R.id.ETCoPassRegis);

        Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinner.getSelectedItem().toString().equals("Restoran")){
                    role = 1;
                    //restoran
                }else {
                    role = 2;
                    //customer
                }

                //cek username kosong atau tidak
                if(!username.getText().toString().isEmpty()) {
                    //cek password kosong atau tidak
                    if(!pass.getText().toString().isEmpty()) {
                        //cek confirm password kosong atau tidak
                        if(!CoPass.getText().toString().isEmpty()){
                            //cek pass ama comfirm pass match atau tidak
                            if(pass.getText().toString().equals(CoPass.getText().toString())){
                                submitUserToFB(new User(username.getText().toString(),pass.getText().toString(),role));
                            }
                            // jika pass tidak match
                            else {
                                Toast.makeText(Register.this, "Password don't match", Toast.LENGTH_LONG).show();
                            }
                        }
                        //jika confirm pass kosong
                        else {
                            Toast.makeText(Register.this, "Please Fill The Confirm Password", Toast.LENGTH_LONG).show();
                        }
                    }
                    //jika pass kosong
                    else {
                        Toast.makeText(Register.this, "Please Fill The Password", Toast.LENGTH_LONG).show();
                    }
                }
                //jika username kosong
                else {
                    Toast.makeText(Register.this, "Please Fill The Username", Toast.LENGTH_LONG).show();
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private  void submitUserToFB(User user){
        database.child("user").push().setValue(user).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Register.this, "Register Success", Toast.LENGTH_LONG).show();
            }
        });
    }
}
