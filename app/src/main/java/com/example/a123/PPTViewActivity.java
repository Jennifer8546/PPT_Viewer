package com.example.a123;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.a123.pptviewer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.itsrts.pptviewer.PPTViewer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PPTViewActivity extends AppCompatActivity {

        PPTViewer pptViewer;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pptview);

            pptViewer = (PPTViewer) findViewById(R.id.pptViewer);
            FirebaseStorage storage=FirebaseStorage.getInstance();
            // Create a storage reference from our app
            StorageReference storageRef = storage.getReferenceFromUrl("gs://happychildapp-b5293.appspot.com/");
            storageRef.child("PPT/ABC.vnd").getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    // Use the bytes to display the image
                    String path=Environment.getExternalStorageDirectory().getPath();
                    try {
                        FileOutputStream fos=new FileOutputStream(path);
                        fos.write(bytes);
                        fos.close();
                        Toast.makeText(getApplicationContext(), "Success!!!", Toast.LENGTH_SHORT).show();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Toast.makeText(getApplicationContext(), exception.toString()+"!!!", Toast.LENGTH_SHORT).show();
                }
            });



            String fileName = "PPT";
            StorageReference spaceRef = storageRef.child(fileName);
            String path =  spaceRef.getPath();
            pptViewer.setNext_img(R.drawable.next).setPrev_img(R.drawable.prev)
                    .setSettings_img(R.drawable.settings)
                    .setZoomin_img(R.drawable.zoomin)
                    .setZoomout_img(R.drawable.zoomout);
            pptViewer.loadPPT(this, path);
        }
    }