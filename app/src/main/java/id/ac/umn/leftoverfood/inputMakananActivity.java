package id.ac.umn.leftoverfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class inputMakananActivity extends AppCompatActivity {
    private Button foto,submit;
    private ImageView kotakFoto;
    private EditText namaMkn,harga,jumlah,deskripsi;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private DatabaseReference database;
    private StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_makanan);

        foto = findViewById(R.id.tambahFoto);
        submit = findViewById(R.id.submitMakanan);
        kotakFoto = findViewById(R.id.inputFoto);
        namaMkn = findViewById(R.id.namaMakanan);
        harga = findViewById(R.id.harga);
        jumlah = findViewById(R.id.jumlah);
        deskripsi = findViewById(R.id.keterangan);

        database = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance().getReference();


        MediaController controller = new MediaController(this);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager())
                        != null) {
                    startActivityForResult(takePictureIntent,
                            REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!namaMkn.getText().toString().isEmpty()){
                    if(!harga.getText().toString().isEmpty()){
                        if(!jumlah.getText().toString().isEmpty()){
                            if(!deskripsi.getText().toString().isEmpty()){
                                if(kotakFoto.getDrawable() != null){
                                    submitMakanan(namaMkn.getText().toString(),deskripsi.getText().toString(),
                                            (new Integer(harga.getText().toString()).intValue()), (new Integer(jumlah.getText().toString()).intValue()));
                                }
                                else {
                                    Toast.makeText(inputMakananActivity.this, "Mohon Masukan Foto Makanan", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(inputMakananActivity.this, "Mohon Masukan Deskripsi Makanan", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(inputMakananActivity.this, "Mohon Masukan Jumlah Makanan", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(inputMakananActivity.this, "Mohon Masukan harga Makanan", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(inputMakananActivity.this, "Mohon Masukan Nama Makanan", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            kotakFoto.setImageBitmap(imageBitmap);
        }
    }



    private void submitMakanan(final String nm,final String ds, final int hrg, final int kwn){
        kotakFoto.setDrawingCacheEnabled(true);
        kotakFoto.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) kotakFoto.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //Mengkompress bitmap menjadi JPG dengan kualitas gambar 100%
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();

        String namaFile = UUID.randomUUID()+".jpg"; //Nama Gambar (Secara Random)
        final String pathImage = "foto/"+namaFile;

        UploadTask uploadTask = storage.child(pathImage).putBytes(bytes);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    storage.child(pathImage).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String downloadURL = uri.toString();
                            uploadMakanan(new Menu(nm,ds,hrg,kwn,downloadURL,User.currentUsername));
                        }
                    });
                }
                else{
                    Toast.makeText(inputMakananActivity.this, "Uploading Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(inputMakananActivity.this, "Uploading Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadMakanan(Menu menu){
        database.child("Makanan").push().setValue(menu).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(inputMakananActivity.this, "Upload Makanan Sukses", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }



}
