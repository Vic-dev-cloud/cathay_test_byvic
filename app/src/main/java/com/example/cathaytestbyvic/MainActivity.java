package com.example.cathaytestbyvic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements ImageAdapter.ButtonClickListener {

    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    private DateListAdapter mDateListAdapter;
    private ImageAdapter mImageAdapter;
    private ArrayList<String> listDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onChangeTab() {
        mViewPager.setCurrentItem(1);
    }

    private void init() {
        getTime();
        mDateListAdapter = new DateListAdapter(MainActivity.this, listDate);
        mImageAdapter = new ImageAdapter();
        mImageAdapter.setClickListener(this);

        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ListAdapter());
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setTabRippleColorResource(R.color.white);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().setBackgroundResource(R.drawable.tab_rectangle_background_shape);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().setBackgroundResource(R.color.white);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(MainActivity.this);
                if (position == 0) {
                    textView.setText(R.string.tab_one);
                } else if (position == 1) {
                    textView.setText(R.string.tab_two);
                }
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setGravity(Gravity.CENTER);
                tab.setCustomView(textView);
            }
        }).attach();
    }

    private void getTime(){
        listDate = new ArrayList<>();
        Calendar calendar =Calendar. getInstance();
        calendar.add( Calendar. DATE, 0);
        Date date= calendar.getTime();
        Log.d("tag","MainActivity getTime date : " + date .toString());
        listDate.add(date .toString());
        for (int i = 0; i < 6; i++) {
            calendar.add( Calendar. DATE, -1); //向前走一天
            date= calendar.getTime();
            Log.d("tag","MainActivity getTime date : " + date .toString());
            listDate.add(date .toString());
        }
    }

    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerView recyclerView = new RecyclerView(parent.getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
            recyclerView.setLayoutParams(
                    new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return new ViewHolder(recyclerView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (position == 0) {
                Log.i("TAG", "setAdapter mImageAdapter");
                holder.recyclerView.setAdapter(mImageAdapter);
            } else if (position == 1) {
                Log.i("TAG", "setAdapter mDateListAdapter");
                holder.recyclerView.setAdapter(mDateListAdapter);
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView recyclerView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                recyclerView = (RecyclerView) itemView;
            }
        }
    }
}