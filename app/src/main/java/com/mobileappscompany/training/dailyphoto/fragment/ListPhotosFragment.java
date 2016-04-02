package com.mobileappscompany.training.dailyphoto.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileappscompany.training.dailyphoto.R;
import com.mobileappscompany.training.dailyphoto.adapter.RecyclerViewPhotoAdapter;
import com.mobileappscompany.training.dailyphoto.db.PhotoInfoModel;
import com.mobileappscompany.training.dailyphoto.ifc.OnPhotoSelectedListener;

import java.util.List;

/**
 * Author mpamplona
 * created on 3/14/2016
 * <p/>
 * Fragment with list of photos using recycler view
 */
public class ListPhotosFragment extends Fragment {

    private OnPhotoSelectedListener listener;

    private RecyclerView recyclerViewList;
    private List<PhotoInfoModel> photos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnPhotoSelectedListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        recyclerViewList = new RecyclerView(getActivity());
        recyclerViewList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewList.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createSimpleCallback());
        itemTouchHelper.attachToRecyclerView(recyclerViewList);

        photos = PhotoInfoModel.listAll(PhotoInfoModel.class);

        RecyclerViewPhotoAdapter adapter = new RecyclerViewPhotoAdapter(photos);
        recyclerViewList.setAdapter(adapter);

        return recyclerViewList;
    }

    private ItemTouchHelper.SimpleCallback createSimpleCallback() {
        return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }


            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                // http://stackoverflow.com/questions/30850494/confirmation-and-undo-removing-in-recyclerview
                final View confirmView = viewHolder.itemView.findViewById(R.id.layout_confirm);
                confirmView.setVisibility(View.VISIBLE);

                final TextView dismiss = (TextView) viewHolder.itemView.findViewById(R.id.text_dismiss);
                dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((RecyclerViewPhotoAdapter) recyclerViewList.getAdapter()).onItemDismiss(viewHolder.getAdapterPosition());
                    }
                });

                final TextView undo = (TextView) viewHolder.itemView.findViewById(R.id.text_undo);
                undo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((RecyclerViewPhotoAdapter) recyclerViewList.getAdapter()).onItemSwip(viewHolder.getAdapterPosition());
                    }
                });
            }

        };
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
