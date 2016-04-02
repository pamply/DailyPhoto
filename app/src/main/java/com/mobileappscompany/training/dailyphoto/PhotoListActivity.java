package com.mobileappscompany.training.dailyphoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.mobileappscompany.training.dailyphoto.adapter.RecyclerViewPhotoAdapter;
import com.mobileappscompany.training.dailyphoto.db.PhotoInfoModel;
import com.mobileappscompany.training.dailyphoto.fragment.ListPhotosFragment;
import com.mobileappscompany.training.dailyphoto.ifc.OnPhotoSelectedListener;

import java.util.List;

/**
 * Author mpamplona
 * created on 3/4/2016
 *
 * Activity showing the list of photos
 *
 */
public class PhotoListActivity extends AppCompatActivity implements OnPhotoSelectedListener {

    @Override
    public void onPhotoSelected() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);


    }
}
