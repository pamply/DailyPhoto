package com.mobileappscompany.training.dailyphoto;

import android.Manifest;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by admin on 3/2/2016.
 */
public class ShowPhotoActivity extends AppCompatActivity{

    private ImageView imgPhoto;
    private Bitmap bitmapPhoto;
    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        initVars();
        loadRandomImage();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void loadRandomImage() {
        int REQUEST_PERMISSION_RESULT = 0;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_RESULT);
        }
        String projection[] = new String[] { MediaStore.Images.Media.DATA };
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = managedQuery(images, projection, "", null, "");

        final ArrayList<String> imagesPath = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            do {
                imagesPath.add(cursor.getString(columnIndex));
            } while(cursor.moveToNext());
        }

        Random random = new Random();
        int count = imagesPath.size();
        int randomIndex = random.nextInt(count);
        String path = imagesPath.get(randomIndex);

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        imgPhoto.setImageBitmap(bitmap);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initVars() {
        imgPhoto = (ImageView) findViewById(R.id.img_photo);

        //mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_gallery_item, null, )

    }

}
