package com.example.cathaytestbyvic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ImageAdapter.ButtonClickListener mClickListener;

    public interface ButtonClickListener {
        void onChangeTab();
    }

    public void setClickListener(ImageAdapter.ButtonClickListener buttonClickListener) {
        this.mClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_for_item, parent, false);
        ImageAdapter.ViewHolder holder = new ImageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ImageAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(R.mipmap.banner_credituser);
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements Button.OnClickListener {
        ImageView imageView;
        Button buttonSwitchTAB;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            buttonSwitchTAB = itemView.findViewById(R.id.buttonSwitchTAB);
            buttonSwitchTAB.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onChangeTab();
        }
    }

}
