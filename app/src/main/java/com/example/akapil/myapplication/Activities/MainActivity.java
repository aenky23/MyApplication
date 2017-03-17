package com.example.akapil.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.akapil.myapplication.Adapters.CustomAdapter;
import com.example.akapil.myapplication.DB.DBHelper;
import com.example.akapil.myapplication.Models.Book;
import com.example.akapil.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView itemsList;
    private ArrayList<Book> dataList = null;
    private FloatingActionButton viewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        fetchData();
        initUI();
    }

    private void initUI() {
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
    }
}
