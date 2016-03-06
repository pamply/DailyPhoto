package com.mobileappscompany.training.dailyphoto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.orm.dsl.NotNull;

/**
 * Author mpamplona
 * created on 3/2/2016
 *
 * Activity Launched when app starts
 *
 */
public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_PERMISSION_RESULT = 0;
    private final static String LOG_TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_RESULT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Intent intent;

        if (requestCode == REQUEST_PERMISSION_RESULT) {
            for (int i = 0; i < permissions.length; i++) {
                if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permissions[i]))  {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        intent = new Intent(this, PermissionDeniedActivity.class);
                        startActivity(intent);
                    } else {
                        intent = new Intent(this, ShowPhotoActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }
    }

    public void discoverPhoto(View view) {
        Intent intent = new Intent(this, ShowPhotoActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
        }
    }
}

