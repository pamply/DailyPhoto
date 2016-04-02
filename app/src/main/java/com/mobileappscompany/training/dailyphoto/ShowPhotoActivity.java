package com.mobileappscompany.training.dailyphoto;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleCursorAdapter;

import com.mobileappscompany.training.dailyphoto.db.PhotoInfoModel;

import java.util.Date;

/**
 * Author mpamplona
 * created on 3/2/2016
 * <p>
 * Activity shows daily random photo
 */
public class ShowPhotoActivity extends AppCompatActivity {

    private ImageView imgPhoto;
    private int currentRate;
    private RatingBar ratingPhoto;

    private SimpleCursorAdapter mAdapter;
    private static String[] IMG_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.MIME_TYPE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);

        initVars();
    }

    private void loadRandomImage() {

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String selectionClause = "";
        String sort = "random() limit 1";

        Cursor cursor = getContentResolver().query(uri, IMG_PROJECTION, selectionClause, null, sort);

        String imagePath = "";

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
        }


        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        imgPhoto.setImageBitmap(bitmap);
        imgPhoto.setTag(imagePath);

    }


    @Override
    protected void onResume() {
        super.onResume();
        loadRandomImage();

    }

    private void initVars() {
        imgPhoto = (ImageView) findViewById(R.id.img_photo);
        ratingPhoto = (RatingBar) findViewById(R.id.rating_bar_rate);
    }

    public void rate(View view) {
        currentRate = (int) ratingPhoto.getRating();

        savePhoto();

        Intent intent = new Intent(this, PhotoListActivity.class);
        startActivity(intent);
    }

    private void savePhoto() {

        PhotoInfoModel photo = new PhotoInfoModel();
        String imgPath = (String) imgPhoto.getTag();

        photo.setCreateDate(new Date());
        photo.setPath(imgPath);
        photo.setRate(currentRate);
        // TODO to use the real place
        photo.setPlace("place test");

        photo.save();
    }

}
