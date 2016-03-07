package com.mobileappscompany.training.dailyphoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.mobileappscompany.training.dailyphoto.adapter.RecyclerViewPhotoAdapter;
import com.mobileappscompany.training.dailyphoto.db.PhotoInfoModel;

import java.util.List;

/**
 * Author mpamplona
 * created on 3/4/2016
 *
 * Activity showing the list of photos
 *
 */
public class PhotoListActivity extends AppCompatActivity  {

    private RecyclerView recyclerViewList;
    private List<PhotoInfoModel> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        initVars();
        loadPhotos();
    }

    private void initVars() {
        recyclerViewList = (RecyclerView) findViewById(R.id.recycler_view_list);
        recyclerViewList.setHasFixedSize(true);
        photos = PhotoInfoModel.listAll(PhotoInfoModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewList.setLayoutManager(layoutManager);
        ItemTouchHelper.SimpleCallback simpleCallback  = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                ((RecyclerViewPhotoAdapter)recyclerViewList.getAdapter()).onItemDismiss(viewHolder.getAdapterPosition());
                
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewList);
    }

    private void loadPhotos() {
        RecyclerViewPhotoAdapter adapter = new RecyclerViewPhotoAdapter(photos);
        recyclerViewList.setAdapter(adapter);
    }


}
