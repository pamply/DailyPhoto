package com.mobileappscompany.training.dailyphoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by admin on 3/2/2016.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void discoverPhoto(View view) {
        Intent intent = new Intent(this, ShowPhotoActivity.class);
        startActivity(intent);
    }

}

