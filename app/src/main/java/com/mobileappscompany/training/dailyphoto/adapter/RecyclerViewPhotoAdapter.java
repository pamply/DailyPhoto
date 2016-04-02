package com.mobileappscompany.training.dailyphoto.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileappscompany.training.dailyphoto.R;
import com.mobileappscompany.training.dailyphoto.db.PhotoInfoModel;
import com.mobileappscompany.training.dailyphoto.ifc.ItemTouchHelperAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author mpamplona
 * created on 3/4/2016
 *
 * Adapter for RecyclerView to show viewcards with PhotoInfoModel object using ViewHolder pattern
 *
 */

public class RecyclerViewPhotoAdapter extends RecyclerView.Adapter<RecyclerViewPhotoAdapter.PhotoViewHolder> implements ItemTouchHelperAdapter {

    private List<PhotoInfoModel> photos;

    @Override
    public void onItemDismiss(int position) {
        PhotoInfoModel photo = photos.get(position);
        photo.delete();
        photos.remove(position);

        notifyItemRemoved(position);
    }

    @Override
    public void onItemSwip(int position) {
        notifyItemChanged(position);
    }

    public RecyclerViewPhotoAdapter(List<PhotoInfoModel> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_card_view, parent, false);
        return new PhotoViewHolder(view, createViewHolderClick());
    }

    private PhotoViewHolder.ViewHolderClick createViewHolderClick() {
        return new PhotoViewHolder.ViewHolderClick() {
            @Override
            public void onViewHolderClick(View view) {

            }
        };
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

    public static class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardViewPhoto;
        private ImageView imageViewPhoto;
        private TextView textViewPlace;
        private TextView textViewDate;
        private RatingBar ratingBar;
        private LinearLayout confirmLayout;
        private ViewHolderClick viewHolderClick;

        public PhotoViewHolder(View itemView, ViewHolderClick viewHolderClick) {
            super(itemView);
            cardViewPhoto = (CardView) itemView.findViewById(R.id.card_view_photo);
            imageViewPhoto = (ImageView) itemView.findViewById(R.id.image_view_photo);
            textViewPlace = (TextView) itemView.findViewById(R.id.text_view_place);
            textViewDate = (TextView) itemView.findViewById(R.id.text_view_date);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating_bar_rate);
            confirmLayout = (LinearLayout) itemView.findViewById(R.id.layout_confirm);
            this.viewHolderClick = viewHolderClick;
        }

        @Override
        public void onClick(View v) {
            viewHolderClick.onViewHolderClick(v);
        }

        public interface ViewHolderClick {

            void onViewHolderClick(View view);

        }

    }

}
