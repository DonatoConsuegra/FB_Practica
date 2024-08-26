package com.example.fb_practica2;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button downloadButton;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        downloadButton = findViewById(R.id.downloadButton);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });
    }

    private void downloadImage() {
        StorageReference imageRef = storageRef.child("gs://cloud-storage-f61c5.appspot.com/IMG-20220114-WA0045.jpg");

        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            // Usa Glide para cargar la imagen
            Glide.with(this)
                    .load(uri)
                    .into(imageView);
        }).addOnFailureListener(exception -> {
            Toast.makeText(MainActivity.this, "Error al descargar la imagen", Toast.LENGTH_SHORT).show();
        });
    }
}