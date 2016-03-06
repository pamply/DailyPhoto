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

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Author mpamplona
 * created on 3/2/2016
 *
 * Activity shows daily random photo
 *
 */
public class ShowPhotoActivity extends AppCompatActivity {

    private ImageView imgPhoto;
    private Bitmap bitmapPhoto;
    private SimpleCursorAdapter mAdapter;
    private RatingBar ratingPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        initVars();
    }

    private void loadRandomImage() {

        String projection[] = new String[] { MediaStore.Images.Media.DATA };
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        // TODO Find a better way to do this
        Cursor cursor = managedQuery(images, projection, "", null, "");

        final ArrayList<String> imagesPath = new ArrayList<>();
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
        imgPhoto.setTag(path);
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
        PhotoInfoModel photo = new PhotoInfoModel();

        String imgPath = (String)imgPhoto.getTag();

        photo.setCreateDate(new Date());
        photo.setPath(imgPath);
        photo.setRate((int) ratingPhoto.getRating());
        // TODO to use the real place
        photo.setPlace("place test");

        photo.save();

        Intent intent = new Intent(this, PhotoListActivity.class);
        startActivity(intent);
    }

}
