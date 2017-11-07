package com.karla.segunda_evaluacin;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    private Adaptador Adapter;
    private ArrayList<Datos> arrayList;
    private Button btnAgregar;
    private int PICK_PHOTO_FOR_AVATAR=3;
    private Animation anim_in,anim_out;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        img = (ImageView) findViewById(R.id.img);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);

        arrayList = new ArrayList<>();
        Adapter = new Adaptador(this, arrayList);
        ListView lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(Adapter);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

    }

  public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
                if(bmp!=null) {
                    String dic = data.getData().getPath();
                    arrayList.add(new Datos(dic,bmp));
                    Adapter.notifyDataSetChanged();
                }
            } catch (IOException e) {
                Toast.makeText(this,"Error....",Toast.LENGTH_SHORT);
                e.printStackTrace();
            }

        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException  {
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

}

