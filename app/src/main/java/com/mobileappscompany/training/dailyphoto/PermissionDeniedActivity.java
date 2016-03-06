package com.mobileappscompany.training.dailyphoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Author mpamplona
 * created on 3/4/2016
 *
 * Activity when permission is denied
 *
 */
public class PermissionDeniedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_denied);
    }

    public void requestPermission(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
