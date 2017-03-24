package com.example.akapil.myapplication.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.akapil.myapplication.Adapters.PagerAdapter;
import com.example.akapil.myapplication.Models.Book;
import com.example.akapil.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView itemsList;
    private ArrayList<Book> dataList = null;
    private FloatingActionButton viewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
//        fetchData();
//        initUI();
    }

    /*private void initUI() {
        itemsList = (ListView) findViewById(R.id.lv);
        viewCart = (FloatingActionButton) findViewById(R.id.fab);

        itemsList.setAdapter(new CustomAdapter(getApplicationContext(), dataList));
        viewCart.setOnClickListener(this);
    }

    private void fetchData() {
        dataList = DBHelper.getInstance(getApplicationContext()).getBooks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent i = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
                break;
        }
    }*/
}
