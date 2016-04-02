package com.mobileappscompany.training.dailyphoto;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Author mpamplona
 * created on 3/2/2016
 *
 * Activity Launched when app starts
 *
 */
public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_PERMISSION_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermission();
    }


    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AsyncDialog dialog = new AsyncDialog(this);
                dialog.execute();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_RESULT);
            }

        }
    }

    /**
     * Class for AsyncTak showing explanation permission message
     */
    private class AsyncDialog extends AsyncTask<String, String, String> {

        private MainActivity activity;

        public AsyncDialog(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            this.publishProgress();

            Looper.prepare();

            String message = getString(R.string.message_dialog_permission);
            String title = getString(R.string.title_dialog_permission);
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(message).setTitle(title);
            AlertDialog dialog = builder.create();
            dialog.show();

            // Requesting permission again
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_RESULT);
                    Looper.myLooper().quit();
                }
            });

            Looper.loop();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
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

