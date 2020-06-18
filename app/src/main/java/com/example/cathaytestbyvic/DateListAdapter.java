package com.example.cathaytestbyvic;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateListAdapter extends RecyclerView.Adapter<DateListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> mDatelist;

    public DateListAdapter(Context context, ArrayList<String> datelist) {
        mContext = context;
        mDatelist = datelist;
        Log.d("tag", "DateListAdapter mDatelist.size() = " + mDatelist.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.date_for_item, parent, false);
        DateListAdapter.ViewHolder holder = new DateListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int iPosition = position + 1;
        String title = mContext.getResources().getString(R.string.item_title) + iPosition;
        Log.d("tag", "DateListAdapter title : " + title + "\r\n Date : " + mDatelist.get(position));
        holder.textViewTitle.setText(title);

        holder.textViewDate.setText(mDatelist.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatelist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDate;

        ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }
}

