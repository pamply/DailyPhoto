package com.mobileappscompany.training.dailyphoto.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mobileappscompany.training.dailyphoto.R;
import com.mobileappscompany.training.dailyphoto.db.PhotoInfoModel;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author mpamplona
 * created on 3/4/2016
 *
 * Adapter for RecyclerView to show viewcards with PhotoInfoModel object using ViewHolder pattern
 *
 */

public class RecyclerViewPhotoAdapter extends RecyclerView.Adapter<RecyclerViewPhotoAdapter.PhotoViewHolder> {

    private List<PhotoInfoModel> photos;

    public RecyclerViewPhotoAdapter(List<PhotoInfoModel> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_card_view, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        PhotoInfoModel photo = photos.get(position);

        SimpleDateFormat format = new SimpleDateFormat("M/dd/yyyy");
        holder.textViewDate.setText(format.format(photo.getCreateDate()));
        holder.textViewPlace.setText(photo.getPlace());
        Bitmap bitmap = BitmapFactory.decodeFile(photo.getPath());
        holder.imageViewPhoto.setImageBitmap(bitmap);
        holder.ratingBar.setRating(photo.getRate());
    }

    @Override
    public int getItemCount() {

        return photos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public List<PhotoInfoModel> getPhotos() {
        return photos;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        private CardView cardViewPhoto;
        private ImageView imageViewPhoto;
        private TextView textViewPlace;
        private TextView textViewDate;
        private RatingBar ratingBar;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            cardViewPhoto = (CardView) itemView.findViewById(R.id.card_view_photo);
            imageViewPhoto = (ImageView) itemView.findViewById(R.id.image_view_photo);
            textViewPlace = (TextView) itemView.findViewById(R.id.text_view_place);
            textViewDate = (TextView) itemView.findViewById(R.id.text_view_date);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating_bar_rate);
        }
    }

}
