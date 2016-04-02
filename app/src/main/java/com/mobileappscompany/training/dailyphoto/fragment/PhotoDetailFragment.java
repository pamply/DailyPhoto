package com.mobileappscompany.training.dailyphoto.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mobileappscompany.training.dailyphoto.ifc.OnPhotoSelectedListener;

/**
 * Author mpamplona
 * created on 3/14/2016
 * <p/>
 * Fragment with content detail from a photo
 */
public class PhotoDetailFragment extends Fragment {

    private ImageView photo;
    private RatingBar ratingBar;


    private TextView place;
    private TextView date;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());

        return null;
    }


}
