package com.example.akapil.myapplication.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.akapil.myapplication.DB.DBHelper;
import com.example.akapil.myapplication.R;

import java.util.List;

/**
 * Created by akapil on 3/15/2017.
 */

public class CartActivity extends AppCompatActivity {

    ListView bookList;
    List<String> bookNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setTitle("My cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        fetchData();
        initUI();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void initUI() {
        bookList = (ListView) findViewById(R.id.cart_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, bookNames);
        bookList.setAdapter(adapter);
    }

    private void fetchData() {
        bookNames = DBHelper.getInstance(getApplicationContext())
                .getBookNamesFromCart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}





