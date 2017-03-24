package com.example.akapil.myapplication.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.akapil.myapplication.Activities.CartActivity;
import com.example.akapil.myapplication.Adapters.CustomAdapter;
import com.example.akapil.myapplication.DB.DBHelper;
import com.example.akapil.myapplication.Models.Book;
import com.example.akapil.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by akapil on 3/16/2017.
 */

public class OneFragment extends Fragment implements View.OnClickListener {

    ListView itemsList;
    private ArrayList<Book> dataList = null;
    private FloatingActionButton viewCart;

    public OneFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        fetchData();
        initUI(rootView);
        return rootView;

    }

    private void initUI(View view) {
        itemsList = (ListView) view.findViewById(R.id.lv);
        viewCart = (FloatingActionButton) view.findViewById(R.id.fab);

        itemsList.setAdapter(new CustomAdapter(getContext(), dataList));
        viewCart.setOnClickListener(this);
    }

    private void fetchData() {
        dataList = DBHelper.getInstance(getContext()).getBooks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent i = new Intent(getContext(), CartActivity.class);
                startActivity(i);
                break;
        }
    }

}
